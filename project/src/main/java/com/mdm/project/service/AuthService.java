package com.mdm.project.service;

import com.mdm.project.dto.LoginRequest;
import com.mdm.project.dto.RegisterRequest;
import com.mdm.project.entity.UserCollection;
import com.mdm.project.repository.UserCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCollectionRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCollection register(RegisterRequest request) {
        // Kiểm tra
        if (request.getCustomerId() == null || request.getCustomerId().isEmpty()) {
            throw new RuntimeException("Customer ID is required");
        }
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new RuntimeException("Name is required");
        }
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty() || !request.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new RuntimeException("Valid email is required");
        }

        // Kiểm tra trùng lặp
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }


        // Tạo user
        UserCollection user = new UserCollection();
        user.setCustomerId(request.getCustomerId());
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public UserCollection login(LoginRequest request) {
        // Kiểm tra thủ công
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        UserCollection user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user; // Trả về thông tin người dùng thay vì JWT
    }
}