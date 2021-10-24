package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.dto.ParkingTicket;
import com.example.AEPB.parkingLot.dto.Vehicle;
import com.example.AEPB.parkingLot.exception.CanNotGetTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartBoyTest {
    private SmartBoy smartBoy;

    @BeforeEach
    private void setUp() {
        smartBoy = new SmartBoy();
    }

    /**
     *     Given：10个空的停车场
     *     When: 聪明小弟停车
     *     Then：小弟停到1号停车场，并取到停车票。
     */
    @Test
    void should_parking_on_1_parkingLot_when_parking_by_smart_boy_given_10_parkingLot_all_is_null() {
        //when
        ParkingTicket parkingTicket = smartBoy.parking(new Vehicle());

        //then
        assertEquals(0, parkingTicket.getParkingLotNumber());
    }

    /**
     *     Given：1号停车场停有1辆车，其他停车场无车
     *     When: 聪明小弟停车
     *     Then：小弟停到2号停车场，并取到停车票。
     */
    @Test
    void should_parking_on_parkingLot_2_when_parkingLot_1_has_1_vehicle_and_others_has_null() {
        //given
        ParkingTicket parkingTicketForParkingTimes1 = smartBoy.parking(new Vehicle());

        //when
        ParkingTicket parkingTicketForParkingTimes2 = smartBoy.parking(new Vehicle());

        //then
        assertEquals(1, parkingTicketForParkingTimes2.getParkingLotNumber());

    }


    /**
     *     Given：1号停车场有空车位9个，其他停车场有8个 空车位
     *     When: 聪明小弟停车
     *     Then：小弟停到1号停车场，并取到停车票。
     */
    @Test
    void should_parking_on_parkingLot_1_when_parkingLot_1_has_9_empty_space_and_others_has_8_empty_space() {
        //given
        ParkingTicket parkingTicketForParkingTimes1 = smartBoy.parking(new Vehicle());
        mockParkingBySmartBoy(19);
        smartBoy.getVehicle(parkingTicketForParkingTimes1);

        //when
        ParkingTicket parkingTicketForParkingTimes20 = smartBoy.parking(new Vehicle());

        //then
        assertEquals(0, parkingTicketForParkingTimes20.getParkingLotNumber());
    }

    /**
     *     Given：所有停车位已满
     *     When: 聪明小弟停车
     *     Then：记录车库已满，取不到停车票
     */
    @Test
    void should_get_parking_ticket_failed_when_parking_given_all_parkingLots_is_full() {
        //given
        mockParkingBySmartBoy(500);

        //then & when
        assertThrows(CanNotGetTicketException.class, ()  -> smartBoy.parking(new Vehicle()));
    }

    /**
     *     Given：所有停车位均为空，
     *     When: 聪明小弟停车
     *     Then：记录车库已满，取不到停车票
     */
    @Test
    void should_get_parking_ticket_failed_when_parking_given_all_vehicle_is_full() {

        //then & when
        assertThrows(CanNotGetTicketException.class, ()  -> smartBoy.parking(null));
    }
    public void mockParkingBySmartBoy(int parkingNumber) {
        for (int i = 0; i < parkingNumber; i++) {
            smartBoy.parking(new Vehicle());
        }
    }

}
