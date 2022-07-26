package by.iba.mapper;

import by.iba.dto.req.report.SelectionReportReq;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.entity.report.SelectionReport;
import by.iba.mapper.common.FullAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SelectionReportMapper extends FullAbstractMapper<SelectionReport, SelectionReportResp, SelectionReportReq> {
    public SelectionReportMapper() {
        super(SelectionReport.class, SelectionReportResp.class);
    }
}