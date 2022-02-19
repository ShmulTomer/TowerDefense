package com.cs2340.towerjackets.views;

import androidx.appcompat.app.AppCompatActivity;
import com.cs2340.towerjackets.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        ImageView[][] path = new ImageView[10][20];

        // setting width and height of each image based on screen width and height
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                String imageID = "iv" + i + "_" + j;
                int resID = getResources().getIdentifier(imageID, "id", getPackageName());
                path[i][j] = ((ImageView) findViewById(resID));
                path[i][j].setMaxWidth(width / 20);
                path[i][j].setMaxHeight(height / 10);
            }
        }
    }
}