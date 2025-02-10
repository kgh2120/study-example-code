package org.kk.cachesync.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.kk.cachesync.dto.MessageDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisMessagePublisher {

    private final RedisTemplate<String,Object> redisTemplate;

    public void publishMessage(MessageDto messageDto){

        try {
            String json = new ObjectMapper().writeValueAsString(messageDto);
            redisTemplate.convertAndSend("test", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
