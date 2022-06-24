package by.iba.service;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.OrderAutoPickerReq;
import by.iba.dto.req.order.OrderStatusReq;
import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.req.order.SelectionOrderUpdateReq;
import by.iba.dto.resp.OrderResp;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderService {
    OrderResp createSelectionOrder(SelectionOrderReq orderReq);

    @Transactional
    OrderResp updateSelectionOrder(Long Id, SelectionOrderUpdateReq orderReq);

    PageWrapper<OrderResp> findAll(OrderSearchCriteriaReq searchReq);

    @Transactional
    OrderResp setAutoPicker(Long id, OrderAutoPickerReq autoPickerReq);

    OrderResp changeOrderStatus(Long id, OrderStatusReq orderStatusReq);

    List<OrderResp> getOrdersByUserId(Long id);

    List<OrderResp> getOrdersByAutoPickerId(Long id);
}
