package com.cs2340.towerjackets.models.enemy;
import com.cs2340.towerjackets.R;
import com.cs2340.towerjackets.models.Monument;

public class BlueEnemy extends Enemy {
    public BlueEnemy() {
        super();
        setDrawableID(R.drawable.blue);
        setHealth(200);
        setSpeed(200);
        setDamage(200);
    }

    // M5 JUnit Things
    public BlueEnemy(int x, int y) {
        this();
        setLocationX(x);
        setLocationY(y);
    }
    public void move() {
        setLocationX(getLocationX() + 50);
        setLocationY(getLocationY() + 50);
    }
    public BlueEnemy(boolean boss) {
        if (boss) {
            super.setDamage(300);
            super.setHealth(300);
        } else {
            super.setDamage(200);
            super.setHealth(200);
        }
    }
    public boolean checkStatus(Monument monument) {
        if (getHealth() <= 0) {
            monument.setGameOver(true);
            return true;
        }
        return false;
    }
    // End of M5 JUnit Things

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}