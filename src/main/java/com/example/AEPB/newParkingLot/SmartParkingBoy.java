package com.example.AEPB.newParkingLot;

import java.util.Collections;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public ParkingLot getParkingLot() {
        Collections.sort(parkingLots);
        if (!parkingLots.isEmpty()) {
            return parkingLots.get(0);
        }
        return null;
    }
}
