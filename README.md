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
  
5. testPlaceTowerDecreaseInv() - Hyun Soo (Harriet) Kim
* This test tests whether the placement of a tower decreases the tower inventory in the Player.
A player with easy difficulty is created. We attempt to purchase tower one one time and 
  tower two two times. Then, we attempt to place tower one and tower two one time each. Then we 
  expect the tower inventory of the tower one be 1 - 1 = 0 and that of the tower two be 2 - 1 = 1.

6. testPlaceTowerInsufficientInv() - Hyun Soo (Harriet) Kim
* This test tests whether a placement with insufficient inventory is processed or not. A player with 
normal difficulty is created. We attempt to purchase tower three two times and place tower three 
  three times. However, the player will no longer have sufficient inventory after two placements. 
  Therefore, it is expected that the player can only place two towers, not three.


7. testChangingPlayerHealth() - Tomer Shmul
* This test tests the functionality of adjusting the player's health throughout the game. This tests
both changing and getting the player's health, which has functionality beyond just setter and getter.
When changing the health, if the health falls below 0, it will default the health to 0, as the player
should not have negative health.

8. testChangingPlayerMoney() - Tomer Shmul
* This test tests the functionality of adjusting the player's money, which is an essential
functionality for the tower features, such as buying (for this milestone thus far). Therefore,
it is important to make sure that this core functionality remains consistent with this unit test.

9. testBuyTowerIncreaseInv() - Ori Yoked
* This test tests whether the placement of a tower increases the tower inventory in the Player. A
player with easy difficulty is created. We attempt to purchase tower one one time and tower two two times. Then, we expect the tower inventory of the tower one be 1 and that of the tower two be 2. This functionality is important because if the inventory doesn't increase when a tower is bought, the player will never be able to place a tower and defeat the enemies.

10. testCorrectHealthForDifficulty() - Ori Yoked
* This test tests the functionality of having a different starting health when choosing a difficulty for the game. This is meaningful because the health is important for combating enemies and keeping the monument safe, so we want to ensure that this is more or less difficult to do based on what the player chooses. Therefore, the player's health should be different for the easy, normal, and hard difficulty.
