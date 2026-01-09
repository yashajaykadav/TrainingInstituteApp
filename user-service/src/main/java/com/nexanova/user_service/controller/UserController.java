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
    public String uploadBulkUser(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return "Invalid File";
        userService.saveUserFromCsv(file);
        return "Bulk upload successful";
    }

    // Rule: Admin needs to see list of Trainers
    @GetMapping("/trainers")
    public List<User> getTrainers() {
        return userService.getTrainers();
    }

    // Rule: Admin needs to see list of Students
    @GetMapping("/students")
    public List<User> getStudents() {
        return userService.getStudents();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}