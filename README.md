# TowerDefense - "Hive Defense Game"

## M4 Testing Writeup

1. beeTowerFunctionality() - Anh Le
* This test checks whether placing a Bee Tower on the board successfully increases monument health.
The purpose of the bee tower is to increase monument health, so each time the tower is placed, monument
health should be increased by 20. This is one of the mechanisms that helps the player defeat the enemies.

2. enemiesHealthZero() - Anh Le
* This test checks to make sure that enemies disappear from the map when their health reaches zero. This
is accomplished by removing the enemy from the gameActivityViewModel Linked List. When the health of an
enemy is zero, they are automatically removed the list of enemies in the game.

3. enemyNotWithinProximity() - Helen Chen
* This test checks whether or not a Hornet Tower can attack an enemy not within proximity. This is
important since the hornet tower is responsible for attacking enemies, but should only be allowed to
decrease enemy damage when the enemy is close enough.

4. enemyWithinProximity() - Helen Chen
* This test checks whether or not a Hornet Tower can attack an enemy within promixity. This ensures that
there is a way for enemy health to decrease, and thus for the enemy to eventually die before reaching
the monument.

5. waspTowerFunctionality() - Ori Yoked
* This test checks whether placing a Wasp Tower generates a coin or not. The Wasp Tower is the only way
the player can gain money throughout the game to purchase more towers, so this functionality is extremely
important. Placing the tower should generate a coin.

6. coinFunctionality() - Ori Yoked
* This test checks whether or not collecting the coin increases the player money. After the wasp tower
generates the coin, the user is in charge of collecting the coin so the player money actually increases.

7. enemyAttackedTwice() - Tomer Shmul
* This test checks whether a hornet tower can attack an enemy twice. Since a tower should be able to attack
any enemy within proximity, it should be able to attack an enemy more than once, even if the enemy moves.
This test ensures that even after an enemy moves, the tower is still able to attack the enemy.

8. () - Tomer Shmul
*

9. () - Harriet Kim
* 

10. () - Harriet Kim
*