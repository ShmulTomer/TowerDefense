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

    // Anh Le
    @Test (expected = IllegalArgumentException.class)
    public void nameIsNull() {
        Difficulty diff = Difficulty.Easy;
        // This test should pass since the following line should throw an IllegalArgumentException
        Player newPlayer = new Player(null, new GameConfiguration(diff));
    }

    // Anh Le
    @Test
    public void testNameDifficultyCorrectlyStored() {
        Difficulty diff = Difficulty.Normal;
        Player newPlayer = new Player("Agatha", new GameConfiguration(diff));
        assertEquals("Agatha", newPlayer.getName());
        assertEquals(Difficulty.Normal, newPlayer.getConfig().getGameDifficulty());
    }

}