package com.cs2340.towerjackets.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cs2340.towerjackets.R;

public class GameWinActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_win);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button playAgain = findViewById(R.id.playAgainB);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(GameWinActivity.this, InitialConfiguration.class);
                startActivity(intention);
            }
        });

        Button quitButton = findViewById(R.id.quitB);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(GameWinActivity.this, MainActivity.class);
                startActivity(intention);
            }
        });
    }
}
