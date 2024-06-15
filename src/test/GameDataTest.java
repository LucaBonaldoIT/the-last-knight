package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameDataTest {

    private static GameData gameData;

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        assertNotNull(gameData.getGameSetting());
        assertNotNull(gameData.getPlayerData());
        assertNotNull(gameData.getPauseMenu());
        assertEquals(GameState.READY, gameData.getGameState());
        assertEquals(GameInput.NONE, gameData.getInput());
        assertEquals(GameRoom.NONE, gameData.getGameRoom());
        assertNotNull(gameData.getGameObjects());
        assertNotNull(gameData.getInventory());
        assertNotNull(gameData.getPlayer());
    }

    @BeforeAll
    public static void setUp() {
        gameData = new GameData();
    }

    @Test
    public void testGetCurrentLevel() {
        GameLevel level = new GameLevel();
        gameData.loadLevel(level);
        assertEquals(level, gameData.getCurrentLevel());
    }

    @Test
    public void testSetAndGetPlayerData() {
        GamePlayerData playerData = new GamePlayerData();
        gameData.setPlayerData(playerData);
        assertEquals(playerData, gameData.getPlayerData());
    }

    @Test
    public void testGetSave() {
        GameDataSave save = gameData.getSave();
        assertEquals(gameData.getCurrentLevel().getLevelIndex(), save.getCurrentLevel());
        assertEquals(gameData.getPlayerData(), save.getPlayerData());
    }
    @Test
    public void testSetAndGetGameState() {
        gameData.setGameState(GameState.RUNNING);
        assertEquals(GameState.RUNNING, gameData.getGameState());
    }

    @Test
    public void testSetAndGetGameRoom() {
        GameRoom room = new GameRoom();
        gameData.setGameRoom(room);
        assertEquals(room, gameData.getGameRoom());
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
