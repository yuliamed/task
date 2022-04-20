package by.iba.service.impl;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.RoleReq;
import by.iba.dto.req.UserBanReq;
import by.iba.dto.req.UserSearchCriteriaReq;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static by.iba.inteface.specification.UserSpecification.*;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public PageWrapper<UserResp> findAll(UserSearchCriteriaReq userSearchCriteriaReq) {

        Pageable pageable = PageRequest.of(userSearchCriteriaReq.getPageNumber(), userSearchCriteriaReq.getPageSize());
        Specification<User> specification = Specification.where(findBySurnameLike(userSearchCriteriaReq.getSurname()));
        specification = specification.and(findByNameLike(userSearchCriteriaReq.getName()));

        if (Objects.nonNull(userSearchCriteriaReq.getTypeOfRole())) {
            Role userRole = getRoleByName(userSearchCriteriaReq.getTypeOfRole().name());
            specification = specification.and((findByRole(userRole)));
        }
        if (Objects.nonNull(userSearchCriteriaReq.getIsActive())) {
            specification = specification.and(findByActiveStatus(userSearchCriteriaReq.getIsActive()));
        }

        Page<User> usersPage = userRepository.findAll(specification, pageable);

        List<UserResp> users = userMapper.toDtoList(usersPage.toList());
        return PageWrapper.of(users,
                usersPage.getTotalPages(),
                usersPage.getTotalElements(),
                pageable.getPageNumber(),
                pageable.getPageSize());
    }

    @Transactional
    @Override
    public UserResp addUserRole(Long userId, RoleReq roleReq) {
        User user = getUserById(userId);
        Role userRole = getRoleByName(roleReq.getTypeOfRole().name());
        user.getRoles().add(userRole);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResp approveUser(Long userId) {
        User user = getUserById(userId);
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
        //checkSelfBanning()
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

    private Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("There is no role with name = " + roleName));
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = " + userId));
    }
}
