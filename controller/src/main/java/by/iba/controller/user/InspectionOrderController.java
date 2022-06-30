package by.iba.controller.user;

import by.iba.dto.req.order.*;
import by.iba.dto.resp.InspectionOrderResp;
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
@RequestMapping(value = "/api/v1/orders/inspection/")
public class InspectionOrderController {
    private final InspectionOrderService inspectionOrderService;

    @PostMapping("/new")
    public ResponseEntity<InspectionOrderResp> createInspectionOrder(@RequestBody @Valid InspectionOrderReq orderReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionOrderResp inspectionOrder = inspectionOrderService.createInspectionOrder(orderReq);
        return new ResponseEntity<>(inspectionOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InspectionOrderResp> updateOrder(@PathVariable("id") Long id,
                                                          @RequestBody @Valid InspectionOrderUpdateReq req,
                                                          BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        InspectionOrderResp order = inspectionOrderService.updateInspectionOrder(id, req);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspectionOrderResp> getOrder(@PathVariable("id") Long id) {
        InspectionOrderResp order = inspectionOrderService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


//    @GetMapping("/")
//    public ResponseEntity<PageWrapper<InspectionOrderResp>> findAllSelectionOrders(@Valid SelectionOrderSearchCriteriaReq searchCriteriaReq,
//                                                                                  BindingResult result) {
//        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
//        PageWrapper<SelectionOrderResp> resp = selectionOrderService.findAllOrder(searchCriteriaReq);
//        return ResponseEntity.ok().body(resp);
//    }
}
