package com.mdm.project.service;

import com.mdm.project.exception.ResourceNotFoundException;
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


    public UserEntity login(String phone, String password) {

        UserEntity user = userRepository.findByCustomerPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", "Phone number", phone));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user; // Trả về thông tin người dùng thay vì JWT
    }
}