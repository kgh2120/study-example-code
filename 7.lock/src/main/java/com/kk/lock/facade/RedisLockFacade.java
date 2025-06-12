package com.kk.lock.facade;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.repository.LettuceLockRepository;
import com.kk.lock.service.BaseAccountService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RedisLockFacade {

    private final RedissonClient redisson;
    private final LettuceLockRepository lettuceLockRepository;
    private final BaseAccountService accountService;

    public void sendWithLettuceLock(SendRequest request) {

        while (!lettuceLockRepository.getLock("send")) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            accountService.send(request);
        } finally {
            lettuceLockRepository.releaseLock("send");
        }
    }

    public void sendWithRedissonLock(SendRequest request) {
        RLock rLock = redisson.getLock("send");

        try {
            boolean available = rLock.tryLock(5L, 3L, TimeUnit.SECONDS);  // (2)
            if (!available) {
                return;
            }
            accountService.send(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rLock.unlock();
        }

    }

}
