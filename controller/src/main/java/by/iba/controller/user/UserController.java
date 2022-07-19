package by.iba.controller.user;

import by.iba.dto.req.ImageReq;
import by.iba.dto.req.user.UserUpdateReq;
import by.iba.dto.resp.user.UserResp;
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
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(value = "/api/v1/users/{id}")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserResp> getProfile(@PathVariable("id") Long id) {
        UserResp user = userService.getProfile(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping()
    public ResponseEntity<UserResp> updateProfile(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateReq userUpdateReq, BindingResult result) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(result);
        UserResp user = userService.updateInfo(id, userUpdateReq);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/add-image")
    public ResponseEntity<UserResp> saveImage(@PathVariable("id") Long id, @RequestBody ImageReq imageUrl) {
        UserResp resp = userService.saveImage(id, imageUrl);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping(value = "/delete-image")
    public ResponseEntity<UserResp> deleteImage(@PathVariable("id") Long id) {
        UserResp resp = userService.deleteImage(id);
        return ResponseEntity.ok(resp);
    }

}
