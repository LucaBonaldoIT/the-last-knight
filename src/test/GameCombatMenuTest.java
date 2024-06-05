package Test;

import main.java.com.mealforks.thelastknight.game.GameCombatMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCombatMenuTest {

    private GameCombatMenu combatMenu;

    @BeforeEach
    public void setUp() {
        combatMenu = new GameCombatMenu();
    }

    @Test
    public void testInitialSelectedOption() {
        assertEquals("Melee Attack", combatMenu.getSelectedOption());
    }

    @Test
    public void testNextOption() {
        combatMenu.nextOption();
        assertEquals("Cast Spell", combatMenu.getSelectedOption());

        combatMenu.nextOption();
        assertEquals("Use Item", combatMenu.getSelectedOption());

        combatMenu.nextOption();
        assertEquals("Flee", combatMenu.getSelectedOption());

        combatMenu.nextOption();
        assertEquals("Melee Attack", combatMenu.getSelectedOption());
    }

    @Test
    public void testPreviousOption() {
        combatMenu.previousOption();
        assertEquals("Flee", combatMenu.getSelectedOption());

        combatMenu.previousOption();
        assertEquals("Use Item", combatMenu.getSelectedOption());

        combatMenu.previousOption();
        assertEquals("Cast Spell", combatMenu.getSelectedOption());

        combatMenu.previousOption();
        assertEquals("Melee Attack", combatMenu.getSelectedOption());
    }

    @Test
    public void testDisplayMessage() {
        assertFalse(combatMenu.getDisplayMessage());

        combatMenu.displayMessage("Test Message");
        assertTrue(combatMenu.getDisplayMessage());
    }
}