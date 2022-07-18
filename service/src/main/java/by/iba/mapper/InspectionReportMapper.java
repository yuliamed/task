package by.iba.mapper;

import by.iba.dto.req.report.InspectionReportReq;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.entity.report.InspectionReport;
import by.iba.mapper.common.FullAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class InspectionReportMapper extends FullAbstractMapper<InspectionReport, InspectionReportResp, InspectionReportReq> {
    public InspectionReportMapper() {
        super(InspectionReport.class, InspectionReportResp.class);
    }
}
