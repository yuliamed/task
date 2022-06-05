package by.iba.controller.user;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.OrderStatusReq;
import by.iba.dto.req.UserSearchCriteriaReq;
import by.iba.dto.req.order.OrderReq;
import by.iba.dto.req.order.OrderSearchCriteriaReq;
import by.iba.dto.resp.OrderResp;
import by.iba.dto.resp.UserResp;
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

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResp> changeOrderStatus(@PathVariable("id") Long id,
                                                       @RequestBody @Valid OrderStatusReq orderStatusReq,
                                                       BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        OrderResp order = orderService.changeOrderStatus(id, orderStatusReq);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //    public ResponseEntity<List<OrderResp>> findAllOrders(@Valid OrderSearchCriteriaReq orderSearchCriteriaReq,
//                                                         BindingResult result) {
//
//    }
    @GetMapping("/{id}")
    public ResponseEntity<List<OrderResp>> findUsersOrders(@PathVariable("id") Long id){
        List<OrderResp> orders= orderService.getUsersOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/auto_picker/{id}")
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER')")
    public ResponseEntity<List<OrderResp>> findAutoPickersOrders(@PathVariable("id") Long id){
        List<OrderResp> orders= orderService.getAutoPickersOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<PageWrapper<OrderResp>> findAllOrders(@Valid OrderSearchCriteriaReq searchCriteriaReq,
                                                         BindingResult result){
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        PageWrapper<OrderResp> resp = orderService.findAll(searchCriteriaReq);
        return ResponseEntity.ok().body(resp);
    }
}
