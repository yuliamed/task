package service;

import dto.UserDTO;

import java.util.List;

public interface UserServiceInt {
    List<UserDTO> findAll();
}
