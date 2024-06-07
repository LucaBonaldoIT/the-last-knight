package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRoomItemTest {

    private GameRoomItem gameRoomItem;
    private GameData gameData;

    @BeforeEach
    void setUp() {

        gameRoomItem = new GameRoomItem(GameItemType.HEALTH_POTION, 0, 0);


        gameData = new GameData();

        gameData.setGameState(GameState.DIALOG);

        gameData.setInput(GameInput.INSPECT);

        GamePlayerData playerData = new GamePlayerData();

        playerData.setMaxWeight(10);

        playerData.addItem(GameItemType.DOOR_KEY);

        gameData.setPlayerData(playerData);
    }

    @Test
    void testUpdate_InspectInput_InventoryFull() {
        GamePlayerData playerData = new GamePlayerData();
        for (int i = 0; i < GamePlayerData.MAX_ITEMS; i++) {
            playerData.addItem(GameItemType.DOOR_KEY);
        }
        gameData.setPlayerData(playerData);

        gameData = gameRoomItem.update(gameData);


        assertEquals(GameState.DIALOG, gameData.getGameState());

        assertFalse(gameRoomItem.toDelete());
    }

    @Test
    void testUpdate_InspectInput_InventoryNotFull() {

        GamePlayerData playerData = new GamePlayerData();
        playerData.addItem(GameItemType.DOOR_KEY);
        gameData.setPlayerData(playerData);


        gameData = gameRoomItem.update(gameData);


        assertEquals(GameState.DIALOG, gameData.getGameState());

        assertFalse(gameRoomItem.toDelete());
    }

    @Test
    void testUpdate_InspectInput_WeightLimitExceeded() {

        gameData.getPlayerData().setMaxWeight(1);


        gameData = gameRoomItem.update(gameData);


        assertEquals(GameState.DIALOG, gameData.getGameState());

        assertFalse(gameRoomItem.toDelete());
    }
}
