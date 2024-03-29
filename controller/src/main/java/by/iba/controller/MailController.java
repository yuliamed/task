package by.iba.controller;

import by.iba.dto.req.user.EmailReq;
import by.iba.dto.req.user.ResetPassReq;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.user.UserResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/mail")
public class MailController {
    private final UserService userService;

    @PatchMapping("/activate/{token}")
    public ResponseEntity<UserResp> activateAccount(@PathVariable("token") String token) {
        UserResp resp = userService.confirmAccount(token);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/recovery-password")
    public ResponseEntity<ApiResp> recoveryPassword(@RequestBody @Valid EmailReq email, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        ApiResp resp = userService.recoveryPass(email);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<ApiResp> resetPassword(@RequestBody @Valid ResetPassReq resetDto, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        ApiResp resp = userService.resetPass(resetDto);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/activate/{token}")
    public ResponseEntity<UserResp> activateAccountGet(@PathVariable("token") String token) {
        UserResp resp = userService.confirmAccount(token);
        return ResponseEntity.ok(resp);
    }
}
