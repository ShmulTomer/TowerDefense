package com.cs2340.towerjackets.models;

import com.cs2340.towerjackets.models.game_config.GameConfiguration;


public class Player {
    private String name;
    private GameConfiguration config;
    private int money;
    private int health;
    private int towerOneInv;
    private int towerTwoInv;
    private int towerThreeInv;
    private int towerOneCost;
    private int towerTwoCost;
    private int towerThreeCost;

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
        return towerOneInv;
    }

    public void setTowerOneInv(int towerOneInv) {
        this.towerOneInv = towerOneInv;
    }

    public int getTowerTwoInv() {
        return towerTwoInv;
    }

    public void setTowerTwoInv(int towerTwoInv) {
        this.towerTwoInv = towerTwoInv;
    }

    public int getTowerThreeInv() {
        return towerThreeInv;
    }

    public void setTowerThreeInv(int towerThreeInv) {
        this.towerThreeInv = towerThreeInv;
    }

    public int getTowerOneCost() {
        return towerOneCost;
    }

    public void setTowerOneCost(int towerOneCost) {
        this.towerOneCost = towerOneCost;
    }

    public int getTowerTwoCost() {
        return towerTwoCost;
    }

    public void setTowerTwoCost(int towerTwoCost) {
        this.towerTwoCost = towerTwoCost;
    }

    public int getTowerThreeCost() {
        return towerThreeCost;
    }

    public void setTowerThreeCost(int towerThreeCost) {
        this.towerThreeCost = towerThreeCost;
    }
}
