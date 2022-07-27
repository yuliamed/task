package by.iba.mapper;

import by.iba.dto.req.report.SalonReportReq;
import by.iba.entity.report.SalonReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SalonReportMapper extends SimpleAbstractMapper<SalonReport, SalonReportReq> {
    public SalonReportMapper(Class<SalonReport> entity, Class<SalonReportReq> dto) {
        super(entity, dto);
    }
}
