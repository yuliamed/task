package by.iba.service;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.OrderStatusReq;
import by.iba.dto.req.order.OrderReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.resp.OrderResp;

import java.util.List;

public interface OrderService {
    OrderResp createOrder(OrderReq orderReq);

    PageWrapper<OrderResp> findAll(OrderSearchCriteriaReq searchReq);

    OrderResp changeOrderStatus(Long id, OrderStatusReq orderStatusReq);

    List<OrderResp> getUsersOrders(Long id);

    List<OrderResp> getAutoPickersOrders(Long id);
}
