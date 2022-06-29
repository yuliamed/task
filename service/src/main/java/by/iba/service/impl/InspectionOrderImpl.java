package by.iba.service.impl;

import by.iba.dto.req.order.InspectionOrderReq;
import by.iba.dto.req.order.InspectionOrderUpdateReq;
import by.iba.dto.resp.InspectionOrderResp;
import by.iba.entity.order.InspectionOrder;
import by.iba.entity.order.Order;
import by.iba.exception.ResourceNotFoundException;
import by.iba.inteface.InspectionOrderRepository;
import by.iba.mapper.InspectionOrderMapper;
import by.iba.service.InspectionOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class InspectionOrderImpl implements InspectionOrderService {
    private final InspectionOrderRepository inspectionOrderRepository;
    private final InspectionOrderMapper inspectionOrderMapper;

    @Transactional
    @Override
    public InspectionOrderResp createInspectionOrder(InspectionOrderReq orderReq) {
        InspectionOrder newOrder = inspectionOrderMapper.toEntityFromReq(orderReq);
        newOrder = inspectionOrderRepository.save(newOrder);
        InspectionOrderResp resp = inspectionOrderMapper.toDto(newOrder);
        return resp;
    }

    @Transactional
    @Override
    public InspectionOrderResp updateInspectionOrder(Long id, InspectionOrderUpdateReq orderReq) {
        InspectionOrder order = getOrderById(id);
        order.setAutoUrl(orderReq.getAutoUrl());
        order = inspectionOrderRepository.save(order);
        InspectionOrderResp resp = inspectionOrderMapper.toDto(order);
        return resp;
    }

    protected InspectionOrder getOrderById(Long id) {
        return inspectionOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order with id = " + id));
    }
}
