package com.example.AEPB.newParkingLot;

import java.util.Collections;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    private final List<NewParkingLot> newParkingLots;

    public SmartParkingBoy(List<NewParkingLot> newParkingLots) {
        super(newParkingLots);
        this.newParkingLots = newParkingLots;
    }

    public NewParkingLot getParkingLot() {
        Collections.sort(newParkingLots);
        if (!newParkingLots.isEmpty()) {
            return newParkingLots.get(0);
        }
        return null;
    }
}
