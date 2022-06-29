package by.iba.mapper;

import by.iba.dto.resp.OrderResp;
import by.iba.entity.order.Order;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends SimpleAbstractMapper<Order, OrderResp> {
    public OrderMapper() {
        super(Order.class, OrderResp.class);
    }
}
