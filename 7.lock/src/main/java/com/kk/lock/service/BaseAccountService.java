package com.kk.lock.service;

import com.kk.lock.controller.BalanceResponse;
import com.kk.lock.controller.SendRequest;
import com.kk.lock.controller.TransactionResponse;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import com.kk.lock.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class BaseAccountService {

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
    }

    public BalanceResponse getBalance(String accountNumber){
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow();
        return new BalanceResponse(account.getBalance());
    }

    public List<TransactionResponse> getTransactions(String accountNumber){
        return null;
    }
}
