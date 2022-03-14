package com.cs2340.towerjackets.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.cs2340.towerjackets.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
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

import com.cs2340.towerjackets.models.Monument;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.enemy.BlueEnemy;
import com.cs2340.towerjackets.models.enemy.Enemy;
import com.cs2340.towerjackets.models.enemy.GreenEnemy;
import com.cs2340.towerjackets.models.enemy.PurpleEnemy;
import com.cs2340.towerjackets.models.tower.Tower;
import com.cs2340.towerjackets.viewmodels.GameActivityViewModel;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView moneyView;
    private TextView healthView;

    private TextView towerOneView;
    private TextView towerTwoView;
    private TextView towerThreeView;

    private Button placeT1;
    private Button placeT2;
    private Button placeT3;

    private Button start;

    private Menu buyTowerMenu;

    private Player player;

    private RelativeLayout areaLayout;
    private GameActivityViewModel gameActivityViewModel;

    private boolean placed1 = false;
    private boolean placed2 = false;
    private boolean placed3 = false;

    private Monument hive;

//
//    ObjectAnimator animation;
//    ObjectAnimator animation2;
//    ObjectAnimator animation3;

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


        //Setting up the monument
        hive = new Monument();
        hive.setHealth(player.getHealth());
        startHive();


        // Populating the views with appropriate text and images
        configViews();


        // Set buttons
        start = findViewById(R.id.startCombatB);
        start.setVisibility(View.VISIBLE);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setVisibility(View.GONE);
                int x = 30;
                int y = 330;

                setValues();


                ImageView iv = new ImageView(getApplicationContext());
                Enemy curr = createEnemy(0, x, y-40, iv);
                moveEnemy(curr, iv);

                ImageView iv2 = new ImageView(getApplicationContext());
                Enemy curr2 = createEnemy(1, x-10, y, iv2);
                moveEnemy(curr2, iv2);

                ImageView iv3 = new ImageView(getApplicationContext());
                Enemy curr3 = createEnemy(2, x, y+40, iv3);
                moveEnemy(curr3, iv3);

                ImageView iv4 = new ImageView(getApplicationContext());
                Enemy curr4 = createEnemy(1, x+30, y+30, iv4);
                moveEnemy(curr4, iv4);
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

                // Setting titles of menu items
                MenuItem item = popupMenu.getMenu().findItem(R.id.HornetBuyTower);
                item.setTitle("Triple Shot $" + player.getTowerOneCost());
                item = popupMenu.getMenu().findItem(R.id.BeeBuyTower);
                item.setTitle("Honey Bomber $" + player.getTowerTwoCost());
                item = popupMenu.getMenu().findItem(R.id.WaspBuyTower);
                item.setTitle("Wasp Sniper $" + player.getTowerThreeCost());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        int idToBuy;
                        switch(id) {
                            case(R.id.HornetBuyTower):
                                idToBuy = 0;
                                break;
                            case(R.id.BeeBuyTower):
                                idToBuy = 1;
                                break;
                            case(R.id.WaspBuyTower):
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

    // Update the health, money, number of towers available on UI elements
    private void setValues() {
        Player player = InitialConfiguration.getPlayer();
        healthView.setText(Integer.toString(hive.getHealth()));
        moneyView.setText("$" + player.getMoney());
        towerOneView.setText(Integer.toString(player.getTowerOneInv()));
        towerTwoView.setText(Integer.toString(player.getTowerTwoInv()));
        towerThreeView.setText(Integer.toString(player.getTowerThreeInv()));
    }

    // Disable all buttons used to place towers
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

    private void startHive() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                for(Enemy curr: gameActivityViewModel.getListOfEnemyMonument()) {
                    if (curr.getAlive()) {
                        hive.setHealth(hive.getHealth() - 5);
                        healthView.setText(Integer.toString(hive.getHealth()));
                    }

                }
                if (hive.getHealth() <= 0) {
                    Intent intention = new Intent(GameActivity.this, GameOverActivity.class);
                    startActivity(intention);

                } else {
                    startHive();
                }
            }
        }, 1000);
    }
    private void moveEnemy(Enemy curr, ImageView iv) {

        //Move along first part of path
        final int[] moveX = new int[1];
        moveX[0] = 100;

        final ObjectAnimator[] animArr = new ObjectAnimator[3];
        animArr[0] = ObjectAnimator.ofFloat(iv, "translationX", moveX[0]);
        animArr[0].setDuration(1000);

        animArr[0].addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anima) {
                int[] location = new int[2];
                iv.getLocationOnScreen(location);
                curr.setLocationX(location[0]);
                curr.setLocationX(location[1]);
                //System.out.println(location[0]+ " IN-LOCATION " +location[1]);
                if (location[0] > 1100) {
                    animArr[1].start();
                } else {
                    //System.out.println("HERE!");
                    moveX[0] += randInt(70,130);
                    animArr[0].setFloatValues(moveX[0]);
                    animArr[0].start();
                }
            }
        });
        animArr[0].start();

        final int[] moveY = new int[1];
        moveY[0] = 100;

        animArr[1] = ObjectAnimator.ofFloat(iv, "translationY", moveY[0]);
        animArr[1].setDuration(600);
        animArr[1].addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anima) {
                int[] location = new int[2];
                iv.getLocationOnScreen(location);
                curr.setLocationX(location[0]);
                curr.setLocationX(location[1]);
                //System.out.println(location[0]+ " IN-LOCATION " +location[1]);
                if (location[1] > 800) {
                    moveX[0] = 1200;
                    animArr[2].start();
                } else {
                    //System.out.println("HERE!");
                    moveY[0] += randInt(30,70);
                    animArr[1].setFloatValues(moveY[0]);
                    animArr[1].start();
                }
            }
        });

        animArr[2] = ObjectAnimator.ofFloat(iv, "translationX", 1100);
        animArr[2].setDuration(1000);
        animArr[2].addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anima) {
                int[] location = new int[2];
                iv.getLocationOnScreen(location);
                curr.setLocationX(location[0]);
                curr.setLocationX(location[1]);
                //System.out.println(location[0]+ " IN-LOCATION " +location[1]);
                if (location[0] > 2000) {
                    iv.clearAnimation();
                    gameActivityViewModel.addEnemyMonument(curr);
                } else {
                   // System.out.println("HERE!");
                    moveX[0] += randInt(70,130);
                    animArr[2].setFloatValues(moveX[0]);
                    animArr[2].start();
                }
            }
        });


    }
    public Enemy createEnemy(int enemy, int x, int y, ImageView iv) {
        RelativeLayout.LayoutParams param = createParam();

        param.setMargins(x, y, 0, 0);
        iv.setLayoutParams(param);
        iv.getLayoutParams().width = 100;
        iv.getLayoutParams().height = 100;
        iv.requestLayout();
        if (enemy == 0) {

            iv.setImageResource(R.drawable.purple);
        } else if (enemy == 1) {
            iv.setImageResource(R.drawable.blue);
        } else if (enemy == 2) {
            iv.setImageResource(R.drawable.green);
        } else {
            throw new java.lang.IllegalArgumentException("Invalid enemy type. We only have 3 types of enemies.");
        }
        areaLayout.addView(iv);
        gameActivityViewModel.addEnemy(enemy, x, y);
        return gameActivityViewModel.getListOfEnemy().peekLast();
    }
    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
