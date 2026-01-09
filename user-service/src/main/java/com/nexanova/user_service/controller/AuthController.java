package com.nexanova.user_service.controller;

import com.nexanova.user_service.dto.LoginRequest;
import com.nexanova.user_service.entity.User;
import com.nexanova.user_service.service.UserService;
import com.nexanova.user_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = userService.verifyUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (isValid) {
            // FIXED: Fetch full user object to get ID
            User user = userService.getUserDetails(loginRequest.getUsername());

            // FIXED: Pass ID to generateToken
            return jwtUtil.generateToken(user.getUsername(), user.getRole(), user.getId());
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String username) {
        return "Reset link sent to " + username;
    }
}