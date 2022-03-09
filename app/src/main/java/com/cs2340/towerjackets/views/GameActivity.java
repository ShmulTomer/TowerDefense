package com.cs2340.towerjackets.views;

import androidx.appcompat.app.AppCompatActivity;
import com.cs2340.towerjackets.R;
import android.os.Bundle;
import android.content.Intent;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

    private Button placeT1;
    private Button placeT2;
    private Button placeT3;

    private RelativeLayout areaLayout;

    private boolean placed1 = false;
    private boolean placed2 = false;
    private boolean placed3 = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        areaLayout = findViewById(R.id.relativeLayout);
        ImageButton menuButton = findViewById(R.id.towerMenuB);
        Player player = InitialConfiguration.getPlayer();

        moneyView = findViewById(R.id.moneyV);
        healthView = findViewById(R.id.hpV);
        towerOneView = findViewById(R.id.towerOneV);
        towerTwoView = findViewById(R.id.towerTwoV);
        towerThreeView = findViewById(R.id.towerThreeV);
        setValues();

        // Add event listeners for button
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(GameActivity.this, TowerActivity.class);
                startActivity(intention);
            }
        });

        placeT1 = findViewById(R.id.towerOneB);
        placeT1.setEnabled(false);
        if (player.getTowerOneInv() > 0) {
            placeT1.setEnabled(true);
        }
        placeT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeT1.setEnabled(false);
                placeT2.setEnabled(false);
                placeT3.setEnabled(false);
                if (player.getTowerOneInv() > 0) {
                    player.setTowerOneInv(player.getTowerOneInv() - 1);
                    setValues();
                    placed1 = true;
                    areaLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && placed1) {
                                placed1 = false;
                                
                                int x = (int) motionEvent.getX();  // get x-Coordinate
                                int y = (int) motionEvent.getY();  // get y-Coordinate
                                RelativeLayout.LayoutParams param =
                                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                                ImageView iv = new ImageView(getApplicationContext());
                                // Hard coded the size of the image in - that's where 100 and 90 came in.
                                // Size of bee image: 100x90 where 100 is width, 90 is height
                                param.setMargins(x, y, 0, 0);
                                iv.setLayoutParams(param);
                                iv.getLayoutParams().width = 100;
                                iv.getLayoutParams().height = 100;
                                iv.requestLayout();
                                iv.setImageResource(R.drawable.hornet);
                                areaLayout.addView(iv);
                                if (player.getTowerOneInv() > 0) {
                                    placeT1.setEnabled(true);
                                }
                                if (player.getTowerTwoInv() > 0) {
                                    placeT2.setEnabled(true);
                                }
                                if (player.getTowerThreeInv() > 0) {
                                    placeT3.setEnabled(true);
                                }
                            }
                            return true;
                        }
                    });
                }
            }
        });

        placeT2 = findViewById(R.id.towerTwoB);
        placeT2.setEnabled(false);
        if (player.getTowerTwoInv() > 0) {
            placeT2.setEnabled(true);
        }
        placeT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeT1.setEnabled(false);
                placeT2.setEnabled(false);
                placeT3.setEnabled(false);
                if (player.getTowerTwoInv() > 0) {
                    player.setTowerTwoInv(player.getTowerTwoInv() - 1);
                    setValues();
                    placed2 = true;
                    areaLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && placed2) {
                                placed2 = false;
                                int x = (int) motionEvent.getX();  // get x-Coordinate
                                int y = (int) motionEvent.getY();  // get y-Coordinate
                                RelativeLayout.LayoutParams param =
                                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                                ImageView iv = new ImageView(getApplicationContext());
                                // Hard coded the size of the image in - that's where 100 and 90 came in.
                                // Size of bee image: 100x90 where 100 is width, 90 is height
                                param.setMargins(x, y, 0, 0);
                                iv.setLayoutParams(param);
                                iv.getLayoutParams().width = 100;
                                iv.getLayoutParams().height = 100;
                                iv.requestLayout();
                                iv.setImageResource(R.drawable.bee);
                                areaLayout.addView(iv);
                                if (player.getTowerOneInv() > 0) {
                                    placeT1.setEnabled(true);
                                }
                                if (player.getTowerTwoInv() > 0) {
                                    placeT2.setEnabled(true);
                                }
                                if (player.getTowerThreeInv() > 0) {
                                    placeT3.setEnabled(true);
                                }
                            }
                            return true;
                        }
                    });
                }
            }
        });

        placeT3 = findViewById(R.id.towerThreeB);
        placeT3 = findViewById(R.id.towerThreeB);
        placeT3.setEnabled(false);
        if (player.getTowerThreeInv() > 0) {
            placeT3.setEnabled(true);
        }
        placeT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeT1.setEnabled(false);
                placeT2.setEnabled(false);
                placeT3.setEnabled(false);
                if (player.getTowerThreeInv() > 0) {
                    player.setTowerThreeInv(player.getTowerThreeInv() - 1);
                    setValues();
                    placed3 = true;
                    areaLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && placed3) {
                                placed3 = false;
                                int x = (int) motionEvent.getX();  // get x-Coordinate
                                int y = (int) motionEvent.getY();  // get y-Coordinate
                                RelativeLayout.LayoutParams param =
                                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                                ImageView iv = new ImageView(getApplicationContext());
                                // Hard coded the size of the image in - that's where 100 and 90 came in.
                                // Size of bee image: 100x90 where 100 is width, 90 is height
                                param.setMargins(x, y, 0, 0);
                                iv.setLayoutParams(param);
                                iv.getLayoutParams().width = 160;
                                iv.getLayoutParams().height = 160;
                                iv.requestLayout();
                                iv.setImageResource(R.drawable.wasp);
                                areaLayout.addView(iv);
                                if (player.getTowerOneInv() > 0) {
                                    placeT1.setEnabled(true);
                                }
                                if (player.getTowerTwoInv() > 0) {
                                    placeT2.setEnabled(true);
                                }
                                if (player.getTowerThreeInv() > 0) {
                                    placeT3.setEnabled(true);
                                }
                            }
                            return true;
                        }
                    });
                }
            }
        });
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