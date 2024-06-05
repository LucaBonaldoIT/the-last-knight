package test;

import main.java.com.mealforks.thelastknight.game.GameLevel;
import main.java.com.mealforks.thelastknight.game.GameRoom;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class GameLevelTest {

    @Test
    public void testParameterizedConstructor() {
        HashMap<String, GameRoom> rooms = new HashMap<>();
        rooms.put("room1", new GameRoom());
        rooms.put("room2", new GameRoom());

        GameLevel level = new GameLevel("room1", rooms, 1);
        assertEquals("room1", level.getStartRoomId());
        assertEquals(1, level.getLevelIndex());
    }
}
