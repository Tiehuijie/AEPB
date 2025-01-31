package com.example.AEPB.parkingLot.dto;

import com.example.AEPB.parkingLot.exception.IllegalParkingLotNumber;
import lombok.Getter;

@Getter
public class ParkingTicket {
    public static final int MAX_PARKING_LOT_NUMBER = 10;
    private Integer parkingLotNumber;

    public ParkingTicket() {
    }

    public ParkingTicket(Integer parkingLotNumber) {
        if (parkingLotNumber < 0 || parkingLotNumber > MAX_PARKING_LOT_NUMBER - 1) {
            throw new IllegalParkingLotNumber("parting lot number should no less than 0 and no more than " + MAX_PARKING_LOT_NUMBER);
        }
        this.parkingLotNumber = parkingLotNumber ;
    }

}
