package by.iba.controller;

import by.iba.dto.req.user.SignInReq;
import by.iba.dto.req.user.SignUpReq;
import by.iba.dto.resp.user.UserResp;
import by.iba.exception.ControllerHelper;
import by.iba.inteface.car_description.BodyRepository;
import by.iba.security.dto.JwtResp;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final BodyRepository repository;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResp> signIn(@RequestBody @Valid SignInReq request, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        JwtResp resp = userService.signIn(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResp> signUp(@Valid @RequestBody SignUpReq userReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        UserResp userResp = userService.signUp(userReq);
        return new ResponseEntity<>(userResp, HttpStatus.CREATED);
    }

}
