package controller;

import dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        List<UserDTO> users = this.userService.getAll();
        return users;
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
