package test;

import main.java.com.mealforks.thelastknight.game.GameData;
import main.java.com.mealforks.thelastknight.game.GameNarratorDialog;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameNarratorDialogTest {

    @Test
    public void testUpdate() {
        // Assuming an update() method that doesn't change the dialog state for now
        GameNarratorDialog dialog = new GameNarratorDialog("Test text");
        GameData gameData = new GameData();
        GameData updatedGameData = dialog.update(gameData);
        assertEquals(gameData, updatedGameData);
    }

    @Test
    public void testToDelete() {
        GameNarratorDialog dialog = new GameNarratorDialog("Test text");
        assertFalse(dialog.toDelete());
        // Assuming the dialog will eventually be deleted after showing the full text
    }
}
