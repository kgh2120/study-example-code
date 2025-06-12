package com.kk.lock.service;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    String senderAccountNumber = "123-123-123-123";
    String receiverAccountNumber = "123-123-123-124";
    long initBalance = 10_0000_0000L;
    @BeforeEach
    void beforeEach(){

        Account a1 = Account.create(senderAccountNumber, "김철수", initBalance);
        Account a2 = Account.create(receiverAccountNumber, "신짱구", initBalance);

        accountRepository.save(a1);
        accountRepository.save(a2);
    }


    @Test
    void sendWithSingleThreadTest() throws Exception {
        // given
        int threadCount = 100;
        int sendAmount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // when
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        SendRequest sendRequest = new SendRequest(
                senderAccountNumber,
                receiverAccountNumber,
                sendAmount
        );
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try{
                    accountService.send(sendRequest);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        // then
        Account sender = accountRepository.findById(senderAccountNumber).get();
        Account receiver = accountRepository.findById(receiverAccountNumber).get();

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(sender.getBalance()).isEqualTo(initBalance - sendAmount * threadCount);
        sa.assertThat(receiver.getBalance()).isEqualTo(initBalance + sendAmount * threadCount);
        sa.assertAll();
    }


    @RepeatedTest(100)
//    @Test
    void sendWithMultiThreadTest() throws Exception {
        // given
        int threadCount = 100;
        int sendAmount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        // when
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        SendRequest sendRequest = new SendRequest(
                senderAccountNumber,
                receiverAccountNumber,
                sendAmount
        );
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try{
                    accountService.send(sendRequest);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        // then
        Account sender = accountRepository.findById(senderAccountNumber).get();
        Account receiver = accountRepository.findById(receiverAccountNumber).get();

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(sender.getBalance()).isNotEqualTo(initBalance - sendAmount * threadCount); // 999986000L
        sa.assertThat(receiver.getBalance()).isNotEqualTo(initBalance + sendAmount * threadCount); // 1000006000L
        sa.assertAll();
    }

}