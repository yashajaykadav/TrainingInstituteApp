package com.nexanova.user_service.controller;


import com.nexanova.user_service.entity.User;
import com.nexanova.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/bulk-upload")
    public String uploadBulkUser(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "Please Upload Valid File";
        }
        userService.saveUserFromCsv(file);
        return "Users Uploaded and registered successfully";
    }

    // Check if you have this method. If not, ADD IT inside the class:
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
