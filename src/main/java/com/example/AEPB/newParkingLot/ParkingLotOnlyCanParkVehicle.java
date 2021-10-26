package com.example.AEPB.newParkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class ParkingLotOnlyCanParkVehicle implements Comparable<ParkingLotOnlyCanParkVehicle>{
    private int maxParkingVehicleNumber;
    private Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();

    public ParkingLotOnlyCanParkVehicle(int maxParkingVehicleNumber) {
        this.maxParkingVehicleNumber = maxParkingVehicleNumber;
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


    public String getParkingLotSpaceOccupancy() {
        if (parkingTicketAndVehicleMappings.size() == 0) {
            return 1.00+"";
        }

        DecimalFormat df=new DecimalFormat("0.00");
        return df.format(1- (float)parkingTicketAndVehicleMappings.size()/ maxParkingVehicleNumber);
    }


    @Override
    public int compareTo(ParkingLotOnlyCanParkVehicle parkingLot) {
        return parkingLot.getParkingLotSpaceOccupancy().compareTo(getParkingLotSpaceOccupancy());
    }
}
