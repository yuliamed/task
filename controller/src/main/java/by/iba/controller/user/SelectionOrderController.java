package by.iba.controller.user;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.req.order.SelectionOrderSearchCriteriaReq;
import by.iba.dto.req.order.SelectionOrderUpdateReq;
import by.iba.dto.resp.SelectionOrderResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.SelectionOrderService;
import lombok.AllArgsConstructor;
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
@RequestMapping(value = "/api/v1/selection-orders")
public class SelectionOrderController {
    private final SelectionOrderService selectionOrderService;

    @PostMapping()
    public ResponseEntity<SelectionOrderResp> createSelectionOrder(@RequestBody @Valid SelectionOrderReq orderReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        SelectionOrderResp selectionOrderResp = selectionOrderService.createOrder(orderReq);
        return new ResponseEntity<>(selectionOrderResp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SelectionOrderResp> updateOrder(@PathVariable("id") Long id,
                                                          @RequestBody @Valid SelectionOrderUpdateReq req,
                                                          BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        SelectionOrderResp order = selectionOrderService.updateOrder(id, req);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelectionOrderResp> getOrderById(@PathVariable("id") Long id) {
        SelectionOrderResp order = selectionOrderService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
