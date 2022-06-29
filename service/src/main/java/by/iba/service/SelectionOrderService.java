package by.iba.service;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.req.order.SelectionOrderSearchCriteriaReq;
import by.iba.dto.req.order.SelectionOrderUpdateReq;
import by.iba.dto.resp.SelectionOrderResp;

public interface SelectionOrderService {
    SelectionOrderResp createOrder(SelectionOrderReq orderReq);

    SelectionOrderResp updateOrder(Long Id, SelectionOrderUpdateReq orderReq);

    PageWrapper<SelectionOrderResp> findAllOrder(SelectionOrderSearchCriteriaReq searchReq);

}
