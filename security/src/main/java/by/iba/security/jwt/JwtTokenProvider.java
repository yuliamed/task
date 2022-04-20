package by.iba.security.jwt;

import by.iba.security.service.JwtUser;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    public String createToken(Authentication authentication) {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .claim("id", jwtUser.getId())
                .claim("email", jwtUser.getEmail())
                .claim("time-create", now.toString())
                .claim("time-expiration", validity.toString())
                .claim("role", jwtUser.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, secret)//
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    public String getUserEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("email").toString();
    }

}
