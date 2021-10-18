package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {
    public static final int MAX_PARKING_VEHICLE_NUMBER = 50;
    private Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();

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

    public ParkingTicket parkCar(Vehicle vehicle){
        if (isNull(vehicle) || StringUtils.isEmpty(vehicle.getVin())) {
            throw new CanNotGetTicketException("can not get ticket when vehicle is null");
        }
        if (parkingTicketAndVehicleMappings.size() == MAX_PARKING_VEHICLE_NUMBER) {
            throw new CanNotGetTicketException("can not get ticket when parking lot is full");
        }
        ParkingTicket parkingTicket = new ParkingTicket(UUID.randomUUID().toString());
        parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        return parkingTicket;
    }


}
