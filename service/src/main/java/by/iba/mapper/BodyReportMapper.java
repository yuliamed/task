package by.iba.mapper;

import by.iba.dto.req.report.BodyReportReq;
import by.iba.entity.report.BodyReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class BodyReportMapper extends SimpleAbstractMapper<BodyReport, BodyReportReq> {
    public BodyReportMapper() {
        super(BodyReport.class, BodyReportReq.class);
    }
}
