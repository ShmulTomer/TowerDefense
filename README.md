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