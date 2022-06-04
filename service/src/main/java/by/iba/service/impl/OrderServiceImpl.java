package by.iba.service.impl;

import by.iba.dto.req.OrderStatusReq;
import by.iba.dto.req.order.*;
import by.iba.dto.resp.OrderResp;
import by.iba.entity.*;
import by.iba.entity.enam.OrderStatusEnum;
import by.iba.entity.enam.TypeOfRole;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.*;
import by.iba.mapper.OrderMapper;
import by.iba.security.service.JwtUser;
import by.iba.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final SelectionOrderRepository orderRepository;
    private final EngineRepository engineRepository;
    private final TransmissionRepository transmissionRepository;
    private final CarBrandRepository carBrandRepository;
    private final OrderMapper orderMapper;
    private final DriveRepository driveRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Transactional
    @Override
    public OrderResp createOrder(OrderReq orderReq) {
        SelectionOrder newOrder = orderMapper.toEntityFromReq(orderReq);
        newOrder.setDrives(mapToDriveEntity(orderReq.getDrives()));
        newOrder.setEngines(mapToEngineEntity(orderReq.getEngines()));
        newOrder.setTransmissions(mapToTransmissionEntity(orderReq.getTransmissions()));
        newOrder.setBrands(mapToCarBrandEntity(orderReq.getBrands()));
        newOrder.setCreator(getUserById(getUserFromAuth().getId()));
        newOrder.setOrderStatus(orderStatusRepository.getByName(OrderStatusEnum.CREATED.name()));
        if (Objects.nonNull(orderReq.getCarPickerId())) {
            newOrder.setCarPicker(getCarPickerById(orderReq.getCarPickerId()));
        }
        newOrder = orderRepository.save(newOrder);
        OrderResp resp = orderMapper.toDto(newOrder);
        return resp;
    }

    @Override
    public List<OrderResp> findAll(OrderSearchCriteriaReq searchReq) {
        return null;
    }

    @Override
    public OrderResp changeOrderStatus(Long id, OrderStatusReq orderStatusReq) {
        SelectionOrder editingOrder = getOrderById(id);
        if (isChangingOrderStatusAllowed(orderStatusReq.getNewOrderStatus())) {
            editingOrder.setOrderStatus(orderStatusRepository.getByName(
                    orderStatusReq.getNewOrderStatus()));
        }
        editingOrder = orderRepository.save(editingOrder);
        return orderMapper.toDto(editingOrder);
    }

    private boolean isChangingOrderStatusAllowed(String newOrderStatus) {
        Set<Role> roles = getUserById(getUserFromAuth().getId()).getRoles();

        if (newOrderStatus.equals(OrderStatusEnum.CANCELED.name())) {
            if(!roles.contains(roleRepository.getByName(TypeOfRole.USER.name())))
                return false;
        } else if(newOrderStatus.equals(OrderStatusEnum.IN_PROCESS.name())){
            if(roles.contains(roleRepository.getByName(TypeOfRole.USER.name())))
                return false;
        }
        return true;
    }

    private Set<Drive> mapToDriveEntity(Set<DriveReq> drivesReq) {
        Set<Drive> drives = new HashSet<>();
        drives = drivesReq.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return driveRepository.findByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return drives;
    }

    private Set<Engine> mapToEngineEntity(Set<EngineReq> engines) {
        Set<Engine> drives = new HashSet<>();
        drives = engines.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return engineRepository.findByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return drives;
    }

    private Set<Transmission> mapToTransmissionEntity(Set<TransmissionReq> transmissions) {
        Set<Transmission> drives = new HashSet<>();
        drives = transmissions.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return transmissionRepository.findByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return drives;
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = " + id));
    }

    private SelectionOrder getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order with id = " + id));
    }

    private JwtUser getUserFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        return jwtUser;
    }


    private User getCarPickerById(Long id) {
        User user = getUserById(id);
        boolean isAllowed = user.getRoles().contains(roleRepository.getByName(TypeOfRole.AUTO_PICKER.name()));
        if (!isAllowed) throw new ServiceException("User with id = " + id + " can't be auto-picker");
        return user;

    }

    private Set<CarBrand> mapToCarBrandEntity(Set<CarBrandReq> brands) {
        Set<CarBrand> drives = new HashSet<>();
        drives = brands.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return carBrandRepository.findByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return drives;
    }
}
