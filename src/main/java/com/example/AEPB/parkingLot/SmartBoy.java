package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;
import lombok.AllArgsConstructor;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class SmartBoy {
    //作为一个聪明小弟，能够将车停在空车位最多的停车场
    private final ParkingLotGroup parkingLotGroup = new ParkingLotGroup();

    public ParkingTicket parking(Vehicle vehicle) {
        return parkingLotGroup.sortParkingLotsByEmptySpace().get(0).parkingCar(vehicle);
    }

    public Vehicle getVehicle(ParkingTicket parkingTicket) {
        if (isNull(parkingTicket)) {
            throw new CanNotGetVehicleException("can not get vehicle when ticket is null");
        }
        if (isNull(parkingTicket.getParkingLotNumber())) {
            throw new CanNotGetVehicleException("can not get vehicle when ticket is null");
        }
        final int parkingLotNumber = parkingTicket.getParkingLotNumber();
        return parkingLotGroup.getParkingLots().get(parkingLotNumber).pickUpCar(parkingTicket);
    }

}
