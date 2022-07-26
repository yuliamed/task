package by.iba.controller.auto_picker;

import by.iba.dto.req.report.InspectionReportReq;
import by.iba.dto.req.report.InspectionReportUpdateReq;
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
    public ResponseEntity<List<InspectionReport>> findAutoPickersReports() {
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

    // todo ???
    @PutMapping("/{reportId}")
    public ResponseEntity<InspectionReportResp> editReportData(@PathVariable("reportId") Long reportId,
                                                               @RequestBody @Valid InspectionReportUpdateReq reqData,
                                                               BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionReportResp resp = inspectionReportService.editReportData(reportId, reqData);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


}
