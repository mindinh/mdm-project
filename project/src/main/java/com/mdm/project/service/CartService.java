package com.mdm.project.service;


import com.mdm.project.entity.CartItem;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, CartItem> hashOps;

    public CartService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOps = redisTemplate.opsForHash();
    }

    private String getCartKey(String userId) {
        return "cart:" + userId;
    }

    public Map<String, Map<String, CartItem>> getAllCarts() {
        Set<String> cartKeys = redisTemplate.keys("cart:*");

        if (cartKeys == null || cartKeys.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Map<String, CartItem>> allCarts = new HashMap<>();

        for (String key : cartKeys) {
            Map<String, CartItem> cart = hashOps.entries(key);
            allCarts.put(key, cart);
        }

        return allCarts;
    }


    public void addItemToCart(String userId, CartItem item) {
        hashOps.put(getCartKey(userId), item.getProductId().toString(), item);
    }

    public CartItem getItemFromCart(String userId, Long productId) {
        return hashOps.get(getCartKey(userId), productId.toString());
    }

    public Map<String, CartItem> getAllItems(String userId) {
        return hashOps.entries(getCartKey(userId));
    }

    public void deleteCartItem(String userId, Long productId) {
        hashOps.delete(getCartKey(userId), productId.toString());
    }

    public void deleteCart(String userId) {
        redisTemplate.delete(getCartKey(userId));
    }

}
