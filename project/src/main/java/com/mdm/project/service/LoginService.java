package com.mdm.project.service;

import com.mdm.project.exception.ResourceNotFoundException;
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

        UserEntity user = userRepository.findByCustomerPhone(request.phoneNumber())
                .orElseThrow(() -> new ResourceNotFoundException("User not found", "Phone number", request.phoneNumber()));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user; // Trả về thông tin người dùng thay vì JWT
    }
}