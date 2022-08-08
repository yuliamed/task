package by.iba.controller.user;

import by.iba.dto.req.order.OrderStatusReq;
import by.iba.dto.resp.order.OrderResp;
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

@RestController
@RequestMapping(value = "api/v1/users/{id}/orders")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
public class UserOrdersController {
    private final OrderService orderService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN','USER')")
    public ResponseEntity<List<OrderResp>> findUsersOrders(@PathVariable("id") Long id) {
        List<OrderResp> orders = orderService.getOrdersByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN','USER')")
    public ResponseEntity<OrderResp> changeOrderStatus(@PathVariable("orderId") Long id,
                                                       @RequestBody @Valid OrderStatusReq orderStatusReq,
                                                       BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp order = orderService.changeOrderStatus(id, orderStatusReq);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
