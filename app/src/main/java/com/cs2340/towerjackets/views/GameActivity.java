package com.cs2340.towerjackets.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.cs2340.towerjackets.R;
import com.cs2340.towerjackets.models.game_config.Difficulty;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView monumentField;
    private TextView moneyField;
    private int monumentHealth;
    private int money;
    private ImageView[][] path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        hideSystemBars();

        // initialize and configure path
        path = new ImageView[10][20];
        configurePath(path);

        // initialize monument and money field and set values by difficulty
        monumentField = findViewById(R.id.monumentValue);
        moneyField = findViewById(R.id.moneyValue);
        setValuesByDifficulty();

    }

    // setting width and height of each image based on screen width and height
    private void configurePath(ImageView[][] path) {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y - 100;

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                String imageID = "iv" + i + "_" + j;
                int resID = getResources().getIdentifier(imageID, "id", getPackageName());
                path[i][j] = findViewById(resID);
                Log.d("test", "total height is " + height + " and total width is " + width);
                // path[i][j].getLayoutParams().height = height / path.length;
                // path[i][j].getLayoutParams().width = width / path[i].length;
                path[i][j].getLayoutParams().height = 108; // currently hard coded, works for Virtual Device Pixel 4 XL
                path[i][j].getLayoutParams().width = 144; // currently hard coded, works for Virtual Device Pixel 4 XL
                Log.d("test", "" + path[i][j].getLayoutParams().height + " " + path[i][j].getLayoutParams().width);
            }
        }
    }

    // setting monument health and starting money based on difficulty level
    private void setValuesByDifficulty() {
        Difficulty difficulty = InitialConfiguration.getPlayer().getConfig().getGameDifficulty();
        if (difficulty.ordinal() == 0) { // easy
            monumentHealth = 100;
            money = 100;
        } else if (difficulty.ordinal() == 1) { // normal
            monumentHealth = 80;
            money = 80;
        } else if (difficulty.ordinal() == 2) { // hard
            monumentHealth = 50;
            money = 50;
        }
        monumentField.setText(Integer.toString(monumentHealth));
        moneyField.setText("$" + money);
    }

    // https://developer.android.com/training/system-ui/immersive
    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        // Configure the behavior of the hidden system bars
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
    }

}