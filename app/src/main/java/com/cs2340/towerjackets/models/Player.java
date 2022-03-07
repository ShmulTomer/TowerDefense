package com.cs2340.towerjackets.models;

import com.cs2340.towerjackets.models.game_config.GameConfiguration;
import com.cs2340.towerjackets.views.InitialConfiguration;
import com.cs2340.towerjackets.views.TowerActivity;


public class Player {
    private String name;
    private GameConfiguration config;
    private int money;
    private int health;
    private int[] towerInv = new int[3];
    private int[] towerCost = new int[towerInv.length];

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
        this.health = health;
    }

    public int getTowerOneInv() {
        return towerInv[0];
    }

    public void setTowerOneInv(int towerOneInv) { towerInv[0] = towerOneInv; }

    public int getTowerTwoInv() {
        return towerInv[1];
    }

    public void setTowerTwoInv(int towerTwoInv) { towerInv[1] = towerTwoInv; }

    public int getTowerThreeInv() {
        return towerInv[2];
    }

    public void setTowerThreeInv(int towerThreeInv) { towerInv[2] = towerThreeInv; }

    public int getTowerOneCost() {
        return towerCost[0];
    }

    public int getTowerTwoCost() {
        return towerCost[1];
    }

    public int getTowerThreeCost() {
        return towerCost[2];
    }

    public void initialConfiguration(int difficulty) {
        for (int i = 0; i < towerInv.length; i++) {
            towerInv[i] = 0;
        }
        if (difficulty == 0) { // easy
            money = 1000;
            health = 100;
            towerCost[0] = 60;
            towerCost[1] = 80;
            towerCost[2] = 110;
        } else if (difficulty == 1) { // normal
            money = 800;
            health = 80;
            towerCost[0] = 55;
            towerCost[1] = 90;
            towerCost[2] = 120;
        } else if (difficulty == 2) { // hard
            money = 500;
            health = 50;
            towerCost[0] = 50;
            towerCost[1] = 100;
            towerCost[2] = 130;
        }
    }

    public void buyTower(int tower) {
        if (money >= towerCost[tower]) {
            money -= towerCost[tower];
            towerInv[tower]++;
        }
    }

}
