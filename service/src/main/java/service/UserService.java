package service;

import dto.UserDTO;
import inteface.UserRepository;
import lombok.AllArgsConstructor;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInt {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map((User u)->{
            UserDTO userDTO = mapper.mapToUserDTO(u);
            return userDTO;
        }).collect(Collectors.toList());
    }
}
