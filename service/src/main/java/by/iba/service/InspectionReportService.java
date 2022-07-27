package by.iba.service;

import by.iba.dto.req.report.*;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.entity.report.InspectionReport;

import java.util.List;

public interface InspectionReportService {
    InspectionReportResp createReport(Long orderId, Long autoPickerId, InspectionReportReq req);

    List<InspectionReport> findAll();

    InspectionReportResp editBodyReport(Long orderId, BodyReportReq reqData);

    InspectionReportResp electricalEquipmentReport(Long orderId, ElectricalEquipmentReportReq reqData);

    InspectionReportResp editPedantReport(Long orderId, PendantReportReq reqData);

    InspectionReportResp editTransmissionReport(Long orderId, TransmissionReportReq reqData);

    InspectionReportResp editEngineReport(Long orderId, EngineReportReq reqData);

    InspectionReportResp editReportMainData(Long orderId, InspectionReportUpdateReq reqData);

    InspectionReportResp editSalonReport(Long orderId, SalonReportReq reqData);

    InspectionReportResp getReportById(Long reportId);

    InspectionReportResp getReportByOrderId(Long reportId);
}

