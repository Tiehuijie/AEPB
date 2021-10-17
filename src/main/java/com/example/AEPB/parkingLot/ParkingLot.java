package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import lombok.*;

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

    public Vehicle getVehicle(ParkingTicket parkingTicket){
        return parkingTicketAndVehicleMappings.remove(parkingTicket);
    }

    public ParkingTicket getParkingTicket(Vehicle vehicle){
        if (isNull(vehicle) || isNull(vehicle.getVin())) {
            throw new CanNotGetTicketException("can not get ticket when vehicle is null");
        }
        if (parkingTicketAndVehicleMappings.size() == MAX_PARKING_VEHICLE_NUMBER) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket(UUID.randomUUID().toString());
        parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        return parkingTicket;
    }


}
