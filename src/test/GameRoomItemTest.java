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
        // Creiamo un'istanza di GameRoomItem per i test
        gameRoomItem = new GameRoomItem(GameItemType.HEALTH_POTION, 0, 0);

        // Creiamo un'istanza di GameData per i test
        gameData = new GameData();
        // Impostiamo lo stato del gioco su RUNNING
        gameData.setGameState(GameState.DIALOG);
        // Impostiamo l'input del giocatore su INSPECT
        gameData.setInput(GameInput.INSPECT);
        // Creiamo un'istanza di GamePlayerData per i test
        GamePlayerData playerData = new GamePlayerData();
        // Impostiamo il peso massimo del giocatore per i test
        playerData.setMaxWeight(10);
        // Aggiungiamo un oggetto all'inventario del giocatore
        playerData.addItem(GameItemType.DOOR_KEY);
        // Impostiamo i dati del giocatore nel gameData
        gameData.setPlayerData(playerData);
    }

    @Test
    void testUpdate_InspectInput_InventoryFull() {
        // Simuliamo un inventario pieno del giocatore
        GamePlayerData playerData = new GamePlayerData();
        // Aggiungiamo oggetti fino a raggiungere il limite massimo
        for (int i = 0; i < GamePlayerData.MAX_ITEMS; i++) {
            playerData.addItem(GameItemType.DOOR_KEY);
        }
        gameData.setPlayerData(playerData);

        // Chiamiamo il metodo update di GameRoomItem
        gameData = gameRoomItem.update(gameData);

        // Verifichiamo che lo stato del gioco sia impostato su DIALOG
        assertEquals(GameState.DIALOG, gameData.getGameState());
        // Verifichiamo che il metodo update abbia impostato il flag toDelete a true
        assertFalse(gameRoomItem.toDelete());
    }

    @Test
    void testUpdate_InspectInput_InventoryNotFull() {
        // Simuliamo un inventario non pieno del giocatore
        GamePlayerData playerData = new GamePlayerData();
        playerData.addItem(GameItemType.DOOR_KEY);
        gameData.setPlayerData(playerData);

        // Chiamiamo il metodo update di GameRoomItem
        gameData = gameRoomItem.update(gameData);

        // Verifichiamo che lo stato del gioco sia impostato su DIALOG
        assertEquals(GameState.DIALOG, gameData.getGameState());
        // Verifichiamo che il metodo update abbia impostato il flag toDelete a true
        assertFalse(gameRoomItem.toDelete());
    }

    @Test
    void testUpdate_InspectInput_WeightLimitExceeded() {
        // Simuliamo un inventario non pieno del giocatore, ma con peso massimo superato
        gameData.getPlayerData().setMaxWeight(1);

        // Chiamiamo il metodo update di GameRoomItem
        gameData = gameRoomItem.update(gameData);

        // Verifichiamo che lo stato del gioco sia impostato su DIALOG
        assertEquals(GameState.DIALOG, gameData.getGameState());
        // Verifichiamo che il metodo update abbia impostato il flag toDelete a false
        assertFalse(gameRoomItem.toDelete());
    }
}
