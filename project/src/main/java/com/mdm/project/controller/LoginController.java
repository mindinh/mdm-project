package com.mdm.project.controller;


import com.mdm.project.entity.UserEntity;
import com.mdm.project.service.LoginService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping()
    public ResponseEntity<UserEntity> login(@RequestParam @NotBlank(message = "Phone number must not be blank") @NotNull(message = "Phone number must not be null")
                                                String phoneNumber,
                                            @RequestParam@NotBlank(message = "Password must not be blank") @NotNull(message = "Password must not be null")
                                                String password) {
        UserEntity user = loginService.login(phoneNumber, password);
        return ResponseEntity.ok(user);
    }
}
