package by.iba.service.impl;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.*;
import by.iba.dto.resp.order.SelectionOrderResp;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.entity.enam.OrderStatusEnum;
import by.iba.entity.enam.RoleEnum;
import by.iba.entity.order.*;
import by.iba.entity.report.SelectionReport;
import by.iba.entity.user.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.CurrencyTypeRepository;
import by.iba.inteface.car_description.*;
import by.iba.inteface.order.OrderStatusRepository;
import by.iba.inteface.order.SelectionOrderRepository;
import by.iba.inteface.user.RoleRepository;
import by.iba.inteface.user.UserRepository;
import by.iba.mapper.SelectionOrderMapper;
import by.iba.mapper.SelectionReportMapper;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static by.iba.inteface.specification.SelectionOrderSpecification.*;

@Service
@AllArgsConstructor
public class SelectionOrderServiceImpl implements SelectionOrderService {
    private final SelectionOrderRepository selectionOrderRepository;
    private final EngineRepository engineRepository;
    private final TransmissionRepository transmissionRepository;
    private final CarBrandRepository carBrandRepository;
    private final DriveRepository driveRepository;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final BodyRepository bodyRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final SelectionOrderMapper selectionOrderMapper;
    private final SelectionReportMapper selectionReportMapper;

    @Transactional
    @Override
    public SelectionOrderResp createOrder(SelectionOrderReq orderReq) {
        SelectionOrder newOrder = selectionOrderMapper.toEntityFromReq(orderReq);
        //newOrder.setDrives(mapToDriveEntity(orderReq.getDrives()));
        newOrder.setEngines(mapToEngineEntity(orderReq.getEngines()));
        // newOrder.setTransmissions(mapToTransmissionEntity(orderReq.getTransmissions()));
        //newOrder.setBrands(mapToCarBrandEntity(orderReq.getBrands()));
        newOrder.setCreator(getUserById(getUserFromAuth().getId()));
        newOrder.setCurrencyType(getCurrencyTypeByName(orderReq.getCurrencyType().getName()));
        newOrder.setStatus(orderStatusRepository.getByName(OrderStatusEnum.CREATED.name()));
        //newOrder.setBodies(mapToBodyEntity(orderReq.getBodies()));
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
        editingOrder.setModel(orderReq.getModel());

        editingOrder.setDrive(mapToDriveEntity(orderReq.getDrive()));
        editingOrder.setEngines(mapToEngineEntity(orderReq.getEngines()));
        editingOrder.setTransmission(mapToTransmissionEntity(orderReq.getTransmissions()));
        editingOrder.setBody(mapToBodyEntity(orderReq.getBody()));
        editingOrder.setBrand(mapToCarBrandEntity(orderReq.getBrand()));
        editingOrder.setCurrencyType(getCurrencyTypeByName(orderReq.getCurrencyType().getName()));
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

    @Override
    public SelectionReportResp getOrderReport(Long id) {
        List<SelectionOrder> list = selectionOrderRepository.findAll();
        SelectionOrder editingOrder = getOrderById(id);
        return selectionReportMapper.toDto((SelectionReport) editingOrder.getReport());
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

        CurrencyType currencyType = getCurrencyTypeByName(searchReq.getCurrencyType());
        // range from
        Double currencyRangeFrom = searchReq.getRangeFrom() / currencyType.getRateRelativeDollar();
        specification = specification.and(findAllByRangeFrom(currencyRangeFrom));

        // range to
        Double currencyRangeTo = searchReq.getRangeTo() / currencyType.getRateRelativeDollar();
        specification = specification.and(findAllByRangeTo(currencyRangeTo));

        return specification;
    }

    private Drive mapToDriveEntity(DriveReq drivesReq) {
        if (Objects.isNull(drivesReq)) return null;
        return driveRepository.findByName(drivesReq.getName())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Drive with name = " + drivesReq.getName()));
    }

    private Body mapToBodyEntity(BodyReq bodies) {

        if (Objects.isNull(bodies)) return null;
        return getBodyByName(bodies.getName());

    }

    private Set<Engine> mapToEngineEntity(Set<EngineReq> engines) {
        Set<Engine> engineSet = engines.stream().map((dto) -> {
                    if (Objects.isNull(dto)) return null;
                    return getEngineByName(dto.getName());
                })
                .collect(Collectors.toSet());
        return engineSet;
    }

    private Transmission mapToTransmissionEntity(TransmissionReq transmissions) {
        if (Objects.isNull(transmissions)) return null;
        return transmissionRepository.findByName(transmissions.getName())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Transmission with name = " + transmissions.getName()));

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

    private CarBrand mapToCarBrandEntity(CarBrandReq brands) {
        if (Objects.isNull(brands)) return null;
        return carBrandRepository.findByName(brands.getName())
                .orElseThrow(() -> new ServiceException("There is no car brand with name = " + brands.getName()));

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
        List<CurrencyType> list = currencyTypeRepository.findAll();
        CurrencyType type = currencyTypeRepository.findByName(currencyType)
                .orElseThrow(() -> new ServiceException("There is no currency type with name " + currencyType));
        return type;
    }
}
