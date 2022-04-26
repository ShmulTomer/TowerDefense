package com.cs2340.towerjackets.models.tower;
import com.cs2340.towerjackets.R;
import com.cs2340.towerjackets.models.Player;

public class BeeTower extends Tower {

    public BeeTower() {
        super();
        setDrawableID(R.drawable.bee_tower_default);
        setCost(90);
    }

    // M5 JUnit Things
    private Player player;
    public BeeTower(Player player) {
        this.player = player;
        player.incrementTowerAvailable(0);
    }
    public void placeTower() {
        player.setHealth(player.getHealth() + 20);
    }
    public void upgrade() {
        player.setHealth(player.getHealth() + 25);
    }
    // End of M5 JUnit Things

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
