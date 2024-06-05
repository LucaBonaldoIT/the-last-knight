package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GamePlayerTest {

    @Test
    public void testUpdatePlayerPositionWithCollision() {
        GameData gameData = new GameData();
        gameData.setGameState(GameState.RUNNING);
        gameData.setInput(GameInput.RIGHT);

        // Mocking game objects with one at (1,0) to simulate a collision
        GameObject mockObject = new GameObject() {
            @Override
            public String getId() {
                return "MOCK_OBJECT";
            }

            @Override
            public GamePoint getCoordinates() {
                return new GamePoint(1, 0);
            }

            @Override
            public int getIndex() {
                return 0;
            }

            @Override
            public void render(Graphics g) {}

            @Override
            public GameData update(GameData d) {
                return d;
            }

            @Override
            public boolean toDelete() {
                return false;
            }
        };

        gameData.addObjectToScene(mockObject);

        GamePlayer player = new GamePlayer(0, 0);
        GameData updatedGameData = player.update(gameData);

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        assertEquals(GameDirection.DOWN, player.getFacingDirection());
        assertFalse(updatedGameData.getGameObjects().isEmpty()); // Check if mock object is still in the game objects list
    }



    @Test
    public void testToDelete() {
        GamePlayer player = new GamePlayer(0, 0);
        assertFalse(player.toDelete());
    }
}
