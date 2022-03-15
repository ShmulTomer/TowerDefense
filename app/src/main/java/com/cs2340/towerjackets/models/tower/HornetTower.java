package com.cs2340.towerjackets.models.tower;
import com.cs2340.towerjackets.R;

public class HornetTower extends Tower {

    public HornetTower() {
        super();
        setDrawableID(R.drawable.hornet);
        setCost(55);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
