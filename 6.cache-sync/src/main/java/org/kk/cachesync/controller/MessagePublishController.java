package org.kk.cachesync.controller;

import lombok.RequiredArgsConstructor;
import org.kk.cachesync.dto.CacheEvictMessage;
import org.kk.cachesync.service.RedisMessagePublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MessagePublishController {

    private final RedisMessagePublisher messagePublisher;

    @PostMapping("/send")
    public void sendMessage(@RequestBody CacheEvictMessage cacheEvictmessage){
        messagePublisher.publishCacheEvictMessage(cacheEvictmessage);
    }
}
