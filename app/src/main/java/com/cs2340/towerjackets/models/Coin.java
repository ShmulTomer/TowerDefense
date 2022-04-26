package com.cs2340.towerjackets.models;

public class Coin {
    private int locationX;
    private int locationY;
    private int value;

    public Coin(int x, int y, int v) {
        locationX = x;
        locationY = y;
        value = v;
    }

    // M5 JUnit Things
    public void collectCoin(Player player) {
        player.setMoney(player.getMoney() + value);
    }
    // End of M5 JUnit Things
    
    public int getValue() {
        return value;
    }

}
