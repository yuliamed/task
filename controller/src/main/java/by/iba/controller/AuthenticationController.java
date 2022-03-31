package by.iba.controller;

import by.iba.entity.User;
import by.iba.security.dto.AuthenticationRequestDTO;
import by.iba.security.jwt.JwtTokenProvider;
import by.iba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            String userEmail = requestDTO.getEmail();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEmail, requestDTO.getPass()));
            User user = userService.findByEmail(userEmail);

            if (user == null) throw new UsernameNotFoundException("User with email not found ");

            String token = jwtTokenProvider.createToken(userEmail, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("email", userEmail);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or pass");
        }
    }
}
