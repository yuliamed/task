package by.iba.service.impl;

import by.iba.dto.req.order.InspectionOrderReq;
import by.iba.dto.req.order.InspectionOrderUpdateReq;
import by.iba.dto.resp.InspectionOrderResp;
import by.iba.entity.enam.OrderStatusEnum;
import by.iba.entity.order.InspectionOrder;
import by.iba.entity.user.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.inteface.InspectionOrderRepository;
import by.iba.inteface.OrderStatusRepository;
import by.iba.inteface.UserRepository;
import by.iba.mapper.InspectionOrderMapper;
import by.iba.security.service.JwtUser;
import by.iba.service.InspectionOrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class InspectionOrderImpl implements InspectionOrderService {
    private final InspectionOrderRepository inspectionOrderRepository;
    private final InspectionOrderMapper inspectionOrderMapper;
    private final OrderStatusRepository orderStatusRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public InspectionOrderResp createInspectionOrder(InspectionOrderReq orderReq) {
        InspectionOrder newOrder = inspectionOrderMapper.toEntityFromReq(orderReq);
        newOrder = inspectionOrderRepository.save(newOrder);
        newOrder.setCreator(getUserById(getUserFromAuth().getId()));
        newOrder.setStatus(orderStatusRepository.getByName(OrderStatusEnum.CREATED.name()));
        InspectionOrderResp resp = inspectionOrderMapper.toDto(newOrder);
        return resp;
    }

    @Transactional
    @Override
    public InspectionOrderResp updateInspectionOrder(Long id, InspectionOrderUpdateReq orderReq) {
        InspectionOrder order = findOrderById(id);
        order.setAutoUrl(orderReq.getAutoUrl());
        order.setAdditionalInfo(orderReq.getAdditionalInfo());
        order = inspectionOrderRepository.save(order);
        InspectionOrderResp resp = inspectionOrderMapper.toDto(order);
        return resp;
    }

    @Override
    public InspectionOrderResp getOrder(Long id) {
        return inspectionOrderMapper.toDto(findOrderById(id));
    }

    protected InspectionOrder findOrderById(Long id) {
        return inspectionOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order with id = " + id));
    }

    private JwtUser getUserFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        return jwtUser;
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = " + id));
    }
}
