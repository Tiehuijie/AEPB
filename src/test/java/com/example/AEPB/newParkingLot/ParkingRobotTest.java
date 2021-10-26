package com.example.AEPB.newParkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingRobotTest {

    @BeforeEach
    private void setup() {

    }

    /**
     * given: 一共两个停车场，1号停车场共10个停车位，已停2辆车， 2号停车场共5个停车位，已停2辆车
     * when: parkingRobot停车
     * then:  停到1号停车场，并取到停车票
     */
    @Test
    void should_parking_on_parkingLot_1_when_parking_by_parking_robot_given_2_parking_lots_and_parkingLot_1_has_10_space_2_vehicle_parkingLot_2_has_5_space_2_vehicle() {

    }

    /**
     * given: 一共两个停车场，1号停车场共10个停车位，已停2辆车， 2号停车场共5个停车位，已停0辆车
     * when: parkingRobot停车
     * then:  停到2号停车场，并取到停车票
     */
    @Test
    void should_parking_on_parkingLot_2_when_parking_by_parking_robot_given_2_parking_lots_and_parkingLot_1_has_10_space_2_vehicle_parkingLot_2_has_5_space_0_vehicle() {

    }

    /**
     * given: 一共三个停车场，1号停车场共10个停车位，已停3辆车， 2号停车场共10个停车位，已停2辆车，3号停车场共10个停车位，已停2辆车
     * when: parkingRobot停车
     * then:  停到2号停车场，并取到停车票
     */
    @Test
    void should_parking_on_parkingLot_2_when_parking_by_parking_robot_given_3_parking_lots_and_parkingLot_1_has_10_space_3_other_parkingLot__has_10_space_2_vehicle() {

    }

    /**
     *     Given：所有停车位已满
     *     When: parkingRobot停车
     *     Then：记录车库已满，取不到停车票
     */
    @Test
    void should_get_parking_ticket_failed_when_parking_given_all_parkingLots_is_full() {

    }


    /**
     *     Given：所有停车位均为空，无车
     *     When: parkingRobot停车
     *     Then：记录车不合规，取不到停车票
     */
    @Test
    void should_get_parking_ticket_failed_when_parking_given_all_vehicle_is_full() {
        
    }

}
