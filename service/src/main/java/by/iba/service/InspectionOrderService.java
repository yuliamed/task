package by.iba.service;

import by.iba.dto.req.order.InspectionOrderReq;
import by.iba.dto.req.order.InspectionOrderUpdateReq;
import by.iba.dto.resp.order.InspectionOrderResp;
import by.iba.dto.resp.report.InspectionReportResp;

public interface InspectionOrderService {
    InspectionOrderResp createInspectionOrder(InspectionOrderReq orderReq);

    InspectionOrderResp updateInspectionOrder(Long id, InspectionOrderUpdateReq orderReq);

    InspectionOrderResp getOrder(Long id);

    InspectionReportResp getOrderReport(Long orderId);
}
