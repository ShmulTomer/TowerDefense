package com.cs2340.towerjackets.views;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.cs2340.towerjackets.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
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

import com.cs2340.towerjackets.models.Coin;
import com.cs2340.towerjackets.models.Monument;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.enemy.Enemy;
import com.cs2340.towerjackets.models.tower.WaspTower;
import com.cs2340.towerjackets.viewmodels.GameActivityViewModel;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private TextView moneyView;
    private TextView healthView;

    private TextView towerOneView;
    private TextView towerTwoView;
    private TextView towerThreeView;

    private int numT = 3; // number of towers

    private Button[] placeT = new Button[numT];

    private Button start;

    private Player player;

    private RelativeLayout areaLayout;
    private GameActivityViewModel gameActivityViewModel;

    private boolean[] placed = {false, false, false};

    private Monument hive;

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
        hive = new Monument(player);
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
                Enemy curr = createEnemy(0, x, y - 40, iv);
                moveEnemy(curr, iv);

                ImageView iv2 = new ImageView(getApplicationContext());
                Enemy curr2 = createEnemy(1, x - 10, y, iv2);
                moveEnemy(curr2, iv2);

                ImageView iv3 = new ImageView(getApplicationContext());
                Enemy curr3 = createEnemy(2, x, y + 40, iv3);
                moveEnemy(curr3, iv3);

                ImageView iv4 = new ImageView(getApplicationContext());
                Enemy curr4 = createEnemy(1, x + 30, y + 30, iv4);
                moveEnemy(curr4, iv4);
            }
        });

        placeT[0] = findViewById(R.id.towerOneB);
        placeT[1] = findViewById(R.id.towerTwoB);
        placeT[2] = findViewById(R.id.towerThreeB);
        for (int i = 0; i < numT; i++) {
            placeT[i].setEnabled(false);
            if (player.getTowerInv(i) > 0) {
                placeT[i].setEnabled(true);
            }
        }

        // runs generateMoney() every 10 seconds (initial 5 second delay)
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                generateMoney();
                handler.postDelayed(this, 10000);
            }
        };
        handler.postDelayed(runnable, 5000);

        for (int i = 0; i < numT; i++) {
            int finalI = i;
            placeT[i].setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public void onClick(View view) {
                    enableAllFalse();
                    if (player.getTowerInv(finalI) > 0) {
                        placed[finalI] = true;
                        areaLayout.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN
                                        && placed[finalI]) {
                                    placed[finalI] = false;
                                    int x = (int) motionEvent.getX();  // get x-Coordinate
                                    int y = (int) motionEvent.getY();  // get y-Coordinate
                                    if (player.placeTower(finalI, x, y)) {
                                        RelativeLayout.LayoutParams param = createParam();
                                        ImageView iv = new ImageView(getApplicationContext());
                                        // Bee image size: 100x90 (100 is width, 90 is height)
                                        param.setMargins(x, y, 0, 0);
                                        iv.setLayoutParams(param);
                                        iv.getLayoutParams().width = 100;
                                        iv.getLayoutParams().height = 100;
                                        iv.requestLayout();
                                        if (finalI == 0) {
                                            iv.setImageResource(R.drawable.hornet);
                                        } else if (finalI == 1) {
                                            iv.setImageResource(R.drawable.bee);
                                        } else if (finalI == 2) {
                                            iv.setImageResource(R.drawable.wasp);
                                        }
                                        areaLayout.addView(iv);
                                        setValues();
                                        gameActivityViewModel.addTower(finalI, x, y);
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
    }

    private void generateMoney() {
        for (int i = 0; i < gameActivityViewModel.getListOfTower().size(); i++) {
            if (gameActivityViewModel.getListOfTower().get(i) instanceof WaspTower) {
                WaspTower t = (WaspTower) gameActivityViewModel.getListOfTower().get(i);
                RelativeLayout.LayoutParams param = createParam();
                ImageView iv = new ImageView(getApplicationContext());
                int randX = t.getLocationX() + 80 + randInt(-25, 25);
                int randY = t.getLocationY() + 80 + randInt(-25, 25);
                param.setMargins(randX, randY, 0, 0);
                iv.setLayoutParams(param);
                iv.getLayoutParams().width = 50;
                iv.getLayoutParams().height = 50;
                iv.setImageResource(R.drawable.coin);
                areaLayout.addView(iv);
                iv.requestLayout();
                Coin c = new Coin(randX, randY);
                gameActivityViewModel.addCoin(c);
                iv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        player.setMoney(player.getMoney() + c.getValue());
                        setValues();
                        iv.setVisibility(View.GONE);
                        gameActivityViewModel.getListOfCoin().remove(c);
                    }
                });
            }
        }

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
                item.setTitle("Triple Shot $" + player.getTowerCost(0));
                item = popupMenu.getMenu().findItem(R.id.BeeBuyTower);
                item.setTitle("Honey Bomber $" + player.getTowerCost(1));
                item = popupMenu.getMenu().findItem(R.id.WaspBuyTower);
                item.setTitle("Wasp Sniper $" + player.getTowerCost(2));

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        int idToBuy;
                        switch (id) {
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
        towerOneView.setText(Integer.toString(player.getTowerInv(0)));
        towerTwoView.setText(Integer.toString(player.getTowerInv(1)));
        towerThreeView.setText(Integer.toString(player.getTowerInv(2)));
    }

    // Disable all buttons used to place towers
    private void enableAllFalse() {
        for (int i = 0; i < numT; i++) {
            placeT[i].setEnabled(false);
        }
    }

    private void updateEnabled(Player player) {
        for (int i = 0; i < numT; i++) {
            if (player.getTowerInv(i) > 0) {
                placeT[i].setEnabled(true);
            }
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
                for (Enemy curr: gameActivityViewModel.getListOfEnemyMonument()) {
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
                if (location[0] > 1100) {
                    animArr[1].start();
                } else {
                    moveX[0] += randInt(70, 130);
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
                if (location[1] > 800) {
                    moveX[0] = 1200;
                    animArr[2].start();
                } else {
                    moveY[0] += randInt(30, 70);
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
                if (location[0] > 2000) {
                    iv.clearAnimation();
                    gameActivityViewModel.addEnemyMonument(curr);
                } else {
                    moveX[0] += randInt(70, 130);
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
            throw new java.lang.IllegalArgumentException("Invalid enemy type."
                    + "We only have 3 types of enemies.");
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
