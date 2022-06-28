package by.iba.service.impl;

import by.iba.dto.req.InspectionOrderReq;
import by.iba.dto.resp.InspectionOrderResp;
import by.iba.entity.order.InspectionOrder;
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
    // todo заполнить сущности inspection order
    @Transactional
    @Override
    public InspectionOrderResp createInspectionOrder(InspectionOrderReq orderReq) {
        InspectionOrder newOrder = inspectionOrderMapper.toEntityFromReq(orderReq);
        newOrder = inspectionOrderRepository.save(newOrder);
        InspectionOrderResp resp = inspectionOrderMapper.toDto(newOrder);
        return resp;
    }
}
