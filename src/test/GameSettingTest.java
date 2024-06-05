package test;

import main.java.com.mealforks.thelastknight.game.GameSetting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSettingTest {

    @Test
    void testSetShowFpsCounter() {
        GameSetting defaultSetting = GameSetting.getDefault();
        assertTrue(defaultSetting.getShowFpsCounter());

        defaultSetting.setShowFpsCounter(false);
        assertFalse(defaultSetting.getShowFpsCounter());

        defaultSetting.setShowFpsCounter(true);
        assertTrue(defaultSetting.getShowFpsCounter());
    }

    @Test
    void testClone() {
        GameSetting defaultSetting = GameSetting.getDefault();
        defaultSetting.setShowFpsCounter(false);

        GameSetting clonedSetting = (GameSetting) defaultSetting.clone();
        assertNotNull(clonedSetting);
        assertEquals(defaultSetting.getShowFpsCounter(), clonedSetting.getShowFpsCounter());
    }
}
