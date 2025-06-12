package com.kk.lock.controller;

import com.kk.lock.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody SendRequest request) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String accountNumber) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/transaction/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionLog(@PathVariable String accountNumber) {

        return ResponseEntity.ok().build();
    }

}
