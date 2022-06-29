package by.iba.mapper;

import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.resp.SelectionOrderResp;
import by.iba.entity.order.SelectionOrder;
import by.iba.mapper.common.FullAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SelectionOrderMapper extends FullAbstractMapper<SelectionOrder, SelectionOrderResp, SelectionOrderReq> {
    public SelectionOrderMapper() {
        super(SelectionOrder.class, SelectionOrderResp.class);
    }
}
