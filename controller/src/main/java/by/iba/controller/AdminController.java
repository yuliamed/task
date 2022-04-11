package by.iba.controller;

import by.iba.dto.req.RoleReq;
import by.iba.dto.req.UserBanReq;
import by.iba.dto.resp.UserResp;
import by.iba.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/admin/")
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    private final AdminService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserResp>> findAll() {
        List<UserResp> resp = service.findAll();
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<UserResp> banUser(@PathVariable("id") Long id, @RequestBody UserBanReq userBanReq) {
        UserResp user = service.banUser(id, userBanReq);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResp> changeUserRole(@PathVariable("id") Long id, @RequestBody RoleReq role) {
        UserResp resp = service.changeUserRole(id, role);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/users/approve/{id}")
    public ResponseEntity<UserResp> approveUser(@PathVariable("id") Long id) {
        UserResp user = service.approveUser(id);
        return ResponseEntity.ok(user);
    }
}
