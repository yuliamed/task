package service;

import dto.UserDTO;
import entity.User;
import inteface.UserRepository;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map((User u)->{
            UserMapper mapper = new UserMapper();
            UserDTO new_user = mapper.mapToUserDTO(u);
            return new_user;
        }).collect(Collectors.toList());
    }

    public void saveAll(List<User> users){
        userRepository.saveAll(users);
    }
}
