package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NewParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    private void setup() {
        parkingLot = new ParkingLot();
    }

    /**
     * 测试策略：
     * given: 停车场为空，用户有一辆车
     * when: 存车
     * then: 拿到票根
     */
    @Test
    void should_get_parking_ticket_success_when_parking_vehicle_given_parking_lot_is_null_and_user_has_one_vehicle() {
        Vehicle vehicle = new Vehicle();

        ParkingTicket parkingTicket = parkingLot.parkingCar(vehicle);

        assertEquals(1, parkingLot.getParkingTicketAndVehicleMappings().size());
        assertEquals(vehicle, parkingLot.getParkingTicketAndVehicleMappings().get(parkingTicket));
    }

    /**
     * 测试策略：
     * given: 停车场内有停车49辆，用户有一辆车
     * when: 存车
     * then: 拿到票根
     */
    @Test
    void should_get_parking_ticket_success_when_parking_vehicle_given_parking_lot_has_49_vehicle_and_user_has_one_vehicle() {
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i = 0; i < 49; i++) {
            Vehicle vehicle = new Vehicle();
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = new Vehicle();

        ParkingTicket parkingTicket = parkingLot.parkingCar(vehicle);

        assertEquals(50, parkingLot.getParkingTicketAndVehicleMappings().size());
        assertEquals(vehicle, parkingLot.pickUpCar(parkingTicket));
    }


    /**
     * 测试策略：
     * given: 停车场内有停车50辆，用户有一辆车
     * when: 存车
     * then: 拿不到票根
     */
    @Test
    void should_get_parking_ticket_failed_when_parking_vehicle_given_parking_lot_has_50_vehicle_and_user_has_one_vehicle() {
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            Vehicle vehicle = new Vehicle();
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = new Vehicle();

        assertThrows(CanNotGetTicketException.class, () -> parkingLot.parkingCar(vehicle));
    }

    /**
     * 测试策略：
     * given: 停车场内有停车2辆，用户没有车，想占用停车场位置
     * when: 存车
     * then: 抛出CanNotGetTicketException异常
     */
    @Test
    void should_get_can_not_get_ticket_exception_when_parking_vehicle_given_parking_lot_has_2_vehicle_and_user_has_no_vehicle() {
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            Vehicle vehicle = new Vehicle();
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = null;

        assertThrows(CanNotGetTicketException.class, () -> parkingLot.parkingCar(vehicle));
    }


    /**
     * 测试策略：
     * given: 停车场内有停车2辆，用户的车vin号为空，想占用停车场位置
     * when: 存车
     * then: 抛出CanNotGetTicketException异常
     */
    @Test
    void should_get_can_not_get_ticket_exception_when_parking_vehicle_given_parking_lot_has_2_vehicle_and_user_vehicle_vin_is_empty() {
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingTicketAndVehicleMappings.put(parkingTicket, new Vehicle());
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = new Vehicle();

        assertThrows(CanNotGetTicketException.class, () -> parkingLot.parkingCar(vehicle));
    }

    /**
     * 测试策略：
     * given: 用户持有之前在该停车场存车的票根
     * when: 取车
     * then: 用户取到自己的车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_success_when_pick_up_vehicle_given_ticket_is_legal() {
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        ParkingTicket parkingTicket = new ParkingTicket();
        Vehicle vehicle = new Vehicle();
        parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);

        Vehicle vehicleGetFromLot = parkingLot.pickUpCar(parkingTicket);

        assertEquals(vehicle, vehicleGetFromLot);
    }

    /**
     * 测试策略：
     * given: 用户持有已经取过车的票根
     * when: 取车
     * then: 取不到车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_failed_when_pick_up_vehicle_given_ticket_has_been_used() {
        ParkingTicket parkingTicket = new ParkingTicket();

        assertThrows(CanNotGetVehicleException.class, () -> parkingLot.pickUpCar(parkingTicket));
    }

    /**
     * 测试策略：
     * given: 用户没有票根
     * when: 取车
     * then: 取不到车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_failed_when_pick_up_vehicle_given_ticket_is_null() {
        ParkingLot parkingLot = new ParkingLot();

        assertThrows(CanNotGetVehicleException.class, () -> parkingLot.pickUpCar(null));
    }

    /**
     * 测试策略：
     * given: 用户票根ticketNumber为空
     * when: 取车
     * then: 取不到车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_failed_when_pick_up_vehicle_given_ticket_ticket_number_is_null() {
        assertThrows(CanNotGetVehicleException.class, () -> parkingLot.pickUpCar(new ParkingTicket()));
    }
}
