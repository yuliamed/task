package by.iba.service.impl;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.*;
import by.iba.dto.resp.SelectionOrderResp;
import by.iba.entity.enam.OrderStatusEnum;
import by.iba.entity.enam.RoleEnum;
import by.iba.entity.order.*;
import by.iba.entity.user.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.*;
import by.iba.mapper.SelectionOrderMapper;
import by.iba.security.service.JwtUser;
import by.iba.service.SelectionOrderService;
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

import static by.iba.inteface.specification.OrderSpecification.findByCreatorNameLike;
import static by.iba.inteface.specification.SelectionOrderSpecification.*;

@Service
@AllArgsConstructor
public class SelectionOrderServiceImpl implements SelectionOrderService {
    private final SelectionOrderRepository selectionOrderRepository;
    private final EngineRepository engineRepository;
    private final TransmissionRepository transmissionRepository;
    private final CarBrandRepository carBrandRepository;
    private final SelectionOrderMapper selectionOrderMapper;
    private final DriveRepository driveRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final BodyRepository bodyRepository;

    @Transactional
    @Override
    public SelectionOrderResp createOrder(SelectionOrderReq orderReq) {
        SelectionOrder newOrder = selectionOrderMapper.toEntityFromReq(orderReq);
        newOrder.setDrives(mapToDriveEntity(orderReq.getDrives()));
        newOrder.setEngines(mapToEngineEntity(orderReq.getEngines()));
        newOrder.setTransmissions(mapToTransmissionEntity(orderReq.getTransmissions()));
        newOrder.setBrands(mapToCarBrandEntity(orderReq.getBrands()));
        newOrder.setCreator(getUserById(getUserFromAuth().getId()));
        newOrder.setCurrencyType(getCurrencyTypeByName(orderReq.getCurrencyType()));
        newOrder.setOrderStatus(orderStatusRepository.getByName(OrderStatusEnum.CREATED.name()));
        newOrder.setBodies(mapToBodyEntity(orderReq.getBodies()));
        if (Objects.nonNull(orderReq.getAutoPickerId())) {
            newOrder.setAutoPicker(getAutoPickerById(orderReq.getAutoPickerId()));
        }
        newOrder = selectionOrderRepository.save(newOrder);
        SelectionOrderResp resp = selectionOrderMapper.toDto(newOrder);
        return resp;
    }

    @Transactional
    @Override
    public SelectionOrderResp updateOrder(Long id, SelectionOrderUpdateReq orderReq) {
        SelectionOrder editingOrder = getOrderById(id);

        editingOrder.setMinYear(orderReq.getMinYear());
        editingOrder.setMileage(orderReq.getMileage());
        editingOrder.setMinEngineVolume(orderReq.getMinEngineVolume());
        editingOrder.setMaxEngineVolume(orderReq.getMaxEngineVolume());
        editingOrder.setAdditionalInfo(orderReq.getAdditionalInfo());
        editingOrder.setRangeFrom(orderReq.getRangeFrom());
        editingOrder.setRangeTo(orderReq.getRangeTo());

        editingOrder.setDrives(mapToDriveEntity(orderReq.getDrives()));
        editingOrder.setEngines(mapToEngineEntity(orderReq.getEngines()));
        editingOrder.setTransmissions(mapToTransmissionEntity(orderReq.getTransmissions()));
        editingOrder.setBodies(mapToBodyEntity(orderReq.getBodies()));
        editingOrder.setBrands(mapToCarBrandEntity(orderReq.getBrands()));
        editingOrder.setCurrencyType(getCurrencyTypeByName(orderReq.getCurrencyType()));
        editingOrder = selectionOrderRepository.save(editingOrder);
        SelectionOrderResp resp = selectionOrderMapper.toDto(editingOrder);
        return resp;
    }

    @Override
    public PageWrapper<SelectionOrderResp> findAllOrder(SelectionOrderSearchCriteriaReq searchReq) {
        Pageable pageable = PageRequest.of(searchReq.getPageNumber(), searchReq.getPageSize());

        Specification<SelectionOrder> specification = buildSelectionOrderSpecification(searchReq);
        // поиск по заказам подбора
        Page<SelectionOrder> orders = selectionOrderRepository.findAll(specification, pageable);
        List<SelectionOrderResp> resp = selectionOrderMapper.toDtoList(orders.toList());
        return PageWrapper.of(resp,
                orders.getTotalPages(),
                orders.getTotalElements(),
                pageable.getPageNumber(),
                pageable.getPageSize());
    }

    @Override
    public SelectionOrderResp getOrder(Long id) {
        return selectionOrderMapper.toDto(getOrderById(id));
    }

    private List<CarBrand> resolveBrands(List<String> brands) {
        return Objects.isNull(brands) ?
                Collections.emptyList() :
                brands.stream()
                        .map(brandName -> carBrandRepository.findByName(brandName)
                                .orElse(new CarBrand()))
                        .collect(Collectors.toList());
    }

    private Specification<SelectionOrder> buildSelectionOrderSpecification(SelectionOrderSearchCriteriaReq searchReq) {
        Specification<SelectionOrder> specification = Specification
                .where(findByMinYear(searchReq.getMinYear()));
        // Mileage
        if (Objects.nonNull(searchReq.getMileage())) {
            specification = specification.and(findByMaxMileage(searchReq.getMileage()));
        }
        // Engine volume
        if (Objects.nonNull(searchReq.getMinEngineVolume())) {
            specification = specification.and(findByMinEngineVolume(searchReq.getMinEngineVolume()));
        }
        if (Objects.nonNull(searchReq.getMaxEngineVolume())) {
            specification = specification.and(findByMaxEngineVolume(searchReq.getMaxEngineVolume()));
        }
        // Order status
        if (Objects.nonNull(searchReq.getOrderStatus())) {
            OrderStatus status = getOrderStatusByName(searchReq.getOrderStatus());
            specification = specification.and((findByOrderStatus(status)));
        }
        // Body
        if (Objects.nonNull(searchReq.getBody())) {
            Body body = getBodyByName(searchReq.getBody());
            specification = specification.and((findAllByBody(body)));
        }
        // Type engine
        if (Objects.nonNull(searchReq.getEngine())) {
            Engine engine = getEngineByName(searchReq.getEngine());
            specification = specification.and((findAllByEngine(engine)));
        }
        // Brands
        if (Objects.nonNull(searchReq.getBrands())) {
            List<CarBrand> brands = resolveBrands(searchReq.getBrands());
            specification = specification.and(findAllByBrands(brands));
        }

        if (Objects.nonNull(searchReq.getCreatorName())) {

            specification = specification.and(findByCreatorNameLike(searchReq.getCreatorName(), searchReq.getCreatorName()));
        }

        CurrencyType currencyType = getCurrencyTypeByName(searchReq.getCurrencyType());
        // range from
        Double currencyRangeFrom = searchReq.getRangeFrom() / currencyType.getRateRelativeDollar();
        specification = specification.and(findAllByRangeFrom(currencyRangeFrom));

        // range to
        Double currencyRangeTo = searchReq.getRangeTo() / currencyType.getRateRelativeDollar();
        specification = specification.and(findAllByRangeTo(currencyRangeTo));

        return specification;
    }

    private Set<Drive> mapToDriveEntity(Set<DriveReq> drivesReq) {
        Set<Drive> drives = drivesReq.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return driveRepository.findByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return drives;
    }

    private Set<Body> mapToBodyEntity(Set<BodyReq> bodies) {
        Set<Body> bodySet = bodies.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return getBodyByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return bodySet;
    }

    private Set<Engine> mapToEngineEntity(Set<EngineReq> engines) {
        Set<Engine> engineSet = engines.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return getEngineByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return engineSet;
    }

    private Set<Transmission> mapToTransmissionEntity(Set<TransmissionReq> transmissions) {
        Set<Transmission> transmissionSet = transmissions.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return transmissionRepository.findByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return transmissionSet;
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = " + id));
    }

    private SelectionOrder getOrderById(Long id) {
        return selectionOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order with id = " + id));
    }

    private JwtUser getUserFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        return jwtUser;
    }


    private User getAutoPickerById(Long id) {
        User user = getUserById(id);
        boolean isAllowed = user.getRoles().contains(roleRepository.getByName(RoleEnum.AUTO_PICKER.name()));
        if (!isAllowed) throw new ServiceException("User with id = " + id + " can't be auto-picker");
        return user;

    }

    private Set<CarBrand> mapToCarBrandEntity(Set<CarBrandReq> brands) {
        Set<CarBrand> carBrandSet = brands.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return carBrandRepository.findByName(dto.getName())
                            .orElseThrow(() -> new ServiceException("There is no car brand with name = " + dto.getName()));
                })
                .collect(Collectors.toSet());
        return carBrandSet;
    }

    private OrderStatus getOrderStatusByName(String status) {
        return orderStatusRepository.findByName(status)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order status with name = "
                        + status));
    }

    private Body getBodyByName(String body) {
        return bodyRepository.findByName(body)
                .orElseThrow(() -> new ResourceNotFoundException("There is no body with name = "
                        + body));
    }

    private Engine getEngineByName(String engine) {
        return engineRepository.findByName(engine)
                .orElseThrow(() -> new ResourceNotFoundException("There is no engine with name = "
                        + engine));
    }

    private CurrencyType getCurrencyTypeByName(String currencyType) {
        CurrencyType type = currencyTypeRepository.findByName(currencyType)
                .orElseThrow(() -> new ServiceException("There is no currency type with name " + currencyType));
        return type;
    }
}
