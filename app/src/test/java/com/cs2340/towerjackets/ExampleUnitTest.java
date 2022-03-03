package com.cs2340.towerjackets;

import org.junit.Test;
import com.cs2340.towerjackets.models.Player;
import com.cs2340.towerjackets.models.game_config.Difficulty;
import com.cs2340.towerjackets.models.game_config.GameConfiguration;

import static org.junit.Assert.*;

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

    // Anh Le - This tests whether our program would throw an exception when the input name is null
    @Test (expected = IllegalArgumentException.class)
    public void nameIsNull() {
        Difficulty diff = Difficulty.Easy;
        // This test should pass since the following line should throw an IllegalArgumentException
        Player newPlayer = new Player(null, new GameConfiguration(diff));
    }

    // Anh Le - This tests whether our program would correctly store the information input by the users
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

}