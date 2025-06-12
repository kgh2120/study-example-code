package com.kk.lock.facade;

import com.kk.lock.controller.SendRequest;
import com.kk.lock.entity.Account;
import com.kk.lock.repository.AccountRepository;
import com.kk.lock.service.OptimisticLockAccountService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisLockFacadeTest {
    @Autowired
    RedisLockFacade redisLockFacade;
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

    @AfterEach
    void afterEach(){
        accountRepository.deleteAll();
    }


    @Test
    void sendWithLettuceLockTest() throws Exception {
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
                    redisLockFacade.sendWithLettuceLock(sendRequest);
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
        sa.assertThat(sender.getBalance()).isEqualTo(initBalance - sendAmount * threadCount); // 999986000L
        sa.assertThat(receiver.getBalance()).isEqualTo(initBalance + sendAmount * threadCount); // 1000006000L
        sa.assertAll();
    }

    @Test
    void sendWithRedissonLockTest() throws Exception {
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
                    redisLockFacade.sendWithRedissonLock(sendRequest);
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
        sa.assertThat(sender.getBalance()).isEqualTo(initBalance - sendAmount * threadCount); // 999986000L
        sa.assertThat(receiver.getBalance()).isEqualTo(initBalance + sendAmount * threadCount); // 1000006000L
        sa.assertAll();
    }

}