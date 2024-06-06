package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import main.java.com.mealforks.thelastknight.game.GameItem;
import main.java.com.mealforks.thelastknight.game.GameItemType;
import org.junit.Test;

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
}