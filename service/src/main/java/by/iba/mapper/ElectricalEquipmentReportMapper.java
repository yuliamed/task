package by.iba.mapper;

import by.iba.dto.req.report.ElectricalEquipmentReportReq;
import by.iba.entity.report.ElectricalEquipmentReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ElectricalEquipmentReportMapper extends SimpleAbstractMapper<ElectricalEquipmentReport, ElectricalEquipmentReportReq> {
    public ElectricalEquipmentReportMapper(Class<ElectricalEquipmentReport> entity, Class<ElectricalEquipmentReportReq> dto) {
        super(entity, dto);
    }
}