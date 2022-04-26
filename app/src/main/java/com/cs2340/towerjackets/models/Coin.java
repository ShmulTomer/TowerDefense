package com.cs2340.towerjackets.models;

public class Coin {
    private int locationX;
    private int locationY;
    private int value;

    public Coin(int x, int y, int value) {
        locationX = x;
        locationY = y;
        this.value = value;
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
