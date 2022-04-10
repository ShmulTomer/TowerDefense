package com.cs2340.towerjackets.views;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.cs2340.towerjackets.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import android.view.MotionEvent;

import com.cs2340.towerjackets.models.Coin;
import com.cs2340.towerjackets.models.Monument;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.enemy.Enemy;
import com.cs2340.towerjackets.models.tower.Tower;
import com.cs2340.towerjackets.models.tower.WaspTower;
import com.cs2340.towerjackets.viewmodels.GameActivityViewModel;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.Random;

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

    private LinkedList<Integer> usedTowers = new LinkedList<>();

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
                int xArr[] = {x, x - 10, x, x + 30};
                int yArr[] = {y - 40, y, y + 40, y + 30};
                int enemyTypeArr[] = {0, 1, 2, 1};

                setValues();
                RelativeLayout.LayoutParams param = createParam();

                for (int i=0; i < 4; i++) {
                    ImageView iv = new ImageView(getApplicationContext());
                    TextView health = new TextView(getApplicationContext());
                    health.setTextColor(Color.WHITE);
                    Enemy curr = createEnemy(enemyTypeArr[i], xArr[i], yArr[i], iv, health);
                    health.setText(curr.getHealth() + "");
                    moveEnemy(curr, iv, health);
                }
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
                                        monumentHealthTower(finalI);
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

    //increment player's monument health by 20 if place a BeeTower
    private void monumentHealthTower(int towerNum) {
        healthView = findViewById(R.id.hpV);
        if (towerNum == 1) {
            int hpInt = hive.getHealth();
            hpInt += 20;
            hive.setHealth(hpInt);
            healthView.setText(Integer.toString(hpInt));
        } else {
            healthView.setText(Integer.toString(hive.getHealth()));
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

    private void moveView(Enemy curr, View iv, int rand, int rand2, int rand3, boolean isText) {

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
                if (isText) {
                    ((TextView)iv).setText(decreaseHealth(curr, location[0], location[1]) + "");
                }
                if (isHealthZero(curr)) {
                    killEnemy(curr, iv);
                }
                if (location[0] > 1150) {
                    animArr[1].start();
                } else {
                    moveX[0] += rand;
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
                if (isText) {
                    ((TextView)iv).setText(decreaseHealth(curr, location[0], location[1]) + "");
                }
                if (isHealthZero(curr)) {
                    killEnemy(curr, iv);
                }
                if (location[1] > 800) {
                    moveX[0] = 1200;
                    animArr[2].start();
                } else {
                    moveY[0] += rand2;
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
                if (isText) {
                    ((TextView)iv).setText(decreaseHealth(curr, location[0], location[1]) + "");
                }
                if (isHealthZero(curr)) {
                    killEnemy(curr, iv);
                }
                if (location[0] > 2000) {
                    iv.clearAnimation();
                    gameActivityViewModel.addEnemyMonument(curr);
                } else {
                    moveX[0] += rand3;
                    animArr[2].setFloatValues(moveX[0]);
                    animArr[2].start();
                }
            }
        });
    }

    private void moveEnemy(Enemy curr, ImageView image, TextView text) {
        int randomInt = randInt(70, 130);
        int randomInt2 = randInt(30, 70);
        int randomInt3 = randInt(70, 130);
        moveView(curr, image, randomInt, randomInt2, randomInt3, false);
        moveView(curr, text, randomInt, randomInt2, randomInt3, true);
    }

    public Enemy createEnemy(int enemy, int x, int y, ImageView iv, TextView health) {
        RelativeLayout.LayoutParams param = createParam();
        RelativeLayout.LayoutParams param2 = createParam();
        param.setMargins(x, y, 0, 0);
        iv.setLayoutParams(param);
        iv.getLayoutParams().width = 100;
        iv.getLayoutParams().height = 100;
        iv.requestLayout();
        param2.setMargins(x, y - 25, 0, 0);
        health.setLayoutParams(param2);
        health.getLayoutParams().width = 100;
        health.getLayoutParams().height = 100;
        health.requestLayout();
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
        areaLayout.addView(health);
        gameActivityViewModel.addEnemy(enemy, x, y);
        return gameActivityViewModel.getListOfEnemy().peekLast();
    }

    public void killEnemy(Enemy enemy, View iv) {
        areaLayout.removeView(iv);
    }
    public int decreaseHealth(Enemy enemy, int enemyX, int enemyY) {
        LinkedList<Tower> list = gameActivityViewModel.getListOfHornetTower();
        for (int i = 0; i < list.size(); i++) {
            if (usedTowers.contains(i)) {
                continue;
            }
            int towerX = list.get(i).getLocationX();
            int towerY = list.get(i).getLocationY();
            //Log.i("", "Enemy x: " + enemyX + "Enemy y: " + enemyY);
            //Log.i("difference", "X: " + Math.abs(enemy.getLocationX() - x) + ", Y: " + Math.abs(enemy.getLocationY() - y) + ", health" + enemy.getHealth());
            if (Math.abs(enemyY - towerY) < 200 && Math.abs(enemyX - towerX) < 100) {
                //if ((isHorizontalPath && Math.abs(enemyY - towerY) < 200) || (!isHorizontalPath && Math.abs(enemyX - towerX) < 200)) {
                int newHealth = enemy.getHealth() - 50;
                enemy.setHealth(newHealth);
                usedTowers.add(i);
                return newHealth;
            }
        }
        return enemy.getHealth();
    }

    public boolean isHealthZero(Enemy enemy) {
        if (enemy.getHealth() <= 0) {
            return true;
        }
        return false;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
