# TowerDefense

## M3 Testing Writeup

1. nameIsNull() - Anh Le
*

2. testNameDifficultyCorrectlyStored() - Anh Le
* 

3. testTowerPurchaseStored() - Helen Chen
* This test tests whether the purchase of a tower in the shop is correctly stored in the Player.
Two players are created, one at easy difficulty and the other at normal difficulty.
The player at easy difficulty buys tower two and the player at normal difficulty buys tower one.
This test also shows that tower costs vary based on difficulty level.

4. testTowerPurchaseInsufficientFunds() - Helen Chen
* This test tests whether or not towers can be purchased with insufficient funds.
A player with hard difficulty is created and an attempt to purchase tower three is made four times.
However, the player will no longer have sufficient funds after three purchases. Therefore, it is
expected that the player can only purchase three towers, not four.

5. testChangingPlayerHealth() - Tomer Shmul
* This test tests the functionality of adjusting the player's health throughout the game. This tests
both changing and getting the player's health, which has functionality beyond just setter and getter.
When changing the health, if the health falls below 0, it will default the health to 0, as the player
should not have negative health.

6. testChangingPlayerMoney() - Tomer Shmul
* This test tests the functionality of adjusting the player's money, which is an essential
functionality for the tower features, such as buying (for this milestone thus far). Therefore,
it is important to make sure that this core functionality remains consistent with this unit test.

6.