package by.iba.service;

import by.iba.dto.req.report.InspectionReportReq;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.entity.report.InspectionReport;

import java.util.List;

public interface InspectionReportService {
    InspectionReportResp createReport(Long orderId, InspectionReportReq reportReq);

    List<InspectionReport> findAll();

    InspectionReportResp editReport(Long reportId, InspectionReportReq req);

    InspectionReportResp getReportById(Long reportId);

    InspectionReportResp getReportByOrderId(Long reportId);
}

