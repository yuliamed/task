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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(mapper::mapToUserDTO).collect(Collectors.toList());
    }
}
