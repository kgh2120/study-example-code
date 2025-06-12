package com.kk.lock.facade;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.service.AccountServiceWithSynchronized;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SynchronizedFacade {

    private final AccountServiceWithSynchronized aSynchronized;

    public synchronized void send(SendRequest request) {
        aSynchronized.send(request);
    }

}
