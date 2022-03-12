package com.cs2340.towerjackets.models;

import com.cs2340.towerjackets.models.game_config.GameConfiguration;
import com.cs2340.towerjackets.models.tower.BeeTower;
import com.cs2340.towerjackets.models.tower.HornetTower;
import com.cs2340.towerjackets.models.tower.Tower;
import com.cs2340.towerjackets.models.tower.WaspTower;

import java.util.LinkedList;


public class Player {
    private String name;
    private GameConfiguration config;
    private int money;
    private int health;
    private Tower[] towerInv = new Tower[Tower.getTotalTowerTypes()];
    private int[] towerAvailable = new int[Tower.getTotalTowerTypes()];

    /**
     * Constructor for creating a new Player. More can be added later if needed.
     * @param name - player's name
     * @param config - a GameConfiguration objects which tell us the difficulty selected.
     */
    public Player(String name, GameConfiguration config) {
        // Preprocessing to check argument validity
        if ((name == null) || (config == null)) {
            throw new java.lang.IllegalArgumentException("Can't proceed without "
                    + "name and difficulty level.");
        }

        // Check to see if name is empty or contain only white-space
        if (name.trim().length() <= 0) {
            throw new java.lang.IllegalArgumentException("Name must not be "
                    + "null, empty, or only contain white spaces");
        }
        this.name = name;
        this.config = config;

        towerInv[0] = new HornetTower();
        towerInv[1] = new BeeTower();
        towerInv[2] = new WaspTower();

        // ordinal() will get the corresponding integer to the difficulty level
        initialConfiguration(config.getGameDifficulty().ordinal());
    }

    public void setName(String name) {
        if (name == null) {
            throw new java.lang.IllegalArgumentException("Name must not be "
                    + "null, empty, or only contain white spaces.");
        }
        // Check to see if name is empty or contain only white-space
        if (name.trim().length() <= 0) {
            throw new java.lang.IllegalArgumentException("Name must not be "
                    + "null, empty, or only contain white spaces");
        }
        this.name = name;
    }

    public void setConfig(GameConfiguration config) {
        if (config == null) {
            throw new java.lang.IllegalArgumentException("Can't process without "
                    + "selecting Difficulty level.");
        }
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public GameConfiguration getConfig() {
        return config;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    public int getTowerOneInv() {
        return towerAvailable[0];
    }

    public int getTowerTwoInv() {
        return towerAvailable[1];
    }

    public int getTowerThreeInv() {
        return towerAvailable[2];
    }

    public int getTowerOneCost() {
        return towerInv[0].getCost();
    }

    public int getTowerTwoCost() {
        return towerInv[1].getCost();
    }

    public int getTowerThreeCost() {
        return towerInv[2].getCost();
    }

    public void initialConfiguration(int difficulty) {
        if (difficulty == 0) { // easy
            money = 1000;
            health = 100;
            towerInv[0].setCost(60);
            towerInv[1].setCost(80);
            towerInv[2].setCost(110);
        } else if (difficulty == 1) { // normal
            money = 800;
            health = 80;
            towerInv[0].setCost(55);
            towerInv[1].setCost(90);
            towerInv[2].setCost(120);
        } else if (difficulty == 2) { // hard
            money = 500;
            health = 50;
            towerInv[0].setCost(50);
            towerInv[1].setCost(100);
            towerInv[2].setCost(130);
        }
    }

    public void buyTower(int tower) {
        if (money >= towerInv[tower].getCost()) {
            money -= towerInv[tower].getCost();
            towerAvailable[tower]++;
        }
    }

    public boolean placeTower(int tower, int x, int y) {
        if (checkValidPlacement(x, y)) {
            if (towerAvailable[tower] >= 1) {
                towerAvailable[tower]--;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkValidPlacement(int x, int y) {
        return !(x < 1149 && y > 205 && y < 436) && !(x > 899 && x < 1149 && y > 329 && y < 811)
                && !(x > 891 && y > 711 && y < 943);
    }
}
