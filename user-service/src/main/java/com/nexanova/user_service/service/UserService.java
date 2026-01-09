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

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("STUDENT");
        }
        return userRepository.save(user);
    }

    public boolean verifyUser(String username, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.filter(value -> passwordEncoder.matches(rawPassword, value.getPassword())).isPresent();
    }

    public void saveUserFromCsv(MultipartFile file) {
        List<User> users = CSVHelper.parseUserService(file);

        System.out.println("Bulk Upload: Found " + users.size() + " users in CSV file.");

        for (User user : users) {
            System.out.println("Processing user: " + user.getUsername());

            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                System.out.println("Skipping duplicate: " + user.getUsername());
                continue;
            }

            String rawPassword = (user.getPassword() == null || user.getPassword().isEmpty())
                    ? "Default@123"
                    : user.getPassword();
            user.setPassword(passwordEncoder.encode(rawPassword));

            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("STUDENT");
            }

            userRepository.save(user);
        }
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getTrainers() {
        return userRepository.findByRole("TRAINER");
    }

    public List<User> getStudents() {
        return userRepository.findByRole("STUDENT");
    }

    public String getRole(String username) {
        return userRepository.findByUsername(username)
                .map(User::getRole)
                .orElse("STUDENT");
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}