package by.iba.controller.auto_picker;

import by.iba.dto.resp.order.OrderResp;
import by.iba.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/auto-picker/{id}/orders")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasAnyAuthority('AUTO_PICKER')")
public class AutoPickerOrderController {

    private final OrderService orderService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('AUTO_PICKER', 'ADMIN')")
    public ResponseEntity<List<OrderResp>> findAutoPickersOrders(@PathVariable("id") Long id) {
        List<OrderResp> orders = orderService.getOrdersByAutoPickerId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
