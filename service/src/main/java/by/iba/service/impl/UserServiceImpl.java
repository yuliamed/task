package by.iba.service.impl;

import by.iba.dto.UserUpdate;
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
import by.iba.dto.req.SignUpReq;
import by.iba.security.jwt.JwtTokenProvider;
import by.iba.security.service.JwtUser;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public UserResp register(SignUpReq userReq) {
        validateEmailAvailability(userReq.getEmail());

        if (!userReq.getPass().equals(userReq.getConfirmPass())) {
            throw new ServiceException("The password didn't equals the confirmed password");
        }

        User userToSave = userMapper.toEntityFromReq(userReq);
        userToSave.setPass(passwordEncoder.encode(userReq.getPass()));
        Role roleUser = roleRepository.getByName(TypeOfRole.USER.name());
        userToSave.getRoles().add(roleUser);
        User savesUser = userRepository.save(userToSave);

        return userMapper.toDto(savesUser);
    }

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
    public UserResp updateInfo(Long id, UserUpdate userUpdate) {
        User user = getUserById(id);
        setUserFields(user, userUpdate);
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

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нет пользователя с полученным id"));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Нет пользователя с полученным email"));
    }

    private void setUserFields(final User user, final UserUpdate userUpdate) {
        String newEmail = userUpdate.getEmail();
        String oldEmail = user.getEmail();
        if (!newEmail.equals(oldEmail)) {
            validateEmailAvailability(newEmail);
            user.setEmail(newEmail);
        }
        user.setName(userUpdate.getName());
        user.setSurname(userUpdate.getSurname());
        user.setImageUrl(userUpdate.getImageUrl());
    }

    private void validateEmailAvailability(String newEmail) {
        if (userRepository.findByEmail(newEmail).isPresent()) {
            throw new ServiceException("New email " + newEmail + " is not available");
        }
    }

}
