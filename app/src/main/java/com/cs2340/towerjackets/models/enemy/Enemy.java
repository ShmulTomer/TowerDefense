package com.cs2340.towerjackets.models.enemy;
import com.cs2340.towerjackets.R;
import androidx.annotation.DrawableRes;

public abstract class Enemy {
    private int drawableID;
    private int locationX;
    private int locationY;
    private int health;
    private int speed;
    private int damage;
    private static final int totalEnemyTypes = 3;


    public Enemy() {
        // Let the children class sets the private fields.
    }

    public int getDrawableNumber() {
        return drawableID;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public static int getTotalEnemyTypes() {
        return totalEnemyTypes;
    }

    public void setDrawableID(int ID) {
        drawableID = ID;
    }

    public void setLocationX(int x) {
        locationX = x;
    }

    public void setLocationY(int y) {
        locationY = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}