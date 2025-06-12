package com.kk.lock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    private String accountNumber;

    @Column
    private String accountOwnerName;

    @Column
    private long balance;

    @Version
    private long version;

    private Account(String accountNumber, String accountOwnerName, long balance) {
        this.accountNumber = accountNumber;
        this.accountOwnerName = accountOwnerName;
        this.balance = balance;
    }

    public static Account create(String accountNumber, String accountOwnerName, long balance) {
        return new Account(accountNumber, accountOwnerName, balance);
    }

    public void withdraw(long amount) {
        if(amount > balance) {
            throw new IllegalArgumentException("너 돈 없어");
        }
        balance -= amount;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void updateAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }
}
