package by.iba.service.impl;

import by.iba.dto.page.PageWrapper;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static by.iba.inteface.specification.OrderSpecification.*;

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
    private final CurrencyTypeRepository currencyTypeRepository;

    @Transactional
    @Override
    public OrderResp createOrder(OrderReq orderReq) {
        SelectionOrder newOrder = orderMapper.toEntityFromReq(orderReq);
        newOrder.setDrives(mapToDriveEntity(orderReq.getDrives()));
        newOrder.setEngines(mapToEngineEntity(orderReq.getEngines()));
        newOrder.setTransmissions(mapToTransmissionEntity(orderReq.getTransmissions()));
        newOrder.setBrands(mapToCarBrandEntity(orderReq.getBrands()));
        newOrder.setCreator(getUserById(getUserFromAuth().getId()));
        newOrder.setCurrencyType(mapToCurrencyType(orderReq.getCurrencyType()));
        newOrder.setOrderStatus(orderStatusRepository.getByName(OrderStatusEnum.CREATED.name()));
        if (Objects.nonNull(orderReq.getAutoPickerId())) {
            newOrder.setAutoPicker(getAutoPickerById(orderReq.getAutoPickerId()));
        }
        newOrder = orderRepository.save(newOrder);
        OrderResp resp = orderMapper.toDto(newOrder);
        return resp;
    }

    private CurrencyType mapToCurrencyType(String currencyType) {
        CurrencyType type = currencyTypeRepository.findByName(currencyType)
                .orElseThrow(()->new ServiceException("There is no currency type with name " + currencyType));
        return type;
    }

    @Transactional
    @Override
    public OrderResp updateOrder(Long id, OrderUpdateReq orderReq) {
        SelectionOrder editingOrder = getOrderById(id);

        editingOrder.setMinYear(orderReq.getMinYear());
        editingOrder.setMileage(orderReq.getMileage());
        editingOrder.setMinEngineVolume(orderReq.getMinEngineVolume());
        editingOrder.setMaxEngineVolume(orderReq.getMaxEngineVolume());
        editingOrder.setAdditionalInfo(orderReq.getAdditionalInfo());
        editingOrder.setCostValue(orderReq.getCostValue());

        editingOrder.setDrives(mapToDriveEntity(orderReq.getDrives()));
        editingOrder.setEngines(mapToEngineEntity(orderReq.getEngines()));
        editingOrder.setTransmissions(mapToTransmissionEntity(orderReq.getTransmissions()));
        editingOrder.setBrands(mapToCarBrandEntity(orderReq.getBrands()));
        editingOrder.setCurrencyType(mapToCurrencyType(orderReq.getCurrencyType()));
        editingOrder = orderRepository.save(editingOrder);
        OrderResp resp = orderMapper.toDto(editingOrder);
        return resp;
    }

    @Override
    public PageWrapper<OrderResp> findAll(OrderSearchCriteriaReq searchReq) {
        Pageable pageable = PageRequest.of(searchReq.getPageNumber(), searchReq.getPageSize());

        Specification<SelectionOrder> specification = Specification
                .where(findByMinYear(searchReq.getMinYear()));
        if (Objects.nonNull(searchReq.getMileage())) {
            specification = specification.and(findByMaxMileage(searchReq.getMileage()));
        }
        if (Objects.nonNull(searchReq.getMinEngineVolume())) {
            specification = specification.and(findByMinEngineVolume(searchReq.getMinEngineVolume()));
        }
        if (Objects.nonNull(searchReq.getMaxEngineVolume())) {
            specification = specification.and(findByMaxEngineVolume(searchReq.getMaxEngineVolume()));
        }
        if (Objects.nonNull(searchReq.getOrderStatus())) {
            OrderStatus status = getOrderStatusByName(searchReq.getOrderStatus());
            specification = specification.and((findByOrderStatus(status)));
        }
        if (Objects.nonNull(searchReq.getBrands())) {
            List<CarBrand> brands = resolveBrands(searchReq.getBrands());
            specification = specification.and(findAllByBrands(brands));
        }

        Page<SelectionOrder> orders = orderRepository.findAll(specification, pageable);
        List<OrderResp> resp = orderMapper.toDtoList(orders.toList());
        return PageWrapper.of(resp,
                orders.getTotalPages(),
                orders.getTotalElements(),
                pageable.getPageNumber(),
                pageable.getPageSize());
    }

    @Transactional
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

    @Override
    public List<OrderResp> getUsersOrders(Long id) {
        Specification<SelectionOrder> specification = Specification.where(findByCreatorIDLike(id));
        List<SelectionOrder> orders = orderRepository.findAll(specification);
        return orderMapper.toDtoList(orders);
    }

    @Override
    public List<OrderResp> getAutoPickersOrders(Long id) {
        Specification<SelectionOrder> specification = Specification.where(findByAutoPickerIDLike(id));
        List<SelectionOrder> orders = orderRepository.findAll(specification);
        return orderMapper.toDtoList(orders);
    }

    private List<CarBrand> resolveBrands(List<String> brands) {
        return Objects.isNull(brands) ?
                Collections.emptyList() :
                brands.stream()
                        .map(brandName -> carBrandRepository.findByName(brandName)
                                .orElse(new CarBrand()))
                        .collect(Collectors.toList());
    }

    private boolean isChangingOrderStatusAllowed(String newOrderStatus) {
        Set<Role> roles = getUserById(getUserFromAuth().getId()).getRoles();

        if (newOrderStatus.equals(OrderStatusEnum.CANCELED.name())) {
            if (!roles.contains(roleRepository.getByName(TypeOfRole.USER.name())))
                return false;
            else throw new ServiceException("You are not allowed to change status of order");
        } else if (newOrderStatus.equals(OrderStatusEnum.IN_PROCESS.name())) {
            if (roles.contains(roleRepository.getByName(TypeOfRole.USER.name())))
                return false;
            else throw new ServiceException("You are not allowed to change status of order");
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


    private User getAutoPickerById(Long id) {
        User user = getUserById(id);
        boolean isAllowed = user.getRoles().contains(roleRepository.getByName(TypeOfRole.AUTO_PICKER.name()));
        if (!isAllowed) throw new ServiceException("User with id = " + id + " can't be auto-picker");
        return user;

    }

    private Set<CarBrand> mapToCarBrandEntity(Set<CarBrandReq> brands) {
        Set<CarBrand> drives = new HashSet<>();
        drives = brands.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return carBrandRepository.findByName(dto.getName())
                            .orElseThrow(() -> new ServiceException("There is no car brand with name = " + dto.getName()));
                })
                .collect(Collectors.toSet());
        return drives;
    }

    private OrderStatus getOrderStatusByName(String status) {
        return orderStatusRepository.findByName(status)
                .orElseThrow(() -> new ResourceNotFoundException("There is no role order status with name = "
                        + status));
    }

}
