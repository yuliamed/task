package by.iba.security.service;

import by.iba.entity.user.Role;
import by.iba.entity.user.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPass(),
                mapToGrantedAuthorities(new ArrayList<Role>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles){
        return userRoles.stream().map(role -> {
           return new SimpleGrantedAuthority(role.getName());
        }).collect(Collectors.toList());
    }
}
