package com.kk.lock.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class LettuceLockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public Boolean getLock(String name){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.setIfAbsent(generateKey(name),name,1000, TimeUnit.MILLISECONDS);
    }


    public Boolean releaseLock(String name) {
        return redisTemplate.delete(generateKey(name));
    }


    private String generateKey(String functionName, Object... key) {
        StringBuilder sb = new StringBuilder();
        sb.append("LOCK::").append(functionName).append("::");
        for (Object k : key) {
            sb.append(k.toString());
        }
        return sb.toString();
    }

}
