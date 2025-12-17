package com.nexanova.user_service.controller;


import com.nexanova.user_service.entity.User;
import com.nexanova.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nexanova.user_service.dto.LoginRequest;
import com.nexanova.user_service.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        userService.registerUser(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){

        boolean isValid  = userService.verifyUser(loginRequest.getUsername(),loginRequest.getPassword());

        if(isValid){
            return jwtUtil.generateToken(loginRequest.getUsername());
        }else{
            throw new RuntimeException("Invalid username or password");
        }
    }
}
