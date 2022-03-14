package com.cs2340.towerjackets.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.cs2340.towerjackets.models.tower.BeeTower;
import com.cs2340.towerjackets.models.tower.HornetTower;
import com.cs2340.towerjackets.models.tower.Tower;
import com.cs2340.towerjackets.models.tower.WaspTower;

import java.util.LinkedList;

public class GameActivityViewModel extends ViewModel {
    // A list of Tower objects - the Tower objects know where they are
    LinkedList<Tower> listOfTower = new LinkedList<>();

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
            throw new java.lang.IllegalArgumentException("Invalid tower type. We only have 3 types of towers.");
        }

        newTower.setLocationX(x);
        newTower.setLocationY(y);
        listOfTower.add(newTower);
    }


}
