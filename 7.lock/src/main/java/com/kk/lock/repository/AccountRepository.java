package com.kk.lock.repository;

import com.kk.lock.entity.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Account a where a.accountNumber = :accountNumber")
    Optional<Account> findByIdWithPessimisticWrite(String accountNumber);


    @Query(value = "select * from Account where account_number = :accountNumber for update", nativeQuery = true)
    Optional<Account> findByIdWithNativeQueryLock(String accountNumber);


    @Lock(LockModeType.OPTIMISTIC)
    @Query("select a from Account a where a.accountNumber = :accountNumber")
    Optional<Account> findByIdWithOptimisticLock(String accountNumber);
}
