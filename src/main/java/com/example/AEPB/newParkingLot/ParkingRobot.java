package com.example.AEPB.newParkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ParkingRobot {
    private final List<ParkingLot> parkingLots;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket parkingVehicle(Vehicle vehicle) {
        return Optional.ofNullable(getParkingLot())
                .map(parkingLot -> parkingLot.parkingVehicle(vehicle))
                .orElseThrow(() ->  new CanNotGetTicketException("can not get ticket when parking lot is null"));
    }

    public ParkingLot getParkingLot() {
        return parkingLots.stream()
                .min(Comparator.comparing(ParkingLot::getParkingSpaceOccupancy))
                .orElse(null);
    }
}
