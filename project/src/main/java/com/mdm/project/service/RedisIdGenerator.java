package com.mdm.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisIdGenerator {
    private final String REDIS_PREFIX = "idgen:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getNextIdWithPrefix(String key, String prefix) {
        String redisKey = REDIS_PREFIX + key;
        Long nextId = redisTemplate.opsForValue().increment(redisKey);

        if (nextId == null) {
            throw new IllegalStateException("Failed to generate ID from Redis for key: " + key);
        }

        return prefix + nextId;
    }

}
