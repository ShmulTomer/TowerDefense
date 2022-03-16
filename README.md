# TowerDefense - "Hive Defense Game"

## M4 Testing Writeup

1. enemyOnPath() - Anh Le
* This test checks

2. enemiesOnMonument() - Anh Le
* This test checks

3. differentEnemyHealth() - Helen Chen
* This test checks whether the three different enemies in the game have different starting healths.
This is one of the main differences between the different types of enemies that will cause them to
impact combat differently.

4. reduceMonumentHealth() - Helen Chen
* This test checks if different types of enemies hitting the monument reduces the monument health by different
damages. It is tested on three different types of enemies: the blue enemy, purple enemy, and green enemy.
  
5. differentEnemyDamage() - Ori Yoked
* This test checks that all the enemies have different damages. This is important because we want to differentiate between how much damage the different types of enemies can have on the towers and the monument, which is just one of the characteristic differences between all three enemies. This allows for the user of the game to strategize on which enemies to try to destroy first.

6. deadEnemies() - Ori Yoked
* This test checks that when an enemy loses all its health, it is no longer able to damage the monument. This is essential because we want enemies to damage the monument when they reach it, but once it's dead, the enemy should not have any functionality at all. This test properly tests this by showing that when an enemy is alive and reaches the monument, it does 200 damage to the monument, while a dead enemy does no damage as shown by the full health of the monument.

7. checkGameStatus() - Tomer Shmul
* This test checks whether doing enough damage to the monument causes the game to end. This is
essential as the game ending properly is a critical function of the game, one that we want to make
sure always works through iterations of this project. Therefore, this JUnit is essential to check
to make sure the game is properly functioning, and that enough enemies damaging the hive causes the
game to enter a game over state.

8. towersGameOver() - Tomer Shmul
* This test checks if the placeEnemy() method places enemy randomly. This is important because when
the game begins, if the enemies are overlapping, then only one enemy will be shown. Therefore, it is
crucial that enemies are separated and not in the same starting position when the game starts
for the visual functionality of this game.

9. differentEnemySpeeds() - Harriet Kim
* This test checks

10. monumentHealthZero() - Harriet Kim
* This test checks
