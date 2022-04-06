package by.iba.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String explanation) {
        super(explanation);
    }

    public JwtAuthenticationException(String explanation, Throwable t) {
        super(explanation, t);
    }
}
