package by.iba.service;

import by.iba.dto.req.report.DateReq;
import by.iba.dto.req.report.SelectionReportReq;
import by.iba.dto.req.report.SelectionReportUpdateReq;
import by.iba.dto.resp.report.SelectionReportResp;

import java.util.List;

public interface SelectionReportService {
    SelectionReportResp createReport(Long autoPickerId,Long orderId, SelectionReportReq req);
    SelectionReportResp setDate(Long reportID, DateReq date);
    SelectionReportResp editReport(Long orderId, SelectionReportUpdateReq req);
    SelectionReportResp getReportByOrderId(Long orderId);

    List<SelectionReportResp> findAll();
}
