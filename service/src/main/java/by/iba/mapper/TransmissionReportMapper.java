package by.iba.mapper;

import by.iba.dto.req.report.TransmissionReportReq;
import by.iba.entity.report.TransmissionReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class TransmissionReportMapper extends SimpleAbstractMapper<TransmissionReport, TransmissionReportReq> {
    public TransmissionReportMapper() {
        super(TransmissionReport.class, TransmissionReportReq.class);
    }
}