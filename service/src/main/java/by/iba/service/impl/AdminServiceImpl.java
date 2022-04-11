package by.iba.service.impl;

import by.iba.dto.req.RoleReq;
import by.iba.dto.req.UserBanReq;
import by.iba.dto.resp.UserResp;
import by.iba.entity.Role;
import by.iba.entity.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.RoleRepository;
import by.iba.inteface.UserRepository;
import by.iba.mapper.UserMapper;
import by.iba.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public List<UserResp> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Transactional
    @Override
    public UserResp changeUserRole(Long userId, RoleReq roleReq) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no uer with id = " + userId));
        Role userRole = roleRepository.findByName(roleReq.getTypeOfRole().name())
                .orElseThrow(() -> new ResourceNotFoundException("There is no role with name = " + roleReq.getTypeOfRole().name()));
        user.getRoles().add(userRole);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResp approveUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("there is no user with id = " + userId));
        if (user.getIsActive()) {
            throw new ServiceException("User with id = " + userId + " is already approved");
        }
        user.setIsActive(true);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResp banUser(Long id, UserBanReq userBanReq) {
        User user = userRepository.getById(id);
        if (userBanReq.getIsBanned()) {
            if (Objects.nonNull(user.getBanDate())) {
                throw new ServiceException("User with id = " + id + " is already banned");
            }
            user.setBanDate(LocalDateTime.now());
        } else {
            user.setBanDate(null);
        }
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
