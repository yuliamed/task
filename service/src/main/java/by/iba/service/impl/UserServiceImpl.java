package by.iba.service.impl;

import by.iba.dto.req.EmailReq;
import by.iba.dto.req.ResetPassReq;
import by.iba.dto.req.SignUpReq;
import by.iba.dto.req.UserUpdateReq;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.UserResp;
import by.iba.entity.Role;
import by.iba.entity.TypeOfRole;
import by.iba.entity.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.RoleRepository;
import by.iba.inteface.UserRepository;
import by.iba.mapper.UserMapper;
import by.iba.security.dto.JwtResp;
import by.iba.security.dto.SignInReq;
import by.iba.security.jwt.JwtTokenProvider;
import by.iba.security.service.JwtUser;
import by.iba.service.MailService;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private MailService mailService;

    private final Duration TOKEN_TIME_VALIDITY = Duration.ofMinutes(5);

    @Transactional
    @Override
    public UserResp signUp(SignUpReq userReq) {
        validateEmailAvailability(userReq.getEmail());

        if (!userReq.getPass().equals(userReq.getConfirmPass())) {
            throw new ServiceException("The password didn't equals the confirmed password");
        }

        User userToSave = userMapper.toEntityFromReq(userReq);
        userToSave.setPass(passwordEncoder.encode(userReq.getPass()));
        userToSave.setActivationToken(generateToken());
        Role roleUser = roleRepository.getByName(TypeOfRole.USER.name());
        userToSave.getRoles().add(roleUser);

        String subject = "Confirm your account";
        String link = "http://localhost:8080/api/v1/users/activate/" + userToSave.getActivationToken();
        mailService.sendEmail(userToSave.getEmail(), subject, link);

        User savedUser = userRepository.save(userToSave);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    @Override
    public UserResp confirmAccount(String token) {
        User user = userRepository.findByActivationToken(token)
                .orElseThrow(() -> new ServiceException("User can't confirm his account, because there is no token = " + token));
        user.setIsActive(true);
        user.setActivationToken(null);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public JwtResp signIn(SignInReq signInReq) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInReq.getEmail(), signInReq.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);

        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        User user = getUserByEmail(signInReq.getEmail());
        user.setLastVisitDate(LocalDateTime.now());
        userRepository.save(user);

        return new JwtResp(jwt, userDetails.getUsername());
    }

    @Transactional
    @Override
    public ApiResp recoveryPass(EmailReq emailReq) {
        User user = getUserByEmail(emailReq.getEmail());
        user.setRecoveryToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        String subject = "Recovery your pass";
        String message = "Token to recovery your pass is " + user.getRecoveryToken();
        mailService.sendEmail(user.getEmail(), subject, message);

        return new ApiResp("Message with recovered token has been sent to your email");
    }

    @Transactional
    @Override
    public ApiResp resetPass(String token, ResetPassReq dto) {
        User user = userRepository.findByRecoveryToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("User with recovery token: " + token
                        + " not found"));
        if (tokenIsActual(user.getTokenCreationDate())) {
            throw new ServiceException("Token for resetting is not actual");
        }

        user.setPass(passwordEncoder.encode(dto.getPass()));
        user.setRecoveryToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);

        return new ApiResp("Your pass was successfully changed");
    }

    @Override
    public UserResp findByEmail(String email) {
        User user = getUserByEmail(email);
        return userMapper.toDto(user);
    }

    @Override
    public UserResp findById(Long id) {
        User user = getUserById(id);
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResp updateInfo(Long id, UserUpdateReq userUpdateReq) {
        User user = getUserById(id);
        setUserFields(user, userUpdateReq);
        userRepository.save(user);
        return userMapper.toDto(user);
    }


    @Transactional
    @Override
    public UserResp saveImage(Long id, String imageUrl) {
        User user = getUserById(id);
        user.setImageUrl(Base64.getEncoder().encodeToString(imageUrl.getBytes()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    //TODO
    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = " + id));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with email = " + email));
    }

    private void setUserFields(final User user, final UserUpdateReq userUpdateReq) {
        String newEmail = userUpdateReq.getEmail();
        String oldEmail = user.getEmail();
        if (!newEmail.equals(oldEmail)) {
            validateEmailAvailability(newEmail);
            user.setEmail(newEmail);
        }
        user.setName(userUpdateReq.getName());
        user.setSurname(userUpdateReq.getSurname());
        user.setImageUrl(userUpdateReq.getImageUrl());
    }

    private void validateEmailAvailability(String newEmail) {
        if (userRepository.findByEmail(newEmail).isPresent()) {
            throw new ServiceException("This email " + newEmail + " is not available");
        }
    }

    private boolean tokenIsActual(LocalDateTime tokenCreationDate) {
        Duration duration = Duration.between(tokenCreationDate, LocalDateTime.now());

        return duration.compareTo(TOKEN_TIME_VALIDITY) < 0;
    }

    private String generateToken() {
        return new StringBuilder()
                .append(UUID.randomUUID())
                .append(UUID.randomUUID()).toString();
    }
}
