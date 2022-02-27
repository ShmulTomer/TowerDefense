package com.cs2340.towerjackets.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cs2340.towerjackets.R;
import com.cs2340.towerjackets.models.Player;

public class TowerActivity extends AppCompatActivity {

    private TextView moneyView;
    private TextView healthView;

    private TextView towerOneView;
    private TextView towerTwoView;
    private TextView towerThreeView;

    private TextView towerOneCostV;
    private TextView towerTwoCostV;
    private TextView towerThreeCostV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tower_menu);
        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton menuButton = findViewById(R.id.towerMenuB);
        Player player = InitialConfiguration.getPlayer();

        // Add event listeners for button
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(TowerActivity.this, GameActivity.class);
                startActivity(intention);
            }
        });

        Button buyT1 = findViewById(R.id.towerOneB);
        buyT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.getMoney() >= player.getTowerOneCost()) {
                    player.setMoney(player.getMoney() - player.getTowerOneCost());
                    player.setTowerOneInv(player.getTowerOneInv() + 1);
                    setValues();
                }
            }
        });

        Button buyT2 = findViewById(R.id.towerTwoB);
        buyT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.getMoney() >= player.getTowerTwoCost()) {
                    player.setMoney(player.getMoney() - player.getTowerTwoCost());
                    player.setTowerTwoInv(player.getTowerTwoInv() + 1);
                    setValues();
                }
            }
        });

        Button buyT3 = findViewById(R.id.towerThreeB);
        buyT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.getMoney() >= player.getTowerThreeCost()) {
                    player.setMoney(player.getMoney() - player.getTowerThreeCost());
                    player.setTowerThreeInv(player.getTowerThreeInv() + 1);
                    setValues();
                }
            }
        });

        moneyView = findViewById(R.id.moneyV);
        healthView = findViewById(R.id.hpV);
        towerOneView = findViewById(R.id.towerOneV);
        towerTwoView = findViewById(R.id.towerTwoV);
        towerThreeView = findViewById(R.id.towerThreeV);

        towerOneCostV = findViewById(R.id.towerOneC);
        towerTwoCostV = findViewById(R.id.towerTwoC);
        towerThreeCostV = findViewById(R.id.towerThreeC);

        setValues();
    }

    private void setValues() {
        Player player = InitialConfiguration.getPlayer();
        healthView.setText(Integer.toString(player.getHealth()));
        moneyView.setText("$" + player.getMoney());
        towerOneView.setText(Integer.toString(player.getTowerOneInv()));
        towerTwoView.setText(Integer.toString(player.getTowerTwoInv()));
        towerThreeView.setText(Integer.toString(player.getTowerThreeInv()));

        towerOneCostV.setText("$"+ Integer.toString(player.getTowerOneCost()));
        towerTwoCostV.setText("$"+ Integer.toString(player.getTowerTwoCost()));
        towerThreeCostV.setText("$"+ Integer.toString(player.getTowerThreeCost()));
    }
}
