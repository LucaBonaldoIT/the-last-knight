package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.java.com.mealforks.thelastknight.game.GameItem;
import main.java.com.mealforks.thelastknight.game.GameItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameItemTest {

    @Test
    public void testDefaultConstructor() {
        GameItem item = new GameItem();
        assertEquals(GameItemType.NONE, item.getItemType());
        assertEquals(0, item.getWeight());
        assertEquals(0, item.getValue());
        assertEquals("item_name", item.getName());
    }

    @Test
    public void testParameterizedConstructor() {
        GameItemType type = GameItemType.IRON_ARMOR;
        int weight = 10;
        int value = 50;
        String name = "Sword";
        GameItem item = new GameItem(type, weight, value, name);
        assertEquals(type, item.getItemType());
        assertEquals(weight, item.getWeight());
        assertEquals(value, item.getValue());
        assertEquals(name, item.getName());
    }

    @Test
    public void testGetItemType() {
        GameItemType type = GameItemType.IRON_SWORD;
        GameItem item = new GameItem(type, 20, 100, "Shield");
        assertEquals(type, item.getItemType());
    }

    @Test
    public void testGetName() {
        String name = "Potion";
        GameItem item = new GameItem(GameItemType.HEALTH_POTION, 5, 25, name);
        assertEquals(name, item.getName());
    }

    @Test
    public void testGetWeight() {
        int weight = 15;
        GameItem item = new GameItem(GameItemType.CELESTIAL_WAND, weight, 10, "Key");
        assertEquals(weight, item.getWeight());
    }

    @Test
    public void testGetValue() {
        int value = 30;
        GameItem item = new GameItem(GameItemType.WOODEN_SWORD, 3, value, "Gem");
        assertEquals(value, item.getValue());
    }
}