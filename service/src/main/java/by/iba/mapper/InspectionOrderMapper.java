package by.iba.mapper;

import by.iba.dto.req.InspectionOrderReq;
import by.iba.dto.resp.InspectionOrderResp;
import by.iba.entity.order.InspectionOrder;
import by.iba.mapper.common.FullAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class InspectionOrderMapper extends FullAbstractMapper<InspectionOrder, InspectionOrderResp, InspectionOrderReq> {
    public InspectionOrderMapper() {
        super(InspectionOrder.class, InspectionOrderResp.class);
    }
}
