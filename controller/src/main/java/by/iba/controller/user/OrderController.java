package by.iba.controller.user;

import by.iba.dto.req.order.OrderReq;
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

@AllArgsConstructor
@RestController
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(value = "/api/v1/orders/")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResp> signUp(@RequestBody @Valid OrderReq orderReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp orderResp = orderService.createOrder(orderReq);
        return new ResponseEntity<>(orderResp, HttpStatus.CREATED);
    }
}
