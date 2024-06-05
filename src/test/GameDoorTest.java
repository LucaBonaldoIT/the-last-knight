package Test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameDoorTest {

    @Test
    void testDoorCreationWithCoordinates() {
        GamePoint coordinates = new GamePoint(5, 5);
        GameDoor door = new GameDoor(coordinates);
        assertNotNull(door);
        assertEquals(coordinates, door.getCoordinates());
    }

    @Test
    void testDoorUpdateEnterWithKey() {
        // Setup
        GameData data = new GameData();
        GamePlayer player = new GamePlayer(7, 5);
        data.setPlayer(player);
        GameDoor door = new GameDoor(new GamePoint(7, 6));
        data.addObjectToScene(door);
        data.getPlayerData().addItem(GameItemType.DOOR_KEY); // Add a key to player's inventory

        // Player is looking at the door
        data.setInput(GameInput.ENTER);

        // Action
        GameData updatedData = door.update(data);

        // Assertion
        assertEquals(GameState.READY, updatedData.getGameState());
    }

    @Test
    void testDoorUpdateEnterWithoutKey() {
        // Setup
        GameData data = new GameData();
        GamePlayer player = new GamePlayer(5, 5);
        data.setPlayer(player);
        GameDoor door = new GameDoor(new GamePoint(5, 6));
        data.addObjectToScene(door);

        // Player is looking at the door
        data.setInput(GameInput.ENTER);

        // Action
        GameData updatedData = door.update(data);

        // Assertion
        assertFalse(door.toDelete()); // Door should not be deleted if player doesn't have key
    }
}