package com.nexanova.user_service.controller;


import com.nexanova.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

}
