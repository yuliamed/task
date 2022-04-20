package by.iba.controller;

import by.iba.dto.req.SignInReq;
import by.iba.dto.req.SignUpReq;
import by.iba.dto.resp.UserResp;
import by.iba.exception.ControllerHelper;
import by.iba.security.dto.JwtResp;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "api/v1/auth/")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResp> signIn(@RequestBody @Valid SignInReq request, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        JwtResp resp = userService.signIn(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResp> signUp(@Valid @RequestBody SignUpReq userReq, BindingResult result) {
        UserResp userResp = userService.signUp(userReq);
        return new ResponseEntity<>(userResp, HttpStatus.CREATED);
    }

}
