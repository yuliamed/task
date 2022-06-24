package by.iba.mapper;

import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.resp.OrderResp;
import by.iba.entity.order.SelectionOrder;
import by.iba.mapper.common.FullAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SelectionOrderMapper extends FullAbstractMapper<SelectionOrder, OrderResp, SelectionOrderReq> {
    public SelectionOrderMapper() {
        super(SelectionOrder.class, OrderResp.class);
    }
}
