package by.iba.controller.auto_picker;

import by.iba.dto.req.report.SelectionReportReq;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.SelectionReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/auto-picker/{id}/selection-orders/{orderId}")
@PreAuthorize("hasAnyAuthority('AUTO_PICKER')")
public class SelectionReportController {
    private final SelectionReportService selectionReportService;

    @PostMapping("")
    public ResponseEntity<SelectionReportResp> createReport(@PathVariable("id") Long autoPickerId,
                                                            @PathVariable("orderId") Long orderId,
                                                            @RequestBody @Valid SelectionReportReq req,
                                                            BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        SelectionReportResp resp = selectionReportService.createReport(autoPickerId, orderId, req);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SelectionReportResp>> findAutoPickersReports() {
        // todo - убери ради бога
        List<SelectionReportResp> list = selectionReportService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
