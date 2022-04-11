package by.iba.controller;

import by.iba.dto.req.EmailReq;
import by.iba.dto.req.ResetPassReq;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.UserResp;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
    private final UserService userService;

    @PutMapping("/activate/{token}")
    public ResponseEntity<UserResp> activateAccount(@PathVariable("token") String token) {
        UserResp resp = userService.confirmAccount(token);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/recovery-password")
    public ResponseEntity<ApiResp> recoveryPassword(@RequestBody EmailReq email) {
        ApiResp resp = userService.recoveryPass(email);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<ApiResp> resetPassword(@RequestParam("token") String token, @RequestBody ResetPassReq resetDto) {
        ApiResp resp = userService.resetPass(token, resetDto);
        return ResponseEntity.ok(resp);
    }
}