package by.iba.controller;

import by.iba.dto.req.ImageReq;
import by.iba.dto.req.UserUpdateReq;
import by.iba.dto.resp.UserResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<UserResp> getProfile() {
        UserResp user = userService.getProfile();
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "")
    public ResponseEntity<UserResp> updateProfile(@Valid @RequestBody UserUpdateReq userUpdateReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        UserResp user = userService.updateInfo(userUpdateReq);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/add-image")
    public ResponseEntity<UserResp> saveImage(@RequestBody ImageReq imageUrl) {
        UserResp resp = userService.saveImage(imageUrl);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping(value = "/delete-image")
    public ResponseEntity<UserResp> deleteImage() {
        UserResp resp = userService.deleteImage();
        return ResponseEntity.ok(resp);
    }

}
