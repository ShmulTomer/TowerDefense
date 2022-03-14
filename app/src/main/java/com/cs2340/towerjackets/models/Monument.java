package com.cs2340.towerjackets.models;

public class Monument {
    private int health;

    public Monument() {

    }

    public void setHealth(int health) {
        if (health <= 0) {
            //game over
        } else {
            this.health = health;
        }
    }

    public int getHealth() {
        return health;
    }
}
