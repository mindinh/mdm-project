package com.mdm.project.service;

import com.mdm.project.request.LoginRequest;
import com.mdm.project.entity.UserEntity;
import com.mdm.project.repository.UserCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserCollectionRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntity login(LoginRequest request) {
        // Kiểm tra thủ công
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user; // Trả về thông tin người dùng thay vì JWT
    }
}