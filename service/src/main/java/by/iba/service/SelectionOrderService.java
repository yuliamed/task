package by.iba.service;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.InspectionOrderReq;
import by.iba.dto.req.OrderSearchCriteriaReq;
import by.iba.dto.req.order.OrderAutoPickerReq;
import by.iba.dto.req.order.OrderStatusReq;
import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.req.order.SelectionOrderSearchCriteriaReq;
import by.iba.dto.req.order.SelectionOrderUpdateReq;
import by.iba.dto.resp.InspectionOrderResp;
import by.iba.dto.resp.OrderResp;

import javax.transaction.Transactional;
import java.util.List;

public interface SelectionOrderService {
    OrderResp createOrder(SelectionOrderReq orderReq);

    OrderResp updateOrder(Long Id, SelectionOrderUpdateReq orderReq);

    PageWrapper<OrderResp> findAllOrder(SelectionOrderSearchCriteriaReq searchReq);

}
