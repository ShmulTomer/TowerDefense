package com.cs2340.towerjackets.models;

public class Coin {
    private int locationX;
    private int locationY;
    private int value = 10;

    public Coin(int x, int y) {
        locationX = x;
        locationY = y;
    }
    
    public int getValue() {
        return value;
    }

}
