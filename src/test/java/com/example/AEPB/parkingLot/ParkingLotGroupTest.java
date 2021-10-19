package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import com.example.AEPB.parkingLot.exception.CanNotGetVehicleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotGroupTest {
    private ParkingLotGroup parkingLotGroup;

    @BeforeEach
    private void setup() {
        parkingLotGroup = new ParkingLotGroup();
    }

    /**
     *  * 测试策略：
     *      * given: 所有停车场为空，停一辆车
     *      * when: 停车小弟停车
     *      * then: 将车停到一号停车场，拿到停车票
     */
    @Test
    void should_get_ticket_and_parking_on_one_parking_lot_success_when_parking_by_boy_given_all_parking_lot_is_null(){
        Vehicle vehicle = new Vehicle();
        ParkingTicket parkingTicket = parkingLotGroup.parkingByParkingBoy(vehicle);

        assertEquals(0, parkingTicket.getParkingLotNumber());
        assertEquals(vehicle, parkingLotGroup.getVehicle(parkingTicket));
    }

    /**
     *  * 测试策略：
     *      * given: 所有停车场为空，停一辆车
     *      * when: 停车小弟停车
     *      * then: 将车停到一号停车场，拿到停车票
     */
    @Test
    void should_get_ticket_and_parking_on_one_parking_lot_success_when_parking_by_boy_given_one_parking_lot_has_49_vehicle() {
        parkingSomeVehicle(49);
        Vehicle vehicle = new Vehicle();
        ParkingTicket parkingTicket = parkingLotGroup.parkingByParkingBoy(vehicle);

        assertEquals(0, parkingTicket.getParkingLotNumber());
        assertEquals(vehicle, parkingLotGroup.getVehicle(parkingTicket));
    }

    /**
     *  * 测试策略：
     *      * given: 一号停车场满，二号停车场不满，停一辆车
     *      * when: 停车小弟停车
     *      * then: 将车停到二号停车场，拿到停车票
     */
    @Test
    void should_get_ticket_and_parking_on_two_parking_lot_success_when_parking_by_boy_given_one_parking_lot_is_full_and_two_parking_lot_is_not_full() {
        parkingSomeVehicle(50);
        Vehicle vehicle = new Vehicle();
        ParkingTicket parkingTicket = parkingLotGroup.parkingByParkingBoy(vehicle);

        assertEquals(1, parkingTicket.getParkingLotNumber());
        assertEquals(vehicle, parkingLotGroup.getVehicle(parkingTicket));
    }


    /**
     *  * 测试策略：
     *      * given: 一号停车场不满，二号停车场不满，
     *      * when: 停车小弟停车
     *      * then: 将车停到一号停车场，拿到停车票
     */
    @Test
    void should_get_ticket_and_parking_on_one_parking_lot_success_when_parking_by_boy_given_one_parking_lot_has_49_and_two_parking_lot_has_one() {
        parkingSomeVehicle(49);
        ParkingTicket parkingTicketOnOneParkingLot = parkingLotGroup.parkingByParkingBoy(new Vehicle());
        parkingLotGroup.parkingByParkingBoy(new Vehicle());
        parkingLotGroup.getVehicle(parkingTicketOnOneParkingLot);

        Vehicle vehicle = new Vehicle();
        ParkingTicket parkingTicket = parkingLotGroup.parkingByParkingBoy(vehicle);

        assertEquals(0, parkingTicket.getParkingLotNumber());
        assertEquals(vehicle, parkingLotGroup.getVehicle(parkingTicket));
    }

    /**
     *  * 测试策略：
     *      * given: 十个停车场都满，
     *      * when: 停车小弟停车
     *      * then: 抛出 CanNotGetTicketException异常
     */
    @Test
    void should_throw_can_not_get_ticket_exception_when_parking_by_boy_given_10_parking_lot_all_full(){
        parkingSomeVehicle(500);

        assertThrows(CanNotGetTicketException.class, () ->  parkingLotGroup.parkingByParkingBoy(new Vehicle()));
    }


    /**
     *  * 测试策略：
     *      * given: 第一个停车场为空，用户没有车，想占用停车场位置，
     *      * when: 停车小弟停车
     *      * then: 抛出 CanNotGetTicketException异常
     */
    @Test
    void should_throw_can_not_get_ticket_exception_when_parking_by_boy_given_parking_lot_is_empty_and_vehicle_is_null() {
        assertThrows(CanNotGetTicketException.class, () ->  parkingLotGroup.parkingByParkingBoy(null));
    }

    private void parkingSomeVehicle(int parkingNumber) {
        for (int i = 0; i< parkingNumber; i++ ){
            parkingLotGroup.parkingByParkingBoy(new Vehicle());
        }
    }
}
