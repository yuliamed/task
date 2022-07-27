package by.iba.mapper;

import by.iba.dto.req.report.PendantReportReq;
import by.iba.entity.report.PendantReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class PendantReportMapper extends SimpleAbstractMapper<PendantReport, PendantReportReq> {
    public PendantReportMapper() {
        super(PendantReport.class, PendantReportReq.class);
    }
}