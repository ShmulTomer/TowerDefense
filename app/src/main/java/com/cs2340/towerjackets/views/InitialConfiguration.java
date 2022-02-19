package com.cs2340.towerjackets.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cs2340.towerjackets.R;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.game_config.Difficulty;
import com.cs2340.towerjackets.models.game_config.GameConfiguration;

public class InitialConfiguration extends AppCompatActivity {
    private Spinner difficultySpinner;
    private EditText nameField;
    private Button continueButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_config);

        // Configure the difficulty spinner
        difficultySpinner = findViewById(R.id.difficultySpinner);
        final ArrayAdapter<Difficulty> difficulties = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Difficulty.values());
        difficulties.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficulties);


        // Configure the nameField
        nameField = findViewById(R.id.promptForName);

        // Configure the "Continue" button
        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Difficulty diffSelected = (Difficulty) difficultySpinner.getSelectedItem();
                GameConfiguration config = new GameConfiguration(diffSelected);
                Player newPlayer = new Player(nameField.getText().toString(), config);

                // open game activity screen
                Intent intention = new Intent(InitialConfiguration.this, GameActivity.class);
                startActivity(intention);
            }
        });
    }
}
