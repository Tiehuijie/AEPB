package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@Getter
public class ParkingLotGroup {
    public static final int MAX_PARKING_LOT_NUMBER = 10;
    public static final int MIN_PARKING_LOT_NUMBER = 0;
    private final Map<Integer, ParkingLot> parkingLots = new HashMap<>();
    public static final int MAX_PARKING_VEHICLE_NUMBER = 50;

    public ParkingLotGroup() {
        for (int i = 0; i < MAX_PARKING_LOT_NUMBER; i++) {
            parkingLots.put(i, new ParkingLot());
        }
    }

    public ParkingTicket parkingByParkingBoy(Vehicle vehicle) {
        return parkingLots.entrySet().stream().filter(
                parkingLotEntry ->
                        Optional.ofNullable(parkingLotEntry.getValue())
                                .orElse(new ParkingLot())
                                .getParkingTicketAndVehicleMappings()
                                .size() < MAX_PARKING_VEHICLE_NUMBER)
                .findFirst()
                .map(parkingLotEntry -> parkingLotEntry.getValue().parkingCar(vehicle, parkingLotEntry.getKey()+1))
                .orElseThrow(() -> new CanNotGetTicketException("can not get ticket for all parking lot is full"));
    }

    public ParkingTicket parkingBySelf(Vehicle vehicle, int parkingLotNumber){
        if (parkingLotNumber > MAX_PARKING_LOT_NUMBER || parkingLotNumber < MIN_PARKING_LOT_NUMBER) {
            throw  new CanNotGetTicketException("can not get ticket for parking number is illegal");
        }
        return parkingLots.get(parkingLotNumber - 1).parkingCar(vehicle, parkingLotNumber);
    }

    public Vehicle getVehicle(ParkingTicket parkingTicket) {
        if (isNull(parkingTicket)) {
            throw new CanNotGetVehicleException("can not get vehicle when ticket is null");
        }
        parkingTicket.getParkingLotNumber();
        final int parkingLotNumber = parkingTicket.getParkingLotNumber();
        return parkingLots.get(parkingLotNumber).pickUpCar(parkingTicket);
    }

}
