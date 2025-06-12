package com.kk.lock.service;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class ExclusiveLockAccountService {

    private final AccountRepository accountRepository;

    public void sendWithPessimisticLock(SendRequest request){
        Account sender = accountRepository.findByIdWithPessimisticWrite(request.senderAccountNumber())
                .orElseThrow();

        Account receiver = accountRepository.findByIdWithPessimisticWrite(request.receiverAccountNumber())
                .orElseThrow();


        long amount = request.amount();

        sender.withdraw(amount);
        receiver.deposit(amount);

        accountRepository.saveAndFlush(sender);
        accountRepository.saveAndFlush(receiver);
    }

    public void sendWithNativeQueryLock(SendRequest request){
        Account sender = accountRepository.findByIdWithNativeQueryLock(request.senderAccountNumber())
                .orElseThrow();

        Account receiver = accountRepository.findByIdWithNativeQueryLock(request.receiverAccountNumber())
                .orElseThrow();


        long amount = request.amount();

        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    public void updateAccountOwnerName(String accountNumber){
        Account account = accountRepository.findByIdWithPessimisticWrite(accountNumber).get();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        account.updateAccountOwnerName(UUID.randomUUID().toString());
//        accountRepository.saveAndFlush(account);
    }

}
