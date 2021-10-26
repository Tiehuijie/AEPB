package com.example.AEPB.newParkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket parkingVehicle(Vehicle vehicle) {
        return Optional.ofNullable(getParkingLot())
                .map(parkingLot -> parkingLot.parkingVehicle(vehicle))
                .orElseThrow(() ->  new CanNotGetTicketException("can not get ticket when parking lot is null"));

    }

    public Vehicle getVehicle(ParkingTicket parkingTicket) {
        return Optional.ofNullable(getParkingLot())
                .map(parkingLot -> parkingLot.getVehicle(parkingTicket))
                .orElseThrow(() -> new CanNotGetVehicleException("can not get vehicle when parking lot is null"));
    }

    public ParkingLot getParkingLot() {
       return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getParkingLotRemainSpace() > 0)
                .findFirst()
               .orElse(null);
    }
}
