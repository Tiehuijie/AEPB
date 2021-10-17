package com.example.AEPB.airCoin.exception;

public class IllegalAirCoinAmountTypeException extends RuntimeException {
    public IllegalAirCoinAmountTypeException(String errorMsg) {
        super(errorMsg);
    }
}
