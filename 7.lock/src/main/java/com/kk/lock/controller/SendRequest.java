package com.kk.lock.controller;

public record SendRequest(String senderAccountNumber, String receiverAccountNumber,
                          long amount) {
}
