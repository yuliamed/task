package by.iba.mapper;

import by.iba.dto.req.report.AbstractPartReportReq;
import by.iba.entity.report.AbstractPartReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class PartReportMapper extends SimpleAbstractMapper<AbstractPartReport, AbstractPartReportReq> {
    public PartReportMapper(Class<AbstractPartReport> entity, Class<AbstractPartReportReq> dto) {
        super(entity, dto);
    }
}
