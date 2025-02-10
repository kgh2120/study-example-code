package org.kk.cachesync.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kk.cachesync.dto.MessageDto;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class RedisSubListener implements MessageListener {

    private final RedisTemplate<String,Object> redisTemplate;
    @Override
    public void onMessage(Message message, byte[] pattern) {

        String body = redisTemplate.getStringSerializer().deserialize(message.getBody());

        try {
            MessageDto messageDto = new ObjectMapper().readValue(body, MessageDto.class);
            log.info("message arrive! : {}", messageDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
