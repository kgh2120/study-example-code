package org.kk.cachesync.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        log.info("message arrive! : {}", body);

    }
}
