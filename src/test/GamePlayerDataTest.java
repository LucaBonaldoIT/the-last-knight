package test;

import main.java.com.mealforks.thelastknight.game.GameData;
import main.java.com.mealforks.thelastknight.game.GameItemType;
import main.java.com.mealforks.thelastknight.game.GamePlayerData;
import main.java.com.mealforks.thelastknight.game.GameState;
import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.*;

public class GamePlayerDataTest {

    @Test
    public void testAddCoins() {
        GamePlayerData playerData = new GamePlayerData();
        playerData.addCoins(50);
        assertEquals(50, playerData.getCoins());
    }

    @Test
    public void testRemoveCoins() {
        GamePlayerData playerData = new GamePlayerData();
        playerData.setCoins(100);
        playerData.removeCoins(50);
        assertEquals(150, playerData.getCoins());
    }

    @Test
    public void testAddItem() {
        GamePlayerData playerData = new GamePlayerData();
        playerData.addItem(GameItemType.HEALTH_POTION);
        HashMap<GameItemType, Integer> inventory = playerData.getInventory();
        assertTrue(inventory.containsKey(GameItemType.HEALTH_POTION));
        assertEquals(1, (int) inventory.get(GameItemType.HEALTH_POTION));
    }

    @Test
    public void testAddXp() {
        GamePlayerData playerData = new GamePlayerData();
        playerData.addXp(100);
        assertEquals(1, playerData.getLevel());
        assertEquals(50, playerData.getXp());
    }
}
