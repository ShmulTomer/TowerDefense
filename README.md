# TowerDefense - "Hive Defense Game"

## M4 Testing Writeup

1. waspTowerUpgrade() - Anh Le
* This tests the functionality of a wasp tower upgrade. The coins the tower initially drop have a
 value of 10, but after an upgrade, the value of the coins increases to 20. This functionality is
 important because it provides a way for the user to upgrade the towers they've bought, which increases
 their defense against enemies.

2. beeTowerUpgrade() - Anh Le
* This tests the functionality of a bee tower upgrade. The user's health is increased when the tower
 is first placed, but upgrading the tower increases the health even more. This functionality is
 important because it provides a way for the user to upgrade the towers they've bought, which increases
 their defense against enemies.

3. hornetTowerUpgrade() - Helen Chen
* This tests the functionality of a hornet tower upgrade. After the hornet tower is upgraded, the damage
done to enemies increases. This functionality is important because it provides a way for the user to upgrade
the towers they've bought, which increases their defense against enemies.

4. purchaseTowerUpgrade() - Helen Chen
* This tests whether or not purchases a tower upgrade decreases the player's money and upgrades the tower.
This ensures that tower upgrades are bought and not freely applied, and also ensures that once a tower
is upgraded, it gets added to the count of upgraded towers.

5. allowedTowerUpgrade() - Tomer Shmul
* This tests whether or not a tower can be upgraded. Only towers that have been purchased can be upgraded,
so a tower must be first purchased before the upgrade is made. This is an important feature because
the user should not be allowed to directly purchase an upgraded tower.

6. finalBossDifficulty() - Tomer Shmul
* This test checks the difficulty and health of the final boss and ensures that the final boss is stronger
than a typical enemy. This is important because the end goal of the game is to defeat the final boss,
so to increase the difficulty of the game, the final boss should be more difficult to defeat than previous
enemies.

7. gameOverAfterBoss() - Harriet Kim
* This test checks that once the boss dies, the game is over and the user wins. It also checks to make sure
that the monument is alive after the boss dies, since the monument must be alive for the user to win.
This functionality is important because it is the only way the user can win the game (by killing the boss
and all previous enemies).

8. updateEnemiesDefeated() - Harriet Kim
* Since the statistics need to be shown at the end of the game, this test checks to make sure that the
number of enemies defeated is consistently updated throughout the game. This is done through a static variable
in the enemy class. This functionality is important because it lets the user know how many enemies
they have defeated by the end of the game.

9. updateTowerInventory() - Ori Yoked
* Since the statistics need to shown at the end of the game, this test checks to make sure that the
tower inventory is consistently updated. At the end of the game, the user will be able to see how many
towers of each type they have purchased along with the total number of towers purchased. This functionality
is important because it lets the user know how many towers they have purchased by the end of the game.

10. updateTotalMoney() - Ori Yoked
* Since the statistics need to be shown at the end of the game, this test checks to make sure that the
user's money is consistently updated throughout the game. First, the purchase of a tower should decrease
the user's money. The upgrade of a tower should also decrease the money. Finally, collecting a coin should
increase the user's money. This feature is important because it lets the user know how much money they
have by the end of the game.