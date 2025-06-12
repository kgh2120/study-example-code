package com.kk.lock.service;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class OptimisticLockAccountService {


    private final AccountRepository accountRepository;
    private final EntityManager entityManager;

    public void sendWithDataJpaOptimisticLock(SendRequest request){
        Account sender = accountRepository.findByIdWithOptimisticLock(request.senderAccountNumber())
                .orElseThrow();

        Account receiver = accountRepository.findByIdWithOptimisticLock(request.receiverAccountNumber())
                .orElseThrow();


        long amount = request.amount();

        sender.withdraw(amount);
        receiver.deposit(amount);


    }

    public void sendWithEntityManagerOptimisticLock(SendRequest request){
        Account sender = entityManager.createQuery("select a from Account a where a.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber", request.senderAccountNumber())
                .setLockMode(LockModeType.OPTIMISTIC)
                .getSingleResult();

        Account receiver = entityManager.createQuery("select a from Account a where a.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber", request.receiverAccountNumber())
                .setLockMode(LockModeType.OPTIMISTIC)
                .getSingleResult();


        long amount = request.amount();

        sender.withdraw(amount);
        receiver.deposit(amount);

        entityManager.flush();
    }
}
