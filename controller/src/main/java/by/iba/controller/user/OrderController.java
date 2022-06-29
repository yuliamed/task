package by.iba.controller.user;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.*;
import by.iba.dto.resp.OrderResp;
import by.iba.dto.resp.SelectionOrderResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.OrderService;
import by.iba.service.SelectionOrderService;
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
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(value = "/api/v1/orders/")
public class OrderController {

    private final SelectionOrderService selectionOrderService;
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<SelectionOrderResp> createSelectionOrder(@RequestBody @Valid SelectionOrderReq orderReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        SelectionOrderResp selectionOrderResp = selectionOrderService.createOrder(orderReq);
        return new ResponseEntity<>(selectionOrderResp, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResp> changeOrderStatus(@PathVariable("id") Long id,
                                                       @RequestBody @Valid OrderStatusReq orderStatusReq,
                                                       BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp order = orderService.changeOrderStatus(id, orderStatusReq);
        return new ResponseEntity<>(order, HttpStatus.OK);
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
    public ResponseEntity<List<OrderResp>> findUsersOrders(@PathVariable("id") Long id) {
        List<OrderResp> orders = orderService.getOrdersByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/auto_picker/{id}")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER')")
    public ResponseEntity<List<OrderResp>> findAutoPickersOrders(@PathVariable("id") Long id) {
        List<OrderResp> orders = orderService.getOrdersByAutoPickerId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<PageWrapper<SelectionOrderResp>> findAllSelectionOrders(@Valid SelectionOrderSearchCriteriaReq searchCriteriaReq,
                                                                                  BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        PageWrapper<SelectionOrderResp> resp = selectionOrderService.findAllOrder(searchCriteriaReq);
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/findAll")
    public ResponseEntity<PageWrapper<OrderResp>> findAllOrders(OrderSearchCriteriaReq param) {
        PageWrapper<OrderResp> resp = orderService.findAll(param);
        return ResponseEntity.ok().body(resp);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<OrderResp> setAutoPicker(@PathVariable("id") Long id, @Valid @RequestBody OrderAutoPickerReq autoPickerReq,
                                                   BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp resp = orderService.setAutoPicker(id, autoPickerReq);
        return ResponseEntity.ok().body(resp);
    }
}
