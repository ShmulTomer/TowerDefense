package com.cs2340.towerjackets.models.enemy;
import com.cs2340.towerjackets.R;

public class BlueEnemy extends Enemy {
    public BlueEnemy() {
        super();
        setDrawableID(R.drawable.blue);
        setHealth(200);
        setSpeed(200);
        setDamage(200);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}