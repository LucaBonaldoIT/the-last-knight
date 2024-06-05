package test;

import main.java.com.mealforks.thelastknight.game.GamePlayer;
import main.java.com.mealforks.thelastknight.game.GamePoint;
import main.java.com.mealforks.thelastknight.game.GameRoom;
import main.java.com.mealforks.thelastknight.game.GameTile;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class GameRoomTest {

    @Test
    public void testAddObjectToLoad() {
        GameRoom room = new GameRoom();
        GamePlayer player = new GamePlayer(0, 0);
        room.addObjectToLoad(player);

        assertTrue(room.getObjectsToLoad().contains(player));
    }
}
