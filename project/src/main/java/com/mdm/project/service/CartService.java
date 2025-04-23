package com.mdm.project.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdm.project.entity.CartItem;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, String> hashOps;
    private final ObjectMapper objectMapper;

    public CartService(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.hashOps = redisTemplate.opsForHash();
        this.objectMapper = objectMapper;
    }

    private String getCartKey(String userId) {
        return "cart:" + userId;
    }

    public Map<String, Map<String, List<CartItem>>> getAllUserCarts() throws JsonProcessingException {
        Set<String> keys = redisTemplate.keys("cart:*");
        Map<String, Map<String, List<CartItem>>> allCarts = new HashMap<>();

        if (keys == null) return allCarts;

        for (String key : keys) {
            String userId = key.replace("cart:", "");
            Map<String, String> raw = hashOps.entries(key);
            Map<String, List<CartItem>> userCart = new HashMap<>();

            for (Map.Entry<String, String> entry : raw.entrySet()) {
                List<CartItem> items = objectMapper.readValue(entry.getValue(), new TypeReference<List<CartItem>>() {});
                userCart.put(entry.getKey(), items);
            }

            allCarts.put(userId, userCart);
        }

        return allCarts;
    }


    public void addItemToShopCart(String userId, String shopName, CartItem newItem) throws JsonProcessingException {
        String cartKey = getCartKey(userId);
        List<CartItem> items = new ArrayList<>();

        try {
            String shopCartJson = hashOps.get(cartKey, shopName);
            if (shopCartJson != null && !shopCartJson.isBlank()) {
                items = objectMapper.readValue(shopCartJson, new TypeReference<List<CartItem>>() {});
            }
        } catch (Exception e) {
            // Optional: log it
            System.err.println("Failed to read cart JSON: " + e.getMessage());
        }

        boolean found = false;
        for (CartItem item : items) {
            if (item.getProductId().equals(newItem.getProductId())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                found = true;
                break;
            }
        }

        if (!found) {
            items.add(newItem);
        }

        String updatedJson = objectMapper.writeValueAsString(items);
        hashOps.put(cartKey, shopName, updatedJson);
    }


    public Map<String, List<CartItem>> getEntireCart(String userId) throws JsonProcessingException {
        String cartKey = getCartKey(userId);
        Map<String, String> raw = hashOps.entries(cartKey);
        Map<String, List<CartItem>> result = new HashMap<>();

        for (Map.Entry<String, String> entry : raw.entrySet()) {
            List<CartItem> items = objectMapper.readValue(entry.getValue(), new TypeReference<List<CartItem>>() {});
            result.put(entry.getKey(), items);
        }

        return result;
    }

    public void removeItemFromShopCart(String userId, String shopName, String productId) throws JsonProcessingException {
        String cartKey = getCartKey(userId);
        String shopCartJson = hashOps.get(cartKey, shopName);

        if (shopCartJson == null) return;

        List<CartItem> items = objectMapper.readValue(shopCartJson, new TypeReference<List<CartItem>>() {});
        items.removeIf(item -> item.getProductId().equals(productId));

        if (items.isEmpty()) {
            hashOps.delete(cartKey, shopName);
        } else {
            String updatedJson = objectMapper.writeValueAsString(items);
            hashOps.put(cartKey, shopName, updatedJson);
        }

        // Optional: remove entire cart if no shops left
        if (hashOps.size(cartKey) == 0) {
            redisTemplate.delete(cartKey);
        }
    }

    public List<CartItem> getItemsFromShop(String userId, String shopName) throws JsonProcessingException {
        String cartKey = getCartKey(userId);
        String shopCartJson = hashOps.get(cartKey, shopName);
        if (shopCartJson == null) return Collections.emptyList();

        return objectMapper.readValue(shopCartJson, new TypeReference<List<CartItem>>() {});
    }


    public void clearCart(String userId) {
        redisTemplate.delete(getCartKey(userId));
    }

}
