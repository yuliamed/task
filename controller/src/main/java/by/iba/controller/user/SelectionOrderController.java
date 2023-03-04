package by.iba.controller.user;

import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.req.order.SelectionOrderUpdateReq;
import by.iba.dto.resp.order.SelectionOrderResp;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.SelectionOrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@CrossOrigin
@RestController
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(value = "/api/v1/users/{id}/selection-orders")
public class SelectionOrderController {
    private final SelectionOrderService selectionOrderService;

    @PostMapping()
    public ResponseEntity<SelectionOrderResp> createSelectionOrder(@RequestBody @Valid SelectionOrderReq orderReq, BindingResult result) {
        //ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        SelectionOrderResp selectionOrderResp = selectionOrderService.createOrder(orderReq);
        return new ResponseEntity<>(selectionOrderResp, HttpStatus.CREATED);
    }

    @PutMapping("/{orderID}")
    public ResponseEntity<SelectionOrderResp> updateOrder(@PathVariable("orderID") Long orderId,
                                                          @RequestBody @Valid SelectionOrderUpdateReq req,
                                                          BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        SelectionOrderResp order = selectionOrderService.updateOrder(orderId, req);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<SelectionOrderResp> getOrderById(@PathVariable("orderId") Long orderId) {
        SelectionOrderResp order = selectionOrderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{orderId}/report")
    public ResponseEntity<SelectionReportResp> getOrderReportById(@PathVariable("orderId") Long orderId) {
        SelectionReportResp report = selectionOrderService.getOrderReport(orderId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
