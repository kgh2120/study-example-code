package com.kk.lock.facade;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.service.BaseAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SynchronizedFacade {

    private final BaseAccountService baseAccountService;

    public synchronized void send(SendRequest request) {
        baseAccountService.send(request);
    }

}
