package com.cs2340.towerjackets.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340.towerjackets.models.Coin;
import com.cs2340.towerjackets.models.tower.BeeTower;
import com.cs2340.towerjackets.models.tower.HornetTower;
import com.cs2340.towerjackets.models.tower.Tower;
import com.cs2340.towerjackets.models.tower.WaspTower;
import com.cs2340.towerjackets.models.enemy.Enemy;
import com.cs2340.towerjackets.models.enemy.PurpleEnemy;
import com.cs2340.towerjackets.models.enemy.BlueEnemy;
import com.cs2340.towerjackets.models.enemy.GreenEnemy;
import java.util.LinkedList;
import com.cs2340.towerjackets.models.Player;

public class GameActivityViewModel extends ViewModel {
    private LinkedList<Coin> listOfCoin = new LinkedList<>();

    public LinkedList<Coin> getListOfCoin() {
        return listOfCoin;
    }

    public void addCoin(Coin coin) {
        listOfCoin.add(coin);
    }


    // A list of Tower objects - the Tower objects know where they are
    private LinkedList<Tower> listOfTower = new LinkedList<>();

    public LinkedList<Tower> getListOfTower() {
        return listOfTower;
    }

    public void addTower(int tower, int x, int y) {
        Tower newTower;
        if (tower == 0) {
            newTower = new HornetTower();
        } else if (tower == 1) {
            newTower = new BeeTower();
        } else if (tower == 2) {
            newTower = new WaspTower();
        } else {
            throw new java.lang.IllegalArgumentException("Invalid tower type."
                    + " We only have 3 types of towers.");
        }

        newTower.setLocationX(x);
        newTower.setLocationY(y);
        listOfTower.add(newTower);
    }

    private LinkedList<Enemy> listOfEnemy = new LinkedList<>();

    public LinkedList<Enemy> getListOfEnemy() {
        return listOfEnemy;
    }

    public void addEnemy(int enemy, int x, int y) {
        Enemy newEnemy;
        if (enemy == 0) {
            newEnemy = new PurpleEnemy();
        } else if (enemy == 1) {
            newEnemy = new BlueEnemy();
        } else if (enemy == 2) {
            newEnemy = new GreenEnemy();
        } else {
            throw new java.lang.IllegalArgumentException("Invalid enemy type."
                    + " We only have 3 types of enemies.");
        }

        newEnemy.setLocationX(x);
        newEnemy.setLocationY(y);
        listOfEnemy.add(newEnemy);

    }

    private LinkedList<Enemy> listOfEnemyMonument = new LinkedList<>();

    public LinkedList<Enemy> getListOfEnemyMonument() {
        return listOfEnemyMonument;
    }

    public void addEnemyMonument(Enemy curr) {
        listOfEnemyMonument.add(curr);
    }


}
