package org.kk.cachesync.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kk.cachesync.dto.CacheEvictMessage;
import org.kk.cachesync.service.CacheEvictService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@RequiredArgsConstructor
public class RedisSubListener implements MessageListener {

    private final RedisTemplate<String,Object> redisTemplate;
    private final CacheEvictService cacheEvictService;
    @Override
    public void onMessage(Message message, byte[] pattern) {

        String body = redisTemplate.getStringSerializer().deserialize(message.getBody());

        try {
            CacheEvictMessage cacheEvictmessage = new ObjectMapper().readValue(body, CacheEvictMessage.class);
            cacheEvictService.evictCache(cacheEvictmessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
