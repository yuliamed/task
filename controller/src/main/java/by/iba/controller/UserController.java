package by.iba.controller;

import by.iba.dto.req.UserUpdateReq;
import by.iba.dto.resp.UserResp;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResp> getProfile(@PathVariable("id") Long id) {
        UserResp user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResp> updateProfile(@PathVariable Long id, @Valid @RequestBody UserUpdateReq userUpdateReq) {
        UserResp user = userService.updateInfo(id, userUpdateReq);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/{id}/add-image")
    public ResponseEntity<UserResp> saveImage(@PathVariable Long id, @RequestParam("image") String imageUrl) {
        UserResp resp = userService.saveImage(id, imageUrl);
        return ResponseEntity.ok(resp);
    }

}
