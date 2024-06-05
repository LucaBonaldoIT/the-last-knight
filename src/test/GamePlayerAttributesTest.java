package test;

import main.java.com.mealforks.thelastknight.game.GamePlayerAttributes;
import main.java.com.mealforks.thelastknight.game.GamePlayerClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GamePlayerAttributesTest {

    @Test
    public void testOnNewLevelKnight() {
        GamePlayerAttributes attributes = new GamePlayerAttributes();
        attributes.onNewLevel(GamePlayerClass.KNIGHT);

        assertEquals(3, attributes.getStrength());
        assertEquals(2, attributes.getEndurance());
        assertEquals(1, attributes.getIntelligence());
        assertEquals(1, attributes.getAgility());
    }

    @Test
    public void testOnNewLevelMage() {
        GamePlayerAttributes attributes = new GamePlayerAttributes();
        attributes.onNewLevel(GamePlayerClass.MAGE);

        assertEquals(1, attributes.getStrength());
        assertEquals(1, attributes.getEndurance());
        assertEquals(4, attributes.getIntelligence());
        assertEquals(1, attributes.getLuck());
    }

    @Test
    public void testOnNewLevelThief() {
        GamePlayerAttributes attributes = new GamePlayerAttributes();
        attributes.onNewLevel(GamePlayerClass.THIEF);

        assertEquals(1, attributes.getStrength());
        assertEquals(2, attributes.getEndurance());
        assertEquals(1, attributes.getIntelligence());
        assertEquals(3, attributes.getAgility());
    }
}
