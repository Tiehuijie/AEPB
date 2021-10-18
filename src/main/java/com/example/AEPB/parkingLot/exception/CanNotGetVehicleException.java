package com.example.AEPB.parkingLot.exception;

public class CanNotGetVehicleException extends RuntimeException {
    public CanNotGetVehicleException(String errorMsg) {
        super(errorMsg);
    }
}
