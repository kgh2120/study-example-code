package com.kk.lock.service;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.ReentrantLock;

@Transactional
@RequiredArgsConstructor
@Service
public class ReentrantLockAccountService {

    private final AccountRepository accountRepository;
    private final ReentrantLock lock = new ReentrantLock();

    public void send(SendRequest request){
        lock.lock();
        try{
            Account sender = accountRepository.findById(request.senderAccountNumber())
                    .orElseThrow();

            Account receiver = accountRepository.findById(request.receiverAccountNumber())
                    .orElseThrow();


            long amount = request.amount();

            sender.withdraw(amount);
            receiver.deposit(amount);
        } finally {
            lock.unlock();
        }

    }
}
