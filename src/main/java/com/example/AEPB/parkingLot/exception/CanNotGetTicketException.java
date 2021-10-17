package com.example.AEPB.parkingLot.exception;

public class CanNotGetTicketException extends RuntimeException {
    public CanNotGetTicketException(String errorMsg) {
        super(errorMsg);
    }
}
