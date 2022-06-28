package by.iba.service;

import by.iba.dto.req.InspectionOrderReq;
import by.iba.dto.resp.InspectionOrderResp;

import javax.transaction.Transactional;

public interface InspectionOrderService {
    // todo заполнить сущности inspection order
    InspectionOrderResp createInspectionOrder(InspectionOrderReq orderReq);
}
