package com.example.AEPB.newParkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class NewParkingLot implements Comparable<NewParkingLot>{
    private int maxParkingVehicleNumber;
    private Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();

    public NewParkingLot(int maxParkingVehicleNumber) {
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

    public String getParkingSpaceOccupancy() {
        DecimalFormat df=new DecimalFormat("0.00");
        return df.format((float)parkingTicketAndVehicleMappings.size()/(float)maxParkingVehicleNumber);
    }


    @Override
    public int compareTo(NewParkingLot newParkingLot) {
        return newParkingLot.getParkingLotRemainSpace()  - getParkingLotRemainSpace();
    }
}
