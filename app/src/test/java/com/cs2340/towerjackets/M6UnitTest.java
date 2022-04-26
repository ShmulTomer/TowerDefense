package com.cs2340.towerjackets;

import static org.junit.Assert.assertEquals;

import com.cs2340.towerjackets.models.Monument;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.enemy.BlueEnemy;
import com.cs2340.towerjackets.models.enemy.Enemy;
import com.cs2340.towerjackets.models.enemy.GreenEnemy;
import com.cs2340.towerjackets.models.enemy.PurpleEnemy;
import com.cs2340.towerjackets.models.game_config.Difficulty;
import com.cs2340.towerjackets.models.game_config.GameConfiguration;
import com.cs2340.towerjackets.models.tower.BeeTower;
import com.cs2340.towerjackets.models.tower.HornetTower;
import com.cs2340.towerjackets.models.tower.WaspTower;

import org.junit.Test;

public class M6UnitTest {

    @Test
    // Anh Le - tests that upgrading a wasp tower increases the
    // value of the coin dropped by the tower.
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
        player.upgradeTower(2);
        assertEquals(1, player.getUpgradedTower(2));
        player.upgradeTower(2);
        assertEquals(1, player.getUpgradedTower(2));
    }

    @Test
    // Tomer Shmul - checks that final boss difficulty is greater than a typical blue enemy.
    public void finalBossDifficulty() {
        BlueEnemy enemy = new BlueEnemy(false);
        BlueEnemy bossEnemy = new BlueEnemy(true);
        assertEquals(true, enemy.getDamage() < bossEnemy.getDamage());
        assertEquals(true, enemy.getHealth() < bossEnemy.getHealth());
        assertEquals(300, bossEnemy.getDamage());
        assertEquals(300, bossEnemy.getHealth());
    }

    @Test
    // Harriet Kim - tests that once the boss dies, the game is over (and won),
    // and the monument is still alive.
    public void gameOverAfterBoss() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        Monument monument = new Monument(player);
        BlueEnemy bossEnemy = new BlueEnemy(true);
        bossEnemy.setHealth(0);
        bossEnemy.checkStatus(monument);
        assertEquals(true, monument.getHealth() > 0);
        assertEquals(true, monument.getGameOver());
    }

    @Test
    // Harriet Kim - since the statistics need to be shown at the end of the game,
    // this tests whether the number of enemies defeated is updated throughout the game.
    public void updateEnemiesDefeated() {
        Enemy.setEnemiesDefeated(0);
        BlueEnemy blue = new BlueEnemy(450, 550);
        blue.setHealth(0);
        assertEquals(1, Enemy.getEnemiesDefeated());
        GreenEnemy green = new GreenEnemy();
        green.setHealth(0);
        assertEquals(2, Enemy.getEnemiesDefeated());
        PurpleEnemy purple = new PurpleEnemy();
        purple.setHealth(0);
        assertEquals(3, Enemy.getEnemiesDefeated());
    }

    @Test
    // Ori Yoked - since the statistics need to be shown at the end of the game,
    // this tests whether or not the tower inventory is consistently updated.
    public void updateTowerInventory() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        player.buyTower(0);
        assertEquals(1, player.getTowerAvailable(0));
        player.buyTower(1);
        assertEquals(1, player.getTowerAvailable(1));
        player.buyTower(2);
        assertEquals(1, player.getTowerAvailable(2));
        assertEquals(3, player.getTotalTowerAvailable());
        player.buyTower(0);
        assertEquals(2, player.getTowerAvailable(0));
        player.buyTower(1);
        assertEquals(2, player.getTowerAvailable(1));
        player.buyTower(2);
        assertEquals(2, player.getTowerAvailable(2));
        assertEquals(6, player.getTotalTowerAvailable());
    }

    @Test
    // Ori Yoked - since the statistics need to be shown at the end of the game,
    // this tests whether or not the player's money is consistently updated.
    public void updateTotalMoney() {
        Player player = new Player("player1", new GameConfiguration(Difficulty.Easy));
        WaspTower waspTower = new WaspTower(player);
        player.buyTower(2);
        assertEquals(890, player.getMoney());
        waspTower.upgrade();
        assertEquals(780, player.getMoney());
        waspTower.getCoin().collectCoin(player);
        assertEquals(800, player.getMoney());
    }
}
