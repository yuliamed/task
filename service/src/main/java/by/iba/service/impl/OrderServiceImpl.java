package by.iba.service.impl;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.OrderAutoPickerReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.req.order.OrderStatusReq;
import by.iba.dto.resp.OrderResp;
import by.iba.entity.enam.OrderStatusEnum;
import by.iba.entity.enam.RoleEnum;
import by.iba.entity.order.Order;
import by.iba.entity.order.OrderStatus;
import by.iba.entity.user.Role;
import by.iba.entity.user.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.OrderRepository;
import by.iba.inteface.OrderStatusRepository;
import by.iba.inteface.RoleRepository;
import by.iba.mapper.OrderMapper;
import by.iba.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static by.iba.inteface.specification.OrderSpecification.*;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;
    private final OrderMapper orderMapper;

    @Override
    public PageWrapper<OrderResp> findAll(OrderSearchCriteriaReq searchReq) {
        Pageable pageable = PageRequest.of(0, 100);
        Specification<Order> spec = null;
        if (Objects.nonNull(searchReq)) {
            spec = getSpecification(searchReq.getParam());
        }

        Page<Order> orders = orderRepository.findAll(spec, pageable);

        System.out.println(orders);

        List<OrderResp> resp = orderMapper.toDtoList(orders.toList());
        return PageWrapper.of(resp,
                orders.getTotalPages(),
                orders.getTotalElements(),
                pageable.getPageNumber(),
                pageable.getPageSize());
    }

    @Transactional
    @Override
    public OrderResp setAutoPicker(Long id, OrderAutoPickerReq autoPickerReq) {
        Order editingOrder = getOrderById(id);

        User user = userService.getUserById(autoPickerReq.getAutoPickerId());
        Set<Role> roles = user.getRoles();
        if (!roles.contains(roleRepository.getByName(RoleEnum.AUTO_PICKER.name())))
            throw new ServiceException("User with id = " + autoPickerReq.getAutoPickerId() +
                    " is not auto-picker!");
        editingOrder.setAutoPicker(user);

        editingOrder = orderRepository.save(editingOrder);

        return orderMapper.toDto(editingOrder);
    }

    @Transactional
    @Override
    public OrderResp changeOrderStatus(Long id, OrderStatusReq orderStatusReq) {
        Order editingOrder = getOrderById(id);

        if (isChangingOrderStatusAllowed(orderStatusReq.getNewOrderStatus())) {
            editingOrder.setOrderStatus(orderStatusRepository.getByName(
                    orderStatusReq.getNewOrderStatus()));
        }
        editingOrder = orderRepository.save(editingOrder);

        return orderMapper.toDto(editingOrder);
    }

    @Override
    public List<OrderResp> getOrdersByUserId(Long id) {
        Specification<Order> specification = Specification.where(findByCreatorIDLike(id));
        List<Order> orders = orderRepository.findAll(specification);

        return orderMapper.toDtoList(orders);
    }

    @Override
    public List<OrderResp> getOrdersByAutoPickerId(Long id) {
        Specification<Order> specification = Specification.where(findByAutoPickerIDLike(id));
        List<Order> orders = orderRepository.findAll(specification);

        return orderMapper.toDtoList(orders);
    }


    private boolean isChangingOrderStatusAllowed(String newOrderStatus) {
        Set<Role> roles = userService.getUserById(userService.getUserFromAuth().getId()).getRoles();

        if (newOrderStatus.equals(OrderStatusEnum.CANCELED.name())) {
            if (!roles.contains(roleRepository.getByName(RoleEnum.USER.name())))
                return false;
            else throw new ServiceException("You are not allowed to change status of order");
        } else if (newOrderStatus.equals(OrderStatusEnum.IN_PROCESS.name())) {
            if (roles.contains(roleRepository.getByName(RoleEnum.USER.name())))
                return false;
            else throw new ServiceException("You are not allowed to change status of order");
        }
        return true;
    }

    protected Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order with id = " + id));
    }

    private OrderStatus getOrderStatusByName(String status) {
        return orderStatusRepository.findByName(status)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order status with name = "
                        + status));
    }
}
