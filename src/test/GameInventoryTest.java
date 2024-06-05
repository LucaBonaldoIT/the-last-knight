package Test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameInventoryTest {

    private GameInventory gameInventory;
    private GameData gameData;

    @BeforeEach
    void setUp() {
        gameInventory = new GameInventory();
        gameData = new GameData();
    }

    @Test
    void testUpdateInventory() {
        // Setup data for updating inventory
        gameData.setInput(GameInput.INVENTORY);
        gameData.setGameState(GameState.RUNNING);

        // Perform update
        GameData updatedGameData = gameInventory.update(gameData);

        // Verify that game state is changed to inventory
        assertEquals(GameState.INVENTORY, updatedGameData.getGameState());

        // Simulate pressing inventory button again to close inventory
        gameData.setInput(GameInput.INVENTORY);
        GameData closedInventoryGameData = gameInventory.update(gameData);

        // Verify that game state is changed back to running
        assertEquals(GameState.RUNNING, closedInventoryGameData.getGameState());
    }

}
