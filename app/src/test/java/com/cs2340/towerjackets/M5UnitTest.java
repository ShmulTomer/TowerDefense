package com.cs2340.towerjackets;

import org.junit.Test;
import static org.junit.Assert.*;

import com.cs2340.towerjackets.models.Monument;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.enemy.BlueEnemy;
import com.cs2340.towerjackets.models.enemy.Enemy;
import com.cs2340.towerjackets.models.enemy.GreenEnemy;
import com.cs2340.towerjackets.models.enemy.PurpleEnemy;
import com.cs2340.towerjackets.models.game_config.Difficulty;
import com.cs2340.towerjackets.models.game_config.GameConfiguration;
import com.cs2340.towerjackets.models.tower.BeeTower;
import com.cs2340.towerjackets.models.tower.WaspTower;
import com.cs2340.towerjackets.viewmodels.GameActivityViewModel;


public class M5UnitTest {

    private GameActivityViewModel gameActivityViewModel;

    @Test
    // Anh Le - tests that placing a Bee Tower on the board increases monument/player health.
    public void beeTowerFunctionality() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        BeeTower beeTower = new BeeTower(player);
        beeTower.placeTower();
        assertEquals(120, player.getHealth());
    }

    @Test
    // Anh Le - Tests that enemies are removed from the list when their health reaches zero.
    public void enemiesHealthZero() {
        gameActivityViewModel = new GameActivityViewModel();
        gameActivityViewModel.addEnemy(1, 10, 10); // adding blue enemy
        gameActivityViewModel.addEnemy(2, 20, 20); // adding green enemy
        for (Enemy e: gameActivityViewModel.getListOfEnemy()) {
            if (e instanceof BlueEnemy) {
                e.setHealth(0);
            }
        }
        assertEquals(1, gameActivityViewModel.getListOfEnemy().size());
    }

    @Test
    // Helen Chen - tests that placing a Hornet Tower and enemy not within proximity does not do anything.
    public void enemyNotWithinProximity() {

    }

    @Test
    // Helen Chen - tests that placing a Hornet Tower and enemy within proximity decreases enemy health.
    public void enemyWithinProximity() {

    }

    @Test
    // Ori Yoked - tests that placing a Wasp Tower generates one coin.
    public void waspTowerFunctionality() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        WaspTower waspTower = new WaspTower(player);
        waspTower.placeTower();
        assertEquals(10, waspTower.getCoin().getValue());
    }

    @Test
    // Ori Yoked - tests that collecting the coin increases player money.
    public void coinFunctionality() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        WaspTower waspTower = new WaspTower(player);
        waspTower.placeTower();
        waspTower.getCoin().collectCoin(player);
        assertEquals(1010, player.getMoney());
    }

    @Test
    // Tomer Shmul -
    public void xxx() {

    }

    @Test
    // Tomer Shmul - each enemy has different damage differences.
    public void yyy() {

    }

    @Test
    // Harriet Kim - all towers function well together
    public void zzz() {

    }

    @Test
    // Harriet Kim -
    public void www() {

    }
}

