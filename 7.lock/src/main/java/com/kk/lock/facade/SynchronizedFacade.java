package com.kk.lock.facade;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.service.AccountService;
import com.kk.lock.service.AccountServiceWithSynchronized;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SynchronizedFacade {

    private final AccountService accountService;

    public synchronized void send(SendRequest request) {
        accountService.send(request);
    }

}
