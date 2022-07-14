package by.iba.service;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.OrderAutoPickerReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.req.order.OrderStatusReq;
import by.iba.dto.resp.order.OrderResp;

import java.util.List;

public interface OrderService {

    PageWrapper<OrderResp> findAll(OrderSearchCriteriaReq searchReq);

    OrderResp setAutoPicker(Long id, OrderAutoPickerReq autoPickerReq);

    OrderResp changeOrderStatus(Long id, OrderStatusReq orderStatusReq);

    List<OrderResp> getOrdersByUserId(Long id);

    List<OrderResp> getOrdersByAutoPickerId(Long id);
}
