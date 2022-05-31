package by.iba.security.jwt;

import by.iba.security.service.JwtUser;
import by.iba.security.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    static final String ORIGIN = "Origin";
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JwtUserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (Objects.nonNull(token) && jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getUserEmailFromJwtToken(token);
            JwtUser userDetails = service.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource()
                    .buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
//        System.out.println(request.getHeader(ORIGIN));
//        System.out.println(request.getMethod());
//        if (request.getHeader(ORIGIN).equals("null")) {
//            String origin = request.getHeader(ORIGIN);
//            response.setHeader("Access-Control-Allow-Origin", "*");//* or origin as u prefer
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Access-Control-Allow-Headers",
//                    request.getHeader("Access-Control-Request-Headers"));
//        }

        filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final int tokenStartPosition = 7;
        Enumeration<String> arr = request.getHeaders("");
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(tokenStartPosition);
        }
        return null;
    }
}
