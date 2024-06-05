package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GamePauseMenuTest {

    @Test
    public void testToUpdateWhenNotActive() {
        GamePauseMenu menu = new GamePauseMenu();
        GameData gameData = new GameData();
        gameData.setInput(GameInput.ESC);
        gameData.setGameState(GameState.RUNNING);
        GameData updatedGameData = menu.update(gameData);
        assertFalse(menu.toDelete());
        assertEquals(gameData, updatedGameData);
    }

    @Test
    public void testToUpdateWhenActive() {
        GamePauseMenu menu = new GamePauseMenu();
        GameData gameData = new GameData();
        gameData.setInput(GameInput.ESC);
        gameData.setGameState(GameState.PAUSE_MENU);
        GameData updatedGameData = menu.update(gameData);
        assertFalse(menu.toDelete());
    }

    @Test
    public void testToDelete() {
        GamePauseMenu menu = new GamePauseMenu();
        assertFalse(menu.toDelete());
    }
}
