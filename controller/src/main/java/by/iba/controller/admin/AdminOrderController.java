package by.iba.controller.admin;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.OrderAutoPickerReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.req.order.SelectionOrderSearchCriteriaReq;
import by.iba.dto.resp.OrderResp;
import by.iba.dto.resp.SelectionOrderResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.OrderService;
import by.iba.service.SelectionOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/admin/{adminId}/orders")
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminOrderController {

    private final OrderService orderService;
    private final SelectionOrderService selectionOrderService;

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResp> setAutoPicker(@PathVariable("id") Long id, @Valid @RequestBody OrderAutoPickerReq autoPickerReq,
                                                   BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp resp = orderService.setAutoPicker(id, autoPickerReq);
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping()
    public ResponseEntity<PageWrapper<OrderResp>> findAllOrders(OrderSearchCriteriaReq param) {
        PageWrapper<OrderResp> resp = orderService.findAll(param);
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/selection-orders")
    public ResponseEntity<PageWrapper<SelectionOrderResp>> findAllSelectionOrders(@Valid SelectionOrderSearchCriteriaReq searchCriteriaReq,
                                                                                  BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        PageWrapper<SelectionOrderResp> resp = selectionOrderService.findAllOrder(searchCriteriaReq);
        return ResponseEntity.ok().body(resp);
    }
}
