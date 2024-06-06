package test;

import main.java.com.mealforks.thelastknight.game.GameData;
import main.java.com.mealforks.thelastknight.game.GameInput;
import main.java.com.mealforks.thelastknight.game.GameState;
import main.java.com.mealforks.thelastknight.game.GameTradeDialog;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class GameTradeDialogTest {

    private GameTradeDialog dialog;
    private static final String DIALOG_ID = "testDialog";
    private static final String DIALOG_TEXT = "This is a test dialog text for GameTradeDialog.";

    @Before
    public void setUp() {
        dialog = new GameTradeDialog(DIALOG_ID, DIALOG_TEXT);
    }

    @Test
    public void testInitialToDelete() {
        assertFalse(dialog.toDelete());
    }

    @Test
    public void testUpdateFirstUpdate() {
        GameData data = new GameData();
        data = dialog.update(data);
        assertEquals(GameState.TRADE, data.getGameState());
    }

    @Test
    public void testUpdateCharIncrement() throws InterruptedException {
        GameData data = new GameData();
        long initialTime = System.currentTimeMillis();
        dialog.update(data);
        dialog.update(data);

        // Simula il passaggio del tempo per permettere l'incremento del numero di caratteri mostrati
        Thread.sleep(2);

        dialog.update(data);
        assertTrue(dialog.getText().substring(0, 1).length() <= dialog.getText().length());
    }
}
