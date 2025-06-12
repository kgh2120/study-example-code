package com.kk.lock.facade;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.service.BaseAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
@Component
public class ReentrantLockFacade {

    private final BaseAccountService baseAccountService;
    private final ReentrantLock lock = new ReentrantLock();

    public void send(SendRequest request) {
        lock.lock();
        try{
            baseAccountService.send(request);
        }finally {
            lock.unlock();
        }

    }

}
