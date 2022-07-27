package by.iba.controller.auto_picker;

import by.iba.dto.req.report.*;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.entity.report.InspectionReport;
import by.iba.exception.ControllerHelper;
import by.iba.service.InspectionReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/auto-picker/{id}/inspection-orders/{orderId}/report")
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('AUTO_PICKER')")
public class InspectionReportController {

    private final InspectionReportService inspectionReportService;

    @GetMapping()
    public ResponseEntity<List<InspectionReport>> findReports(@PathVariable("id") Long autoPickerId) {
        // todo - убери ради бога
        List<InspectionReport> list = inspectionReportService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<InspectionReportResp> createReport(@PathVariable("id") Long autoPickerId,
                                                             @PathVariable("orderId") Long orderId,
                                                             @RequestBody @Valid InspectionReportReq req,
                                                             BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.createReport(autoPickerId, orderId, req);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<InspectionReportResp> editMainReportData(@PathVariable("orderId") Long orderId,
                                                                   @RequestBody @Valid InspectionReportUpdateReq reqData,
                                                                   BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.editReportMainData(orderId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/salon")
    public ResponseEntity<InspectionReportResp> editSalonReport(@PathVariable("orderId") Long orderId,
                                                                @RequestBody @Valid SalonReportReq reqData,
                                                                BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.editSalonReport(orderId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/body")
    public ResponseEntity<InspectionReportResp> editBodyReport(@PathVariable("orderId") Long orderId,
                                                               @RequestBody @Valid BodyReportReq reqData,
                                                               BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.editBodyReport(orderId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/electrical-equipment")
    public ResponseEntity<InspectionReportResp> editElectricalEquipmentReport(@PathVariable("orderId") Long orderId,
                                                                              @RequestBody @Valid ElectricalEquipmentReportReq reqData,
                                                                              BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.electricalEquipmentReport(orderId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/pendant")
    public ResponseEntity<InspectionReportResp> editPendantReport(@PathVariable("orderId") Long orderId,
                                                                  @RequestBody @Valid PendantReportReq reqData,
                                                                  BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.editPedantReport(orderId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/engine")
    public ResponseEntity<InspectionReportResp> editEngineReport(@PathVariable("orderId") Long orderId,
                                                                 @RequestBody @Valid EngineReportReq reqData,
                                                                 BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.editEngineReport(orderId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/transmission")
    public ResponseEntity<InspectionReportResp> editTransmissionReport(@PathVariable("orderId") Long orderId,
                                                                       @RequestBody @Valid TransmissionReportReq reqData,
                                                                       BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.editTransmissionReport(orderId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
