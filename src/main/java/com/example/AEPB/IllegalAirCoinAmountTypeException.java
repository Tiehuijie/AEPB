package com.example.AEPB;

public class IllegalAirCoinAmountTypeException extends RuntimeException {
    public IllegalAirCoinAmountTypeException(String errorMsg) {
        super(errorMsg);
    }
}
