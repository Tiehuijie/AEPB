package com.example.AEPB.newParkingLot;

import com.example.AEPB.parkingLot.ParkingLot;
import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingRobotTest {

    /**
     * given: 一共两个停车场，1号停车场共10个停车位，已停2辆车， 2号停车场共5个停车位，已停2辆车
     * when: parkingRobot停车
     * then:  停到1号停车场，并取到停车票
     */
    @Test
    void should_parking_on_parkingLot_1_when_parking_by_parking_robot_given_2_parking_lots_and_parkingLot_1_has_10_space_2_vehicle_parkingLot_2_has_5_space_2_vehicle() {
        //given
       NewParkingLot parkingLot1 = new NewParkingLot(10);
       NewParkingLot parkingLot2 = new NewParkingLot(5);
        parkingVehicle(parkingLot1, 2);
        parkingVehicle(parkingLot2, 2);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLot1, parkingLot2));

        //when
        ParkingTicket parkingTicket = parkingRobot.parkingVehicle(new Vehicle());

        //then
        assertEquals(7, parkingLot1.getParkingLotRemainSpace());
        assertEquals(3, parkingLot2.getParkingLotRemainSpace());

    }

    /**
     * given: 一共两个停车场，1号停车场共10个停车位，已停2辆车， 2号停车场共5个停车位，已停0辆车
     * when: parkingRobot停车
     * then:  停到2号停车场，并取到停车票
     */
    @Test
    void should_parking_on_parkingLot_2_when_parking_by_parking_robot_given_2_parking_lots_and_parkingLot_1_has_10_space_2_vehicle_parkingLot_2_has_5_space_0_vehicle() {
        //given
        NewParkingLot parkingLot1 = new NewParkingLot(10);
        NewParkingLot parkingLot2 = new NewParkingLot(5);
        parkingVehicle(parkingLot1, 2);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLot1, parkingLot2));

        //when
        ParkingTicket parkingTicket = parkingRobot.parkingVehicle(new Vehicle());

        //then
        assertEquals(8, parkingLot1.getParkingLotRemainSpace());
        assertEquals(4, parkingLot2.getParkingLotRemainSpace());
    }

    /**
     * given: 一共三个停车场，1号停车场共10个停车位，已停3辆车， 2号停车场共10个停车位，已停2辆车，3号停车场共10个停车位，已停2辆车
     * when: parkingRobot停车
     * then:  停到2号停车场，并取到停车票
     */
    @Test
    void should_parking_on_parkingLot_2_when_parking_by_parking_robot_given_3_parking_lots_and_parkingLot_1_has_10_space_3_other_parkingLot__has_10_space_2_vehicle() {
        //given
        NewParkingLot parkingLot1 = new NewParkingLot(10);
        NewParkingLot parkingLot2 = new NewParkingLot(10);
        NewParkingLot parkingLot3 = new NewParkingLot(10);
        parkingVehicle(parkingLot1, 3);
        parkingVehicle(parkingLot2, 2);
        parkingVehicle(parkingLot3, 2);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLot1, parkingLot2, parkingLot3));

        //when
        ParkingTicket parkingTicket = parkingRobot.parkingVehicle(new Vehicle());

        //then
        assertEquals(7, parkingLot1.getParkingLotRemainSpace());
        assertEquals(7, parkingLot2.getParkingLotRemainSpace());
        assertEquals(8, parkingLot3.getParkingLotRemainSpace());
    }

    /**
     *     Given：所有停车位已满
     *     When: parkingRobot停车
     *     Then：记录车库已满，取不到停车票
     */
    @Test
    void should_get_parking_ticket_failed_when_parking_given_all_parkingLots_is_full() {
        //given
        NewParkingLot parkingLot1 = new NewParkingLot(10);
        NewParkingLot parkingLot2 = new NewParkingLot(10);
        parkingVehicle(parkingLot1, 10);
        parkingVehicle(parkingLot2, 10);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLot1, parkingLot2));

        //when&then
        assertThrows(CanNotGetTicketException.class, () -> parkingRobot.parkingVehicle(new Vehicle()));
    }


    /**
     *     Given：所有停车位均为空，无车
     *     When: parkingRobot停车
     *     Then：记录车不合规，取不到停车票
     */
    @Test
    void should_get_parking_ticket_failed_when_parking_given_all_vehicle_is_full() {
        //given
        NewParkingLot parkingLot1 = new NewParkingLot(10);
        NewParkingLot parkingLot2 = new NewParkingLot(10);
        parkingVehicle(parkingLot1, 10);
        parkingVehicle(parkingLot2, 10);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLot1, parkingLot2));

        //when&then
        assertThrows(CanNotGetTicketException.class, () -> parkingRobot.parkingVehicle(null));
    }

    void parkingVehicle(NewParkingLot parkingLot, int parkingVehicleNumber) {
        for (int i =0 ; i< parkingVehicleNumber; i++) {
            parkingLot.parkingVehicle(new Vehicle());
        }
    }
    
}
