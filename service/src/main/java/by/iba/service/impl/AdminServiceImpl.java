package by.iba.service.impl;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.user.RoleListReq;
import by.iba.dto.req.user.RoleReq;
import by.iba.dto.req.user.UserBanReq;
import by.iba.dto.req.user.UserSearchCriteriaReq;
import by.iba.dto.resp.order.OrderResp;
import by.iba.dto.resp.user.UserResp;
import by.iba.entity.enam.OrderStatusEnum;
import by.iba.entity.enam.RoleEnum;
import by.iba.entity.user.Role;
import by.iba.entity.user.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.user.RoleRepository;
import by.iba.inteface.user.UserRepository;
import by.iba.mapper.UserMapper;
import by.iba.security.service.JwtUser;
import by.iba.service.AdminService;
import by.iba.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static by.iba.inteface.specification.UserSpecification.*;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final OrderService orderService;

    @Override
    public PageWrapper<UserResp> findAll(UserSearchCriteriaReq userSearchCriteriaReq) {
        Pageable pageable = PageRequest.of(userSearchCriteriaReq.getPageNumber(), userSearchCriteriaReq.getPageSize());
        Specification<User> specification = Specification.where(findBySurnameLike(userSearchCriteriaReq.getSurname()));
        specification = specification.and(findByNameLike(userSearchCriteriaReq.getName()));

        if (Objects.nonNull(userSearchCriteriaReq.getTypeOfRole())) {
            Role userRole = getRoleByName(userSearchCriteriaReq.getTypeOfRole());
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
        Role userRole = getRoleByName(roleReq.getTypeOfRole());
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

    @Override
    public UserResp changeRoleList(Long userId, RoleListReq rolesReq) {
        Set<Role> roles = new HashSet<>();
        for (RoleReq req : rolesReq.getRoles()) {
            roles.add(getRoleByName(req.getTypeOfRole()));
        }
        User user = getUserById(userId);
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResp> findAllAutoPickers() {
        Role role = roleRepository.getByName(RoleEnum.AUTO_PICKER.name());
        Specification<User> specification = Specification.where(findBySurnameLike(""))
                .and((findByRole(role)));
        List<User> autopickers = userRepository.findAll(specification);
        List<User> needed = autopickers.stream().filter((user)->{
            List<OrderResp> orders = orderService.getOrdersByAutoPickerId(user.getId());
            return orders.stream().filter((orderResp -> { return
                orderResp.getStatus().getName().equals(OrderStatusEnum.CREATED) ||
                        orderResp.getStatus().getName().equals(OrderStatusEnum.IN_PROCESS);
                })).collect(Collectors.toList()).size()<=8;
            //return orders.size()<=8;
        }).collect(Collectors.toList());
        return userMapper.toDtoList(needed);
    }

    @Transactional
    @Override
    public UserResp banUser(Long id, UserBanReq userBanReq) {
        User user = userRepository.getById(id);
        checkSelfBanning(id);
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

    private void checkSelfBanning(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        Long adminId = jwtUser.getId();
        if (adminId.equals(id)) throw new ServiceException("You can`t ban yourself, input another user id to ban");
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
