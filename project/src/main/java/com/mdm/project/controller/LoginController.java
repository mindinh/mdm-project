package com.mdm.project.controller;

import com.mdm.project.request.LoginRequest;
import com.mdm.project.entity.UserEntity;
import com.mdm.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping()
    public ResponseEntity<UserEntity> login(@RequestBody LoginRequest request) {
        UserEntity user = loginService.login(request);
        return ResponseEntity.ok(user);
    }
}
