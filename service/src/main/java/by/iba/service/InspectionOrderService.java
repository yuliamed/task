package by.iba.service;

import by.iba.dto.req.order.InspectionOrderReq;
import by.iba.dto.resp.InspectionOrderResp;

public interface InspectionOrderService {
    // todo заполнить сущности inspection order
    InspectionOrderResp createInspectionOrder(InspectionOrderReq orderReq);
}
