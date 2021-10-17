package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    /**
     * 测试策略：
     * given: 停车场为空，用户有一辆车
     * when: 存车
     * then: 拿到票根
     */
    @Test
    void should_get_parking_ticket_success_when_put_vehicle_given_parking_lot_is_null_and_user_has_one_vehicle() {
        Vehicle vehicle = new Vehicle("123");
        ParkingLot parkingLot = new ParkingLot();

        ParkingTicket parkingTicket = parkingLot.getParkingTicket(vehicle);

        assertEquals(1, parkingLot.getParkingTicketAndVehicleMappings().size());
        assertEquals("123", parkingLot.getParkingTicketAndVehicleMappings().get(parkingTicket).getVin());
    }

    /**
     * 测试策略：
     * given: 停车场内有停车49辆，用户有一辆车
     * when: 存车
     * then: 拿到票根
     */
    @Test
    void should_get_parking_ticket_success_when_put_vehicle_given_parking_lot_has_49_vehicle_and_user_has_ont_vehicle() {
        ParkingLot parkingLot = new ParkingLot();
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i=0; i < 49; i++) {
            Vehicle vehicle = new Vehicle("ori" + i);
            ParkingTicket parkingTicket = new ParkingTicket(UUID.randomUUID().toString());
            parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = new Vehicle("123");

        ParkingTicket parkingTicket = parkingLot.getParkingTicket(vehicle);

        assertEquals(50, parkingLot.getParkingTicketAndVehicleMappings().size());
        assertEquals("123", parkingLot.getParkingTicketAndVehicleMappings().get(parkingTicket).getVin());
    }


    /**
     * 测试策略：
     * given: 停车场内有停车50辆，用户有一辆车
     * when: 存车
     * then: 拿不到票根
     */
    @Test
    void should_get_parking_ticket_failed_when_put_vehicle_given_parking_lot_has_50_vehicle_and_user_has_one_vehicle() {
        ParkingLot parkingLot = new ParkingLot();
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i=0; i < 50; i++) {
            Vehicle vehicle = new Vehicle("ori" + i);
            ParkingTicket parkingTicket = new ParkingTicket(UUID.randomUUID().toString());
            parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = new Vehicle("123");

        assertNull(parkingLot.getParkingTicket(vehicle));
    }

    /**
     * 测试策略：
     * given: 停车场内有停车2辆，用户没有车，想占用停车厂位置
     * when: 存车
     * then: 抛出CanNotGetTicketException异常
     */
    @Test
    void should_get_can_not_get_ticket_exception_when_put_vehicle_given_parking_lot_has_2_vehicle_and_user_has_no_vehicle() {
        ParkingLot parkingLot = new ParkingLot();
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i=0; i < 2; i++) {
            Vehicle vehicle = new Vehicle("ori" + i);
            ParkingTicket parkingTicket = new ParkingTicket(UUID.randomUUID().toString());
            parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = null;

        assertThrows(CanNotGetTicketException.class, () -> parkingLot.getParkingTicket(vehicle));
    }


    /**
     * 测试策略：
     * given: 停车场内有停车2辆，用户车没有vin号，想占用停车厂位置
     * when: 存车
     * then: 抛出CanNotGetTicketException异常
     */
    @Test
    void should_get_can_not_get_ticket_exception_when_put_vehicle_given_parking_lot_has_2_vehicle_and_user_vehicle_has_no_vin() {
        ParkingLot parkingLot = new ParkingLot();
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        for (int i=0; i < 2; i++) {
            Vehicle vehicle = new Vehicle("ori" + i);
            ParkingTicket parkingTicket = new ParkingTicket(UUID.randomUUID().toString());
            parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        }
        parkingLot.setParkingTicketAndVehicleMappings(parkingTicketAndVehicleMappings);
        Vehicle vehicle = new Vehicle();

        assertThrows(CanNotGetTicketException.class, () -> parkingLot.getParkingTicket(vehicle));
    }


    /**
     * 测试策略：
     * given: 用户持有之前在该停车场存车的票根
     * when: 取车
     * then: 用户取到自己的车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_success_when_get_vehicle_given_ticket_is_legal() {
        Map<ParkingTicket, Vehicle> parkingTicketAndVehicleMappings = new HashMap<>();
        ParkingTicket parkingTicket = new ParkingTicket("ticketNumber");
        Vehicle vehicle = new Vehicle("vin001");
        parkingTicketAndVehicleMappings.put(parkingTicket, vehicle);
        ParkingLot parkingLot = new ParkingLot(parkingTicketAndVehicleMappings);

        Vehicle vehicleGetFromLot = parkingLot.getVehicle(parkingTicket);

        assertEquals("vin001", vehicleGetFromLot.getVin());
    }

    /**
     * 测试策略：
     * given: 用户持有已经取过车的票根
     * when: 取车
     * then: 取不到车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_failed_when_get_vehicle_given_ticket_has_been_used() {
        ParkingTicket parkingTicket = new ParkingTicket("ticketNumber");
        ParkingLot parkingLot = new ParkingLot();

        Vehicle vehicleGetFromLot = parkingLot.getVehicle(parkingTicket);

        assertEquals(null, vehicleGetFromLot);
    }

    /**
     * 测试策略：
     * given: 用户没有票根
     * when: 取车
     * then: 取不到车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_failed_when_get_vehicle_given_ticket_is_null() {
        ParkingLot parkingLot = new ParkingLot();

        Vehicle vehicleGetFromLot = parkingLot.getVehicle(null);

        assertEquals(null, vehicleGetFromLot);
    }

    /**
     * 测试策略：
     * given: 用户票根ticketNumber为空
     * when: 取车
     * then: 取不到车
     */
    @Test
    void should_get_vehicle_by_parking_ticket_failed_when_get_vehicle_given_ticket_ticket_number_is_null() {
        ParkingLot parkingLot = new ParkingLot();

        Vehicle vehicleGetFromLot = parkingLot.getVehicle(new ParkingTicket());

        assertEquals(null, vehicleGetFromLot);
    }

}
