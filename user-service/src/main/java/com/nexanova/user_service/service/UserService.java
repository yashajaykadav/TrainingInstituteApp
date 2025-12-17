package com.nexanova.user_service.service;

import com.nexanova.user_service.entity.User;
import com.nexanova.user_service.repository.UserRepository;
import com.nexanova.user_service.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean verifyUser(String username , String rawPassword){
        Optional<User> user  = userRepository.findByUsername(username);

        return user.filter(value -> passwordEncoder.matches(rawPassword, value.getPassword())).isPresent();
    }

    public void saveUserFromCsv(MultipartFile file){
        List<User> users = CSVHelper.parseUserService(file);
        for(User user : users){
            String rawPassword = (user.getPassword()==null || user.getPassword().isEmpty())
                    ? "Default@123" : user.getPassword();
            user.setPassword(passwordEncoder.encode(rawPassword));

            if(user.getRole()==null || user.getRole().isEmpty()){
                user.setRole("STUDENT");
            }
        }

        userRepository.saveAll(users);
    }
}
