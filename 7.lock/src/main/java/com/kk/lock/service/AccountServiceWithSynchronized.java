package com.kk.lock.service;

import com.kk.lock.controller.BalanceResponse;
import com.kk.lock.controller.SendRequest;
import com.kk.lock.controller.TransactionResponse;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import com.kk.lock.repository.TransactionLogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class AccountServiceWithSynchronized {

    // TODO Bean Scope 변경해서 동작하는거 확인하기
    // TODO 멀티 인스턴스에서 동기화 깨지는거 확인하기

    private final AccountRepository accountRepository;
    private final TransactionLogRepository transactionLogRepository;


    public void send(SendRequest request){
        Account sender = accountRepository.findById(request.senderAccountNumber())
                .orElseThrow();

        Account receiver = accountRepository.findById(request.receiverAccountNumber())
                .orElseThrow();


        long amount = request.amount();

        sender.withdraw(amount);
        receiver.deposit(amount);

        accountRepository.saveAndFlush(sender);
        accountRepository.saveAndFlush(receiver);
    }

    public BalanceResponse getBalance(String accountNumber){
        return null;
    }

    public List<TransactionResponse> getTransactions(String accountNumber){
        return null;
    }
}
