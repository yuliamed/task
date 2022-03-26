package controller;

import dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import service.UserServiceInt;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserServiceInt userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        List<UserDTO> users = this.userService.findAll();
        return users;
    }

//    @GetMapping(value = "/users")
//    public String getUsers() {
//        return "Fuck you";
//    }
}
