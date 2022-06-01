package by.iba.mapper;

import by.iba.dto.req.order.OrderReq;
import by.iba.dto.resp.OrderResp;
import by.iba.entity.SelectionOrder;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends FullAbstractMapper<SelectionOrder, OrderResp, OrderReq> {
    public OrderMapper() {
        super(SelectionOrder.class, OrderResp.class);
    }
}
