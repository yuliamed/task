package by.iba.mapper;

import by.iba.dto.req.report.EngineReportReq;
import by.iba.entity.report.EngineReport;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class EngineReportMapper extends SimpleAbstractMapper<EngineReport, EngineReportReq> {
    public EngineReportMapper(Class<EngineReport> entity, Class<EngineReportReq> dto) {
        super(entity, dto);
    }
}