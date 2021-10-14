package com.example.AEPB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AirCoinTest {
    /**
     * 测试策略：
     * （1）given:货币A的面值为1和货币B的面值为1 when:比较A和B面值的大小 then: A和B面值相等，返回true.
     * （2）given:货币A的面值为1和货币B的面值为2 when:比较A和B面值的大小 then: A和B面值不等，返回false.
     * （3）given:货币A的面值小于货币面值的最小值 when:创建货币A then: 抛出货币数值异常.
     * （4）given:货币A的面值大于货币面值的最大值 when:创建货币A then: 抛出货币数值异常.
     * （5）given:货币A的面值为1和int类型B when:比较A面值和B的大小 then: 抛出A和B类型不同异常.
     * （6）given:货币A的面值为1和为空的B when:比较A面值和B的大小 then: 抛出空指针异常.
     * （7）given:货币A的面值为1和货币B的面值为null when:比较A和B面值的大小 then: A和B面值不等，返回false.
     */
    @Test
    void should_return_true_when_compare_airCoin_amount_given_airCoin_a_amount_is_1_and_airCoin_b_amount_is_1(){
        AirCoin a =  new AirCoin(1);
        AirCoin b = new AirCoin(1);
        assertTrue( a.equals(b));
    }

    @Test
    void should_return_false_when_compare_airCoin_amount_given_airCoin_a_amount_is_1_and_airCoin_b_amount_is_2(){
        AirCoin a =  new AirCoin(1);
        AirCoin b = new AirCoin(2);
        assertFalse(a.equals(b));
    }


    @Test
    void should_throw_illegal_airCoin_amount_exception_when_create_airCoin_given_airCoin_a_amount_is_less_than_min_amount(){
        assertThrows(IllegalAirCoinAmountTypeException.class, () -> new AirCoin(AirCoin.MIN_AMOUNT-1));
    }

    @Test
    void should_throw_illegal_airCoin_amount_exception_when_create_airCoin_given_airCoin_a_amount_more_than_max_amount(){
        assertThrows(IllegalAirCoinAmountTypeException.class, () -> new AirCoin(AirCoin.MAX_AMOUNT+1));
    }

    @Test
    void should_throw_illegal_airCoin_type_exception_when_compare_airCoin_given_airCoin_type_a_and_int_type_b(){
        AirCoin a =  new AirCoin(1);
        int b = 1;
        assertThrows(IllegalAirCoinTypeException.class, () -> a.equals(b));
    }

    @Test
    void should_throw_null_pointer_exception_when_compare_airCoin_amount_given_airCoin_a_amount_is_1_and_airCoin_b__is_null(){
        AirCoin a =  new AirCoin(1);
        AirCoin b = null;
        assertThrows(NullPointerException.class, () -> a.equals(b));
    }

    @Test
    void should_return_false_when_compare_airCoin_amount_given_airCoin_a_amount_is_1_and_airCoin_b_amount_is_null(){
        AirCoin a =  new AirCoin(1);
        AirCoin b = new AirCoin();
        assertFalse(a.equals(b));
    }

}
