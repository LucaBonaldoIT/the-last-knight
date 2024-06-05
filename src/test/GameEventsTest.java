package Test;

import main.java.com.mealforks.thelastknight.game.GameData;
import main.java.com.mealforks.thelastknight.game.GameEvents;
import main.java.com.mealforks.thelastknight.game.GameState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GameEventsTest {

    @Test
    void testLoadLevel1() {
        GameData data = new GameData();
        GameEvents.LoadLevel1(data);
        assertNotNull(data.getCurrentLevel());

        assertEquals(GameState.RUNNING, data.getGameState());
    }

    @Test
    void testLoadLevel2() {
        GameData data = new GameData();
        GameEvents.LoadLevel2(data);
        assertNotNull(data.getCurrentLevel());

        assertEquals(GameState.RUNNING, data.getGameState());
    }
    @Test
    void testLoadLevel3() {
        GameData data = new GameData();
        GameEvents.LoadLevel3(data);
        assertNotNull(data.getCurrentLevel());

        assertEquals(GameState.RUNNING, data.getGameState());
    }
}