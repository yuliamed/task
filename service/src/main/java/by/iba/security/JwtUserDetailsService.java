package by.iba.security;

import by.iba.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import by.iba.security.jwt.JwtUser;
import by.iba.security.jwt.JwtUserFactory;
import by.iba.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException(" no user with email = " + email);
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
