package com.cs2340.towerjackets.views;

import androidx.appcompat.app.AppCompatActivity;
import com.cs2340.towerjackets.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import android.view.MotionEvent;
import com.cs2340.towerjackets.models.Player;

public class GameActivity extends AppCompatActivity {
    private TextView moneyView;
    private TextView healthView;

    private TextView towerOneView;
    private TextView towerTwoView;
    private TextView towerThreeView;

    private RelativeLayout areaLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        areaLayout = findViewById(R.id.relativeLayout);
        ImageButton menuButton = findViewById(R.id.towerMenuB);

        // Add event listeners for button
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(GameActivity.this, TowerActivity.class);
                startActivity(intention);
            }
        });

        areaLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    int x = (int) motionEvent.getX();  // get x-Coordinate
                    int y = (int) motionEvent.getY();  // get y-Coordinate
                    RelativeLayout.LayoutParams param =
                            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                    ImageView iv = new ImageView(getApplicationContext());
                    param.setMargins(x, y, 0, 0);
                    iv.setLayoutParams(param);
                    iv.setImageResource(R.drawable.enemy);
                    areaLayout.addView(iv);
                }
                return true;
            }
        });
        moneyView = findViewById(R.id.moneyV);
        healthView = findViewById(R.id.hpV);
        towerOneView = findViewById(R.id.towerOneV);
        towerTwoView = findViewById(R.id.towerTwoV);
        towerThreeView = findViewById(R.id.towerThreeV);

        setValues();


    }

    private void setValues() {
        Player player = InitialConfiguration.getPlayer();
        healthView.setText(Integer.toString(player.getHealth()));
        moneyView.setText("$" + player.getMoney());
        towerOneView.setText(Integer.toString(player.getTowerOneInv()));
        towerTwoView.setText(Integer.toString(player.getTowerTwoInv()));
        towerThreeView.setText(Integer.toString(player.getTowerThreeInv()));
    }
}