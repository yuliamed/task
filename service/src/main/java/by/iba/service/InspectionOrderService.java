package by.iba.service;

import by.iba.dto.req.order.InspectionOrderReq;
import by.iba.dto.req.order.InspectionOrderUpdateReq;
import by.iba.dto.resp.order.InspectionOrderResp;

public interface InspectionOrderService {
    InspectionOrderResp createInspectionOrder(InspectionOrderReq orderReq);

    InspectionOrderResp updateInspectionOrder(Long id, InspectionOrderUpdateReq orderReq);

    InspectionOrderResp getOrder(Long id);
}
