package com.cs2340.towerjackets.models.tower;
import com.cs2340.towerjackets.R;
import androidx.annotation.DrawableRes;

public abstract class Tower {
    private int drawableID;
    private int locationX;
    private int locationY;
    private int cost;
    private static final int totalTowerTypes = 3;


    public Tower() {
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

    public int getCost() {
        return cost;
    }

    public static int getTotalTowerTypes() {
        return totalTowerTypes;
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

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
