package com.mdm.project.controller;


import com.mdm.project.dto.ResponseDto;
import com.mdm.project.request.RegisterRequest;
import com.mdm.project.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        registerService.register(registerRequest);

        return ResponseEntity.ok(new ResponseDto("200", "Register successfully"));
    }

}
