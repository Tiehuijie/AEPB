package com.example.AEPB;

import java.util.Objects;

public class AirCoin {
    private int amount;

    public static final int MAX_AMOUNT = 1000000000;
    public static final int MIN_AMOUNT = 0;

    public  AirCoin() { }

    public AirCoin(int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalAirCoinAmountTypeException("amount value should no less than "
                    + MIN_AMOUNT + " and no more than " + MAX_AMOUNT);
        }
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) throw new IllegalAirCoinTypeException("amount type is int can not be " + o.getClass());
        AirCoin airCoin = (AirCoin) o;
        return amount == airCoin.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
