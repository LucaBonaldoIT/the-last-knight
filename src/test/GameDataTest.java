package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameDataTest {

    private static GameData gameData;

    @BeforeAll
    public static void setUp() {
        gameData = new GameData();
    }

    @Test
    public void testClearScene() {
        gameData.clearScene();
        assertTrue(gameData.getGameObjects().isEmpty());
    }

    @Test
    public void testSetInput() {
        gameData.setInput(GameInput.UP);
        assertEquals(GameInput.UP, gameData.getInput());
    }

    @Test
    public void testLoadLevel() {
        GameLevel level = new GameLevel();
        gameData.loadLevel(level);
        assertEquals(level, gameData.getCurrentLevel());
    }
}