package com.kk.lock.service;

import com.kk.lock.controller.BalanceResponse;
import com.kk.lock.controller.SendRequest;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class BaseAccountService {

    private final AccountRepository accountRepository;

    public void send(SendRequest request){
        Account sender = accountRepository.findById(request.senderAccountNumber())
                .orElseThrow();

        Account receiver = accountRepository.findById(request.receiverAccountNumber())
                .orElseThrow();


        long amount = request.amount();

        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    public BalanceResponse getBalance(String accountNumber){
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow();
        return new BalanceResponse(account.getBalance());
    }

}
