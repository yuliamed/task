package by.iba.controller;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.RoleReq;
import by.iba.dto.req.UserBanReq;
import by.iba.dto.req.UserSearchCriteriaReq;
import by.iba.dto.resp.UserResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/admin/")
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    private final AdminService service;

    @GetMapping("/users")
    public ResponseEntity<PageWrapper<UserResp>> findAll(@Valid UserSearchCriteriaReq userSearchCriteriaReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        PageWrapper<UserResp> resp = service.findAll(userSearchCriteriaReq);
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<UserResp> banUser(@PathVariable("id") Long id, @RequestBody @Valid UserBanReq userBanReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        UserResp user = service.banUser(id, userBanReq);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResp> addUserRole(@PathVariable("id") Long id, @RequestBody @Valid RoleReq role, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        UserResp resp = service.addUserRole(id, role);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/users/approve/{id}")
    public ResponseEntity<UserResp> approveUser(@PathVariable("id") Long id) {
        UserResp user = service.approveUser(id);
        return ResponseEntity.ok(user);
    }
}
