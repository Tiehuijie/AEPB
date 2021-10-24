package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;
import com.example.AEPB.parkingLot.exception.ParkingLotNumberException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
public class ParkingLot implements Comparable<ParkingLot> {
    public static final int MAX_PARKING_VEHICLE_NUMBER = 50;
    public static final int MAX_PARKING_LOT_NUMBER = 10;
    public static final int MIN_PARKING_LOT_NUMBER = 0;
    private int parkingNumber;
    private Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();;

    public ParkingLot(int parkingNumber) {
        if (parkingNumber > MAX_PARKING_LOT_NUMBER - 1 || parkingNumber < MIN_PARKING_LOT_NUMBER) {
            throw new ParkingLotNumberException("parkingLot number must bigger than "+ MIN_PARKING_LOT_NUMBER + " and less than "+ MAX_PARKING_LOT_NUMBER);
        }
        this.parkingNumber =  parkingNumber;
    }


    public int getEmptySpace() {
        return MAX_PARKING_VEHICLE_NUMBER - parkingTicketAndVehicleMappings.size();
    }

    @Override
    public int compareTo(ParkingLot parkingLot) {
        return parkingLot.getEmptySpace() - getEmptySpace();
    }

    public Vehicle pickUpCar(ParkingTicket parkingTicket){
        if (isNull(parkingTicket)) {
            throw new CanNotGetVehicleException("can not get vehicle when ticket is null");
        }
        Vehicle vehicle = parkingTicketAndVehicleMappings.remove(parkingTicket);
        if (isNull(vehicle)) {
            throw new CanNotGetVehicleException("can not get vehicle when ticket is illegal");
        }
        return vehicle;
    }

    public ParkingTicket parkingCar(Vehicle vehicle){
        if (isNull(vehicle)) {
            throw new CanNotGetTicketException("can not get ticket when vehicle is null");
        }
        if (parkingTicketAndVehicleMappings.size() == MAX_PARKING_VEHICLE_NUMBER) {
            throw new CanNotGetTicketException("can not get ticket when parking lot is full");
        }
        ParkingTicket parkingTicket = new ParkingTicket(parkingNumber);
        parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        return parkingTicket;
    }


}
