package com.mdm.project.controller;

import com.mdm.project.dto.LoginRequest;
import com.mdm.project.dto.RegisterRequest;
import com.mdm.project.entity.UserEntity;
import com.mdm.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterRequest request) {
        UserEntity user = authService.register(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody LoginRequest request) {
        UserEntity user = authService.login(request);
        return ResponseEntity.ok(user);
    }
}
