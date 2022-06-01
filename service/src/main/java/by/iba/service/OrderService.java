package by.iba.service;

import by.iba.dto.req.order.OrderReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.resp.OrderResp;

import java.util.List;

public interface OrderService {
    OrderResp createOrder(OrderReq orderReq);
    List<OrderResp> findAll(OrderSearchCriteriaReq searchReq);
}
