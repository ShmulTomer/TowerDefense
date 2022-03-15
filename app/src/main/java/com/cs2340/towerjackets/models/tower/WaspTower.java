package com.cs2340.towerjackets.models.tower;
import com.cs2340.towerjackets.R;

public class WaspTower extends Tower {

    public WaspTower() {
        super();
        setDrawableID(R.drawable.wasp);
        setCost(120);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
