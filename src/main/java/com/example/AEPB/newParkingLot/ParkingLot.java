package com.example.AEPB.newParkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class ParkingLot implements Comparable<ParkingLot>{
    private int maxParkingVehicleNumber;
    private Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();

    public ParkingLot(int maxParkingVehicleNumber) {
        this.maxParkingVehicleNumber = maxParkingVehicleNumber;
    }

    public Vehicle getVehicle(ParkingTicket parkingTicket){
        if (isNull(parkingTicket)) {
            throw new CanNotGetVehicleException("can not get vehicle when ticket is null");
        }
        Vehicle vehicle = parkingTicketAndVehicleMappings.remove(parkingTicket);
        if (isNull(vehicle)) {
            throw new CanNotGetVehicleException("can not get vehicle when ticket is illegal");
        }
        return vehicle;
    }

    public ParkingTicket parkingVehicle(Vehicle vehicle){
        if (isNull(vehicle)) {
            throw new CanNotGetTicketException("can not get ticket when vehicle is null");
        }
        if (parkingTicketAndVehicleMappings.size() == maxParkingVehicleNumber) {
            throw new CanNotGetTicketException("can not get ticket when parking lot is full");
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        return parkingTicket;
    }


    public int getParkingLotRemainSpace() {
        return maxParkingVehicleNumber - parkingTicketAndVehicleMappings.size();
    }

    public float getParkingSpaceOccupancy() {
        if (parkingTicketAndVehicleMappings.size() == 0) {
            return 1f;
        }
        return 1-(float)(parkingTicketAndVehicleMappings.size()/maxParkingVehicleNumber);
    }


    @Override
    public int compareTo(ParkingLot parkingLot) {
        return parkingLot.getParkingLotRemainSpace()  - getParkingLotRemainSpace();
    }
}
