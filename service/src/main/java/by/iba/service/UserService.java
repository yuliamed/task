package by.iba.service;

import by.iba.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User register(User user);
    List<User> gelAll();

    User findByEmail(String email);
    User findById(Long id);
    void delete(Long id);
}
