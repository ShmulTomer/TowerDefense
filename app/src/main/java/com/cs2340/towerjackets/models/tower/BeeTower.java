package com.cs2340.towerjackets.models.tower;
import com.cs2340.towerjackets.R;

public class BeeTower extends Tower{

    public BeeTower() {
        super();
        setDrawableID(R.drawable.bee);
        setCost(90);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
