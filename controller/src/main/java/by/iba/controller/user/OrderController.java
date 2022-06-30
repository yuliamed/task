package by.iba.controller.user;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.OrderAutoPickerReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.req.order.OrderStatusReq;
import by.iba.dto.resp.OrderResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.OrderService;
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
@RequestMapping(value = "/api/v1/orders/")
public class OrderController {

    private final OrderService orderService;

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN','USER')")
    public ResponseEntity<OrderResp> changeOrderStatus(@PathVariable("id") Long id,
                                                       @RequestBody @Valid OrderStatusReq orderStatusReq,
                                                       BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp order = orderService.changeOrderStatus(id, orderStatusReq);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN','USER')")
    public ResponseEntity<List<OrderResp>> findUsersOrders(@PathVariable("id") Long id) {
        List<OrderResp> orders = orderService.getOrdersByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/auto_picker/{id}")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN')")
    public ResponseEntity<List<OrderResp>> findAutoPickersOrders(@PathVariable("id") Long id) {
        List<OrderResp> orders = orderService.getOrdersByAutoPickerId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN')")
    public ResponseEntity<PageWrapper<OrderResp>> findAllOrders(OrderSearchCriteriaReq param) {
        PageWrapper<OrderResp> resp = orderService.findAll(param);
        return ResponseEntity.ok().body(resp);
    }

    @PatchMapping("/admin/{id}")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN')")
    public ResponseEntity<OrderResp> setAutoPicker(@PathVariable("id") Long id, @Valid @RequestBody OrderAutoPickerReq autoPickerReq,
                                                   BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp resp = orderService.setAutoPicker(id, autoPickerReq);
        return ResponseEntity.ok().body(resp);
    }
}
