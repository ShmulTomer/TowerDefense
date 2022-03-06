package com.cs2340.towerjackets;

import org.junit.Test;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.game_config.Difficulty;
import com.cs2340.towerjackets.models.game_config.GameConfiguration;

import static org.junit.Assert.*;

import android.widget.Button;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void additionIsCorrect() {
        assertEquals(4, 2 + 2);
    }

    // M3 - Anh Le - This tests whether our program would throw an exception when the input name is null
    @Test (expected = IllegalArgumentException.class)
    public void nameIsNull() {
        Difficulty diff = Difficulty.Easy;
        // This test should pass since the following line should throw an IllegalArgumentException
        Player newPlayer = new Player(null, new GameConfiguration(diff));
    }

    // M3 - Anh Le - This tests whether our program would correctly store the information input by the users
    // Tested on all difficulties. Tested with 3 different input name strings.
    @Test
    public void testNameDifficultyCorrectlyStored() {
        Difficulty diff1 = Difficulty.Easy;
        Player newPlayer1 = new Player("Bob", new GameConfiguration(diff1));
        assertEquals("Bob", newPlayer1.getName());
        assertEquals(Difficulty.Easy, newPlayer1.getConfig().getGameDifficulty());

        Difficulty diff2 = Difficulty.Normal;
        Player newPlayer = new Player("Agatha", new GameConfiguration(diff2));
        assertEquals("Agatha", newPlayer.getName());
        assertEquals(Difficulty.Normal, newPlayer.getConfig().getGameDifficulty());

        Difficulty diff3 = Difficulty.Hard;
        Player newPlayer3 = new Player("Calvin", new GameConfiguration(diff3));
        assertEquals("Calvin", newPlayer3.getName());
        assertEquals(Difficulty.Hard, newPlayer3.getConfig().getGameDifficulty());
    }

    // M3 - Helen Chen - This tests whether the purchase of a tower in the shop is stored in the Player.
    // Tested at easy and normal difficulty with tower two and tower one respectively.
    @Test
    public void testTowerPurchaseStored() {
        Player player1 = new Player("tower tester", new GameConfiguration(Difficulty.Easy));
        player1.initialConfiguration(0); // easy
        player1.buyTower(1); // tower two
        assertEquals(20, player1.getMoney());
        assertEquals(1, player1.getTowerTwoInv());

        Player player2 = new Player("tower tester", new GameConfiguration(Difficulty.Normal));
        player2.initialConfiguration(1); // normal
        player2.buyTower(0); // tower one
        assertEquals(25, player2.getMoney());
        assertEquals(1, player2.getTowerOneInv());
    }

    // M3 - Helen Chen - This tests whether a purchase with insufficient funds is processed or not.
    // Tested at hard difficulty, tower three. Expects not purchasable.
    @Test
    public void testTowerPurchaseInsufficientFunds() {
        Player player3 = new Player("tower tester", new GameConfiguration(Difficulty.Hard));
        player3.initialConfiguration(2);
        player3.buyTower(2); // tower three
        assertEquals(50, player3.getMoney());
        assertEquals(0, player3.getTowerThreeInv());
    }

}