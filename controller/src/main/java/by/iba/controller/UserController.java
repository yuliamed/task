package by.iba.controller;

import by.iba.dto.UserUpdate;
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
    
    @PutMapping(value = "/{id}/add-image")//dto
    public ResponseEntity<UserResp> saveImage(@PathVariable Long id, @RequestParam("image") String imageUrl) {
        UserResp resp = userService.saveImage(id, imageUrl);
        return ResponseEntity.ok(resp);
    }
}
