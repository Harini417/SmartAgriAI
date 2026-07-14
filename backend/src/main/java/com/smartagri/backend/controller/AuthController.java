package com.smartagri.backend.controller;

import com.smartagri.backend.entity.User;
import com.smartagri.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        try {
            User savedUser = userService.register(user);
            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {

        try {
            User user = userService.login(
                    loginRequest.getEmail(),
                    loginRequest.getPassword());

            return ResponseEntity.ok(user);

        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    // Change Password
    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> changePassword(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        try {

            User updatedUser = userService.changePassword(
                    id,
                    request.get("oldPassword"),
                    request.get("newPassword"));

            return ResponseEntity.ok(updatedUser);

        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}