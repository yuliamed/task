package by.iba.controller.user;

import by.iba.dto.req.ImageReq;
import by.iba.dto.req.user.ChangePassDto;
import by.iba.dto.req.user.ResetPassReq;
import by.iba.dto.req.user.UserUpdateReq;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.user.UserResp;
import by.iba.exception.ControllerHelper;
import by.iba.service.UserService;
import by.iba.service.impl.StorageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Base64;

@AllArgsConstructor
@RestController
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(value = "/api/v1/users/{id}")
public class UserController {
    private final UserService userService;
    private final StorageServiceImpl storageService;

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

    @PatchMapping(value = "/image")
    public ResponseEntity<UserResp> saveImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        UserResp resp = userService.saveImage(id, file);
        return ResponseEntity.ok(resp);
    }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable("id") Long id) {
        byte[] resp = userService.getUserPhoto(id);
        //ByteArrayResource resource = new ByteArrayResource(resp);

        return ResponseEntity.ok().contentLength(resp.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + "file.png"  + "\"")
                .body(Base64.getEncoder().encode(resp));
    }

    @DeleteMapping(value = "/image")
    public ResponseEntity<UserResp> deleteImage(@PathVariable("id") Long id) {
        UserResp resp = userService.deleteImage(id);
        return ResponseEntity.ok(resp);
    }

}
