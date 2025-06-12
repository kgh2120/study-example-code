package com.kk.lock.service;

import com.kk.lock.controller.BalanceResponse;
import com.kk.lock.controller.SendRequest;
import com.kk.lock.controller.TransactionResponse;
import com.kk.lock.repository.AccountRepository;
import com.kk.lock.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionLogRepository transactionLogRepository;

    public void send(SendRequest request){

    }

    public BalanceResponse getBalance(String accountNumber){
        return null;
    }

    public List<TransactionResponse> getTransactions(String accountNumber){
        return null;
    }
}
