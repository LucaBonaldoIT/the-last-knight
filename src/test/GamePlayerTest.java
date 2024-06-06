package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GamePlayerTest {

    private GamePlayer player;

    @BeforeEach
    void setUp() {
        // Setup a player object before each test
        player = new GamePlayer(5, 5);
    }

    @Test
    void testUpdate() {
        // Test updating player position with valid input
        GameData gameData = new GameData();
        gameData.setInput(GameInput.UP);
        gameData.setGameState(GameState.RUNNING);
        GameData updatedData = player.update(gameData);
        assertEquals(5, player.getY()); // Expected Y-coordinate after moving up
    }

    @Test
    void testUpdateBoundary() {
        // Test updating player position when hitting boundary
        GameData gameData = new GameData();
        gameData.setInput(GameInput.LEFT);
        gameData.setGameState(GameState.RUNNING);
        GameData updatedData = player.update(gameData);
        assertEquals(5, player.getX()); // Expected X-coordinate remains unchanged
    }

    @Test
    void testUpdateCollision() {
        // Test updating player position when colliding with another object
        GameData gameData = new GameData();
        gameData.setInput(GameInput.RIGHT);
        gameData.setGameState(GameState.RUNNING);

        // Add a mock game object to represent collision
        GameObject obstacle = new MockGameObject(6, 5);
        gameData.addObjectToScene(obstacle);

        GameData updatedData = player.update(gameData);
        assertEquals(5, player.getX()); // Expected X-coordinate remains unchanged
    }

    @Test
    void testUpdateGameOver() {
        // Test updating player when health is zero (game over)
        GameData gameData = new GameData();
        gameData.setInput(GameInput.RIGHT);
        gameData.setGameState(GameState.RUNNING);
        gameData.getPlayerData().setHealth(0); // Set health to zero
        GameData updatedData = player.update(gameData);
        assertEquals(GameState.GAME_OVER, updatedData.getGameState()); // Expected game over state
    }

    @Test
    void testToDelete() {
        // Test if player object should be deleted
        assertFalse(player.toDelete()); // Player object should never be deleted
    }

    // MockGameObject class for testing collisions
    private class MockGameObject implements GameObject {
        private int x;
        private int y;

        public MockGameObject(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String getId() {
            return "MOCK";
        }

        @Override
        public GamePoint getCoordinates() {
            return new GamePoint(x, y);
        }

        @Override
        public int getIndex() {
            return 0;
        }

        @Override
        public void render(Graphics g) {
            // Mock rendering method
        }

        @Override
        public GameData update(GameData d) {
            // Mock update method
            return d;
        }

        @Override
        public boolean toDelete() {
            return false;
        }
    }
}
