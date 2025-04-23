package com.mdm.project.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mdm.project.entity.CartItem;
import com.mdm.project.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get-all-carts")
    public ResponseEntity<?> getAllCarts() throws JsonProcessingException {
        Map<String, Map<String, List<CartItem>>> allCarts = cartService.getAllUserCarts();
        return ResponseEntity.ok(allCarts);
    }

    @GetMapping("/get-cart/{userId}")
    public ResponseEntity<?> getCart(@PathVariable String userId) throws JsonProcessingException {
        return ResponseEntity.ok(cartService.getEntireCart(userId));
    }

    @PostMapping("/add-item/{userId}/{shopName}")
    public ResponseEntity<?> addItemToCart(@PathVariable String userId, @PathVariable String shopName, @RequestBody CartItem cartItem) throws JsonProcessingException {
        cartService.addItemToShopCart(userId, shopName, cartItem);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/delete-item/{userId}/{shopName}/{productId}")
    public ResponseEntity<?> deleteItem(@PathVariable String userId, @PathVariable String shopName, @PathVariable String productId) throws JsonProcessingException {
        cartService.removeItemFromShopCart(userId, shopName, productId);
        return ResponseEntity.ok("Ok");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("OK");
    }

}
