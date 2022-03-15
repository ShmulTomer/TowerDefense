package com.cs2340.towerjackets.models;


public class Monument {
    private int health;

    public Monument(Player player) {
        this.health = player.getHealth();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
