package by.iba.mapper;

import by.iba.dto.req.report.AbstractPartReportReq;
import by.iba.dto.req.report.SalonReportReq;
import by.iba.entity.report.AbstractPartReport;
import by.iba.entity.report.SalonReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SalonReportMapper extends SimpleAbstractMapper<SalonReport, SalonReportReq> {
    public SalonReportMapper() {
        super(SalonReport.class, SalonReportReq.class);
    }
}

//@Component
//public class SalonReportMapper extends SimpleAbstractMapper<AbstractPartReport, AbstractPartReportReq> {
//    public SalonReportMapper() {
//        super(AbstractPartReport.class, AbstractPartReportReq.class);
//    }
//}
