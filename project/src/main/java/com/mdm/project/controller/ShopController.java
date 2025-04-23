package com.mdm.project.controller;


import com.mdm.project.dto.ShopDto;
import com.mdm.project.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/get-shop/{userid}")
    public ResponseEntity<?> getShopByUserId(@PathVariable String userid) {
        ShopDto shopDto = shopService.findByOwnerId(userid);

        return ResponseEntity.ok(shopDto);
    }
}
