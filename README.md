# TowerDefense - "Hive Defense Game"

## M4 Testing Writeup

1. enemyOnPath() - Anh Le
* This test checks

2. enemiesOnMonument() - Anh Le
* This test checks

3. differentEnemyHealth() - Helen Chen
* This test checks

4. reduceMonumentHealth() - Helen Chen
* This test checks
  
5. differentEnemyDamage() - Ori Yoked
* This test checks

6. deadEnemies() - Ori Yoked
* This test checks

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
