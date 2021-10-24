package com.example.AEPB.parkingLot;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class ParkingLotGroup {
    public static final int MAX_PARKING_LOT_NUMBER = 10;
    public static final int MIN_PARKING_LOT_NUMBER = 0;
    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingLotGroup() {
        for (int parkLotNumber = MIN_PARKING_LOT_NUMBER; parkLotNumber < MAX_PARKING_LOT_NUMBER; parkLotNumber ++) {
            parkingLots.add(new ParkingLot(parkLotNumber));
        }
    }

    public List<ParkingLot> sortParkingLotsByEmptySpace() {
        Collections.sort(parkingLots);
        return parkingLots;
    }

}
