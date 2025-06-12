package com.kk.lock.facade;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.service.OptimisticLockAccountService;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OptimisticLockFacade {

    private final OptimisticLockAccountService optimisticLockAccountService;

    public void sendWithDataJpaOptimisticLock(SendRequest request){
        while (true) {
            try{
                optimisticLockAccountService.sendWithDataJpaOptimisticLock(request);
                break;
            }catch (ObjectOptimisticLockingFailureException e){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    public void sendWithEntityManagerOptimisticLock(SendRequest request){
        while (true) {
            try{
                optimisticLockAccountService.sendWithEntityManagerOptimisticLock(request);
                break;
            }catch (OptimisticLockException e){

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
