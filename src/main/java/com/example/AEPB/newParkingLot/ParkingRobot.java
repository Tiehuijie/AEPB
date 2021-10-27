package com.example.AEPB.newParkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ParkingRobot {
    private final List<NewParkingLot> newParkingLots;

    public ParkingRobot(List<NewParkingLot> newParkingLots) {
        this.newParkingLots = newParkingLots;
    }

    public ParkingTicket parkingVehicle(Vehicle vehicle) {
        return Optional.ofNullable(getParkingLot())
                .map(newParkingLot -> newParkingLot.parkingVehicle(vehicle))
                .orElseThrow(() ->  new CanNotGetTicketException("can not get ticket when parking lot is null"));
    }

    private NewParkingLot getParkingLot() {
        return newParkingLots.stream()
                .min(Comparator.comparing(NewParkingLot::getParkingSpaceOccupancy))
                .orElse(null);
    }
}
