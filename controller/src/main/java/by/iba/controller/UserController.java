package by.iba.controller;

import by.iba.dto.ResetPassDTO;
import by.iba.dto.UserUpdate;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.UserResp;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserResp> updateProfile(@PathVariable Long id, @RequestBody UserUpdate userUpdate) {
        UserResp user = userService.updateInfo(id, userUpdate);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/{id}/add-image")
    public ResponseEntity<UserResp> saveImage(@PathVariable Long id, @RequestParam("image") String imageUrl) {
        UserResp resp = userService.saveImage(id, imageUrl);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/{id}/recovery-password")
    public ResponseEntity<ApiResp> recoveryPassword(@PathVariable("id") Long id, @RequestParam String email) {
        ApiResp resp = userService.recoveryPass(id, email);

        return ResponseEntity.ok(resp);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<ApiResp> resetPassword(@RequestParam String token, @RequestBody ResetPassDTO resetDto) {
        ApiResp resp = userService.resetPass(token, resetDto);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/activate/{token}")
    public ResponseEntity<UserResp> activateAccount(@PathVariable("token") String token) {
        UserResp resp = userService.confirmAccount(token);
        return ResponseEntity.ok(resp);
    }

}
