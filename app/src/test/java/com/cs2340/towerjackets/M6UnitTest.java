package com.cs2340.towerjackets;

import static org.junit.Assert.assertEquals;

import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.enemy.BlueEnemy;
import com.cs2340.towerjackets.models.game_config.Difficulty;
import com.cs2340.towerjackets.models.game_config.GameConfiguration;
import com.cs2340.towerjackets.models.tower.BeeTower;
import com.cs2340.towerjackets.models.tower.HornetTower;
import com.cs2340.towerjackets.models.tower.WaspTower;

import org.junit.Test;

public class M6UnitTest {

    @Test
    // Anh Le - tests that upgrading a wasp tower increases the value of the coin dropped by the tower.
    public void waspTowerUpgrade() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        WaspTower waspTower = new WaspTower(player);
        waspTower.placeTower();
        assertEquals(10, waspTower.getCoin().getValue());
        waspTower.upgrade();
        assertEquals(20, waspTower.getCoin().getValue());
    }

    @Test
    // Anh Le - tests that upgrading a bee tower increases monument health.
    public void beeTowerUpgrade() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        BeeTower beeTower = new BeeTower(player);
        beeTower.placeTower();
        assertEquals(120, player.getHealth());
        beeTower.upgrade();
        assertEquals(145, player.getHealth());
    }

    @Test
    // Helen Chen - tests that upgrading a hornet tower increases damage done to enemies.
    public void hornetTowerUpgrade() {
        HornetTower hornetTower = new HornetTower(500, 500);
        BlueEnemy enemy = new BlueEnemy(450, 550);
        hornetTower.attackEnemy(enemy);
        assertEquals(150, enemy.getHealth());
        hornetTower.upgrade();
        System.out.println(hornetTower.getDamage());
        hornetTower.attackEnemy(enemy);
        assertEquals(75, enemy.getHealth());
    }

    @Test
    // Helen Chen - tests that purchasing a tower upgrade decreases player money and upgrades tower.
    public void purchaseTowerUpgrade() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        WaspTower waspTower = new WaspTower(player);
        player.buyTower(2);
        waspTower.placeTower();
        assertEquals(890, player.getMoney());
        player.upgradeTower(2);
        assertEquals(780, player.getMoney());
        assertEquals(1, player.getUpgradedTower(2));
    }

    @Test
    // Tomer Shmul - tests that only purchased towers can be upgraded.
    public void allowedTowerUpgrade() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        WaspTower waspTower = new WaspTower(player);
        player.buyTower(2);
        waspTower.placeTower();
        player.upgradeTower(2);
        assertEquals(1, player.getUpgradedTower(2));
        player.upgradeTower(2);
        assertEquals(1, player.getUpgradedTower(2));
    }

    @Test
    // Tomer Shmul -
    public void finalBossDifficulty() {

    }

    @Test
    // Harriet Kim -
    public void gameOverAfterBoss() {

    }

    @Test
    // Harriet Kim -
    public void updateStatistics() {

    }

    @Test
    // Ori Yoked -
    public void finalBossAppear() {

    }

    @Test
    // Ori Yoked -
    public void x() {

    }
}
