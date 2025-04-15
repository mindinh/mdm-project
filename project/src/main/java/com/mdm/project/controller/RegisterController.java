package com.mdm.project.controller;


import com.mdm.project.dto.ResponseDto;
import com.mdm.project.request.RegisterRequest;
import com.mdm.project.request.ShopRegisterRequest;
import com.mdm.project.service.RegisterService;
import com.mdm.project.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/register")
public class RegisterController {

    private final RegisterService registerService;
    private final ShopService shopService;
    public RegisterController(RegisterService registerService, ShopService shopService) {
        this.registerService = registerService;
        this.shopService = shopService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        registerService.register(registerRequest);

        return ResponseEntity.ok(new ResponseDto("200", "Register successfully"));
    }

    @PostMapping("/shop")
    public ResponseEntity<?> registerShop(@Valid @RequestBody ShopRegisterRequest registerRequest) {
        shopService.insertShop(registerRequest);
        return ResponseEntity.ok(new ResponseDto("200", "Shop Registered successfully"));
    }
}
