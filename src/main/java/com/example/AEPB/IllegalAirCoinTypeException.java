package com.example.AEPB;

public class IllegalAirCoinTypeException extends RuntimeException{
    public IllegalAirCoinTypeException(String errorMsg) {
        super(errorMsg);
    }
}
