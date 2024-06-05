package Test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    private GameCharacter combatCharacter;
    private GameCharacter giftCharacter;
    private GameCharacter textCharacter;
    private GameCharacter tradeCharacter;
    private GameItem item;
    private GameSprite sprite;
    private GamePoint coordinates;

    @BeforeEach
    void setUp() {
        item = new GameItem(GameItemType.DOOR_KEY, 0, 0, "key");
        sprite = GameSprite.GIFT_CHARACTER;
        coordinates = new GamePoint(0, 0);

        combatCharacter = GameCharacter.getCombatCharacter(sprite, coordinates, GameCombatType.NORMAL, 100, 20, 10);
        giftCharacter = GameCharacter.getGiftCharacter(item, sprite, coordinates);
        textCharacter = GameCharacter.getTextCharacter("Hello, adventurer!", sprite, coordinates);
        tradeCharacter = GameCharacter.getTradeCharacter(sprite, coordinates);
    }

    @Test
    void testCombatCharacter() {
        assertEquals("COMBAT_CHARACTER", combatCharacter.getId());
        assertEquals(GameCharacterType.COMBAT, combatCharacter.get_type());
        assertEquals(100, combatCharacter.getHealth());
        assertEquals(20, combatCharacter.getAttackPower());
        assertEquals(10, combatCharacter.getDefense());
    }

    @Test
    void testGiftCharacter() {
        assertEquals("GIFT_CHARACTER", giftCharacter.getId());
        assertEquals(GameCharacterType.GIFT, giftCharacter.get_type());
        assertSame(item, giftCharacter.getItemToGift());
    }

    @Test
    void testTextCharacter() {
        assertEquals("TEXT_ONLY_CHARACTER", textCharacter.getId());
        assertEquals(GameCharacterType.TEXT_ONLY, textCharacter.get_type());
        assertEquals("Hello, adventurer!", textCharacter.getText());
    }

    @Test
    void testTradeCharacter() {
        assertEquals("TRADE_CHARACTER", tradeCharacter.getId());
        assertEquals(GameCharacterType.TRADE, tradeCharacter.get_type());
    }

    @Test
    void testTakeDamage() {
        GameData data = new GameData();
        combatCharacter.takeDamage(10, data);
        assertEquals(90, combatCharacter.getHealth());
    }

    @Test
    void testGetId() {
        assertEquals("COMBAT_CHARACTER", combatCharacter.getId());
        assertEquals("GIFT_CHARACTER", giftCharacter.getId());
        assertEquals("TEXT_ONLY_CHARACTER", textCharacter.getId());
        assertEquals("TRADE_CHARACTER", tradeCharacter.getId());
    }
}