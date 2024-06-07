package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GamePlayerDataTest {

    private GamePlayerData playerData;

    @BeforeEach
    public void setUp() {
        playerData = new GamePlayerData();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(playerData);
        assertEquals(0, playerData.getXp());
        assertEquals(0, playerData.getLevel());
        assertNotNull(playerData.getInventory());
        assertEquals(0, playerData.getHealth());
        assertEquals(0, playerData.getStamina());
        assertEquals(0, playerData.getMana());
        assertEquals(GamePlayerClass.NONE, playerData.getPlayerClass());
        assertEquals(100, playerData.getMaxWeight());
        assertEquals(0, playerData.getCoins());
        assertEquals(0, playerData.getInventoryWeight());
    }

    @Test
    public void testAddCoins() {
        playerData.addCoins(50);
        assertEquals(50, playerData.getCoins());
    }

    @Test
    public void testRemoveCoins() {
        playerData.addCoins(100);
        playerData.removeCoins(50);
        assertEquals(150, playerData.getCoins());
    }

    @Test
    public void testTakeDamage() {
        GameData gameData = new GameData();
        playerData.takeDamage(20, gameData);
        assertTrue(playerData.getHealth() < 0 || playerData.getHealth() == 0);
        assertEquals(GameState.GAME_OVER, gameData.getGameState());
    }

    @Test
    public void testAddItem() {
        playerData.addItem(GameItemType.HEALTH_POTION);
        HashMap<GameItemType, Integer> inventory = playerData.getInventory();
        assertEquals(1, inventory.size());
        assertTrue(inventory.containsKey(GameItemType.HEALTH_POTION));
        assertEquals(1, inventory.get(GameItemType.HEALTH_POTION));
    }

    @Test
    public void testRemoveItem() {
        playerData.addItem(GameItemType.STRENGTH_AMULET);
        playerData.removeItem(GameItemType.STRENGTH_AMULET);
        HashMap<GameItemType, Integer> inventory = playerData.getInventory();
        assertEquals(1, inventory.size());
        assertFalse(!inventory.containsKey(GameItemType.STRENGTH_AMULET));
    }

    @Test
    public void testAddXp() {
        GamePlayerData playerData = new GamePlayerData();
        playerData.addXp(100);
        assertEquals(1, playerData.getLevel());
        assertEquals(50, playerData.getXp());
    }




    @Test
    public void testUseItem() {
        GameData gameData = new GameData();

        playerData.addItem(GameItemType.HEALTH_POTION);

        playerData.useItem(GameItemType.HEALTH_POTION, gameData);

        assertEquals(10, playerData.getHealth());

        assertTrue(playerData.getInventory().containsKey(GameItemType.HEALTH_POTION));


    }

}
