package org.kk.cachesync.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.kk.cachesync.dto.CacheEvictMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisMessagePublisher {

    private final RedisTemplate<String,Object> redisTemplate;

    public void publishCacheEvictMessage(CacheEvictMessage cacheEvictmessage){

        try {
            String json = new ObjectMapper().writeValueAsString(cacheEvictmessage);
            redisTemplate.convertAndSend("cache-sync", json);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
