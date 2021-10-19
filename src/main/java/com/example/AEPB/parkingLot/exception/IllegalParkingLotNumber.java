package com.example.AEPB.parkingLot.exception;

public class IllegalParkingLotNumber extends RuntimeException {
    public IllegalParkingLotNumber(String errorMsg) {
        super(errorMsg);
    }
}
