package com.mdm.project.controller;


import com.mdm.project.entity.CartItem;
import com.mdm.project.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get-all-carts")
    public ResponseEntity<?> getAllCarts() {
        Map<String, Map<String, CartItem>> allCarts = cartService.getAllCarts();
        return ResponseEntity.ok(allCarts);
    }

    @GetMapping("/get-cart/{userId}")
    public ResponseEntity<?> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getAllItems(userId));
    }

    @PostMapping("/add-item/{userId}")
    public ResponseEntity<?> addItemToCart(@PathVariable String userId, @RequestBody CartItem cartItem) {
        cartService.addItemToCart(userId, cartItem);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/delete-item/{userId}/{productId}")
    public ResponseEntity<?> deleteItem(@PathVariable String userId, @PathVariable Long productId) {
        cartService.deleteCartItem(userId, productId);
        return ResponseEntity.ok("Ok");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteCart(@PathVariable String userId) {
        cartService.deleteCart(userId);
        return ResponseEntity.ok("OK");
    }

}
