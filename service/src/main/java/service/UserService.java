package service;

import dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
}
