package com.kk.lock.controller;

import com.kk.lock.facade.SynchronizedFacade;
import com.kk.lock.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;
    private final SynchronizedFacade synchronizedFacade;
    private final Environment environment;


    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody SendRequest request) {
        log.info("Send request: {}", request);
        synchronizedFacade.send(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().body(environment.getProperty("server.port"));
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String accountNumber) {

        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }

    @GetMapping("/transaction/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionLog(@PathVariable String accountNumber) {

        return ResponseEntity.ok().build();
    }

}
