package com.cs2340.towerjackets.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.cs2340.towerjackets.R;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.MotionEvent;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.tower.Tower;
import com.cs2340.towerjackets.viewmodels.GameActivityViewModel;

public class GameActivity extends AppCompatActivity {
    private TextView moneyView;
    private TextView healthView;

    private TextView towerOneView;
    private TextView towerTwoView;
    private TextView towerThreeView;

    private Button placeT1;
    private Button placeT2;
    private Button placeT3;

    Player player;

    private RelativeLayout areaLayout;
    private GameActivityViewModel gameActivityViewModel;

    private boolean placed1 = false;
    private boolean placed2 = false;
    private boolean placed3 = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        gameActivityViewModel = new ViewModelProvider(this).get(GameActivityViewModel.class);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        areaLayout = findViewById(R.id.relativeLayout);
        player = InitialConfiguration.getPlayer();
        configViews();

        // Set buttons
        placeT1 = findViewById(R.id.towerOneB);
        placeT1.setEnabled(false);
        if (player.getTowerOneInv() > 0) {
            placeT1.setEnabled(true);
        }

        placeT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableAllFalse();
                if (player.getTowerOneInv() > 0) {
                    placed1 = true;
                    areaLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && placed1) {
                                placed1 = false;
                                int x = (int) motionEvent.getX();  // get x-Coordinate
                                int y = (int) motionEvent.getY();  // get y-Coordinate
                                if (player.placeTower(0, x, y)) {
                                    RelativeLayout.LayoutParams param = createParam();
                                    ImageView iv = new ImageView(getApplicationContext());
                                    // Size of bee image: 100x90 where 100 is width, 90 is height
                                    param.setMargins(x, y, 0, 0);
                                    iv.setLayoutParams(param);
                                    iv.getLayoutParams().width = 100;
                                    iv.getLayoutParams().height = 100;
                                    iv.requestLayout();
                                    iv.setImageResource(R.drawable.hornet);
                                    areaLayout.addView(iv);
                                    setValues();
                                    gameActivityViewModel.addTower(0, x, y);
                                } else {
                                    alertPath();
                                }
                                updateEnabled(player);
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
                enableAllFalse();
                if (player.getTowerTwoInv() > 0) {
                    setValues();
                    placed2 = true;
                    areaLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && placed2) {
                                placed2 = false;
                                int x = (int) motionEvent.getX();  // get x-Coordinate
                                int y = (int) motionEvent.getY();  // get y-Coordinate
                                if (player.placeTower(1, x, y)) {
                                    RelativeLayout.LayoutParams param = createParam();
                                    ImageView iv = new ImageView(getApplicationContext());
                                    // Size of bee image: 100x90 where 100 is width, 90 is height
                                    param.setMargins(x, y, 0, 0);
                                    iv.setLayoutParams(param);
                                    iv.getLayoutParams().width = 100;
                                    iv.getLayoutParams().height = 100;
                                    iv.requestLayout();
                                    iv.setImageResource(R.drawable.bee);
                                    areaLayout.addView(iv);
                                    setValues();
                                    gameActivityViewModel.addTower(1, x, y);
                                } else {
                                    alertPath();
                                }
                                updateEnabled(player);
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
                enableAllFalse();
                if (player.getTowerThreeInv() > 0) {
                    setValues();
                    placed3 = true;
                    areaLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && placed3) {
                                placed3 = false;
                                int x = (int) motionEvent.getX();  // get x-Coordinate
                                int y = (int) motionEvent.getY();  // get y-Coordinate
                                if (player.placeTower(2, x, y)) {
                                    RelativeLayout.LayoutParams param = createParam();
                                    ImageView iv = new ImageView(getApplicationContext());
                                    // Size of bee image: 100x90 where 100 is width, 90 is height
                                    param.setMargins(x, y, 0, 0);
                                    iv.setLayoutParams(param);
                                    iv.getLayoutParams().width = 100;
                                    iv.getLayoutParams().height = 100;
                                    iv.requestLayout();
                                    iv.setImageResource(R.drawable.wasp);
                                    areaLayout.addView(iv);
                                    setValues();
                                    gameActivityViewModel.addTower(2, x, y);
                                } else {
                                    alertPath();
                                }
                                updateEnabled(player);
                            }
                            return true;
                        }
                    });
                }
            }
        });
    }

    private void configViews() {
        moneyView = findViewById(R.id.moneyV);
        healthView = findViewById(R.id.hpV);
        towerOneView = findViewById(R.id.towerOneV);
        towerTwoView = findViewById(R.id.towerTwoV);
        towerThreeView = findViewById(R.id.towerThreeV);
        setValues();

        ImageButton menuButton = findViewById(R.id.towerMenuB);
        // Add event listeners for button
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(menuButton.getContext(), view);
                popupMenu.inflate(R.menu.popup_buytower);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        int idToBuy;
                        switch(id) {
                            case(R.id.Hornet):
                                idToBuy = 0;
                                break;
                            case(R.id.Bee):
                                idToBuy = 1;
                                break;
                            case(R.id.Wasp):
                                idToBuy = 2;
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid item to buy.");
                        }
                        player.buyTower(idToBuy);
                        setValues();
                        updateEnabled(player);
                        return true;
                    }
                });
                popupMenu.show();
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

    private void enableAllFalse() {
        placeT1.setEnabled(false);
        placeT2.setEnabled(false);
        placeT3.setEnabled(false);
    }

    private void updateEnabled(Player player) {
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

    private void alertPath() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(GameActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Invalid Tower Placement");
        builder.setMessage("You placed the tower on the path."
                + " Try again.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private RelativeLayout.LayoutParams createParam() {
        RelativeLayout.LayoutParams param =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        return param;
    }

}