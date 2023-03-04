package by.iba.controller.user;

import by.iba.dto.req.order.InspectionOrderReq;
import by.iba.dto.req.order.InspectionOrderUpdateReq;
import by.iba.dto.resp.order.InspectionOrderResp;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.InspectionOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(value = "/api/v1/users/{id}/inspection-orders")
public class InspectionOrderController {

    private final InspectionOrderService inspectionOrderService;

    @PostMapping()
    public ResponseEntity<InspectionOrderResp> createInspectionOrder(@RequestBody @Valid InspectionOrderReq orderReq,
                                                                     BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionOrderResp inspectionOrder = inspectionOrderService.createInspectionOrder(orderReq);
        return new ResponseEntity<>(inspectionOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<InspectionOrderResp> updateOrder(@PathVariable("orderId") Long orderId,
                                                           @RequestBody @Valid InspectionOrderUpdateReq req,
                                                           BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionOrderResp order = inspectionOrderService.updateInspectionOrder(orderId, req);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<InspectionOrderResp> getOrderById(@PathVariable("orderId") Long orderId) {
        InspectionOrderResp order = inspectionOrderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{orderId}/report")
    public ResponseEntity<InspectionReportResp> getOrderReportById(@PathVariable("orderId") Long orderId) {
        InspectionReportResp report = inspectionOrderService.getOrderReport(orderId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
