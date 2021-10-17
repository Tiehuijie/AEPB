package com.example.AEPB.airCoin.exception;

public class IllegalAirCoinTypeException extends RuntimeException {
    public IllegalAirCoinTypeException(String errorMsg) {
        super(errorMsg);
    }
}
