package com.kk.lock.controller;

public record TransactionResponse(
        String counterpartName,
        long balance
) {
}
