package test;
import org.junit.jupiter.api.Test;
import main.java.com.mealforks.thelastknight.game.GameInput;
import main.java.com.mealforks.thelastknight.game.GameInputHandler;
import org.junit.jupiter.api.AfterEach;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class GameInputHandlerTest {

    @Test
    void getCorrectGameInput() {
        GameInputHandler gameInputHandler = GameInputHandler.getInstance();
        Component component = new JPanel(); // Example component

        KeyEvent keyEventUP = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 87, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventDOWN = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 83, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventRIGHT = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 68, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventLEFT = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 65, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventENTER = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, 69, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventINSPECT = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventINVENTORY = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_I, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventTOSS = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_T, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventYES = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Y, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventNO = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_N, KeyEvent.CHAR_UNDEFINED);
        KeyEvent keyEventESC = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);

        assertEquals(GameInput.UP, gameInputHandler.getGameInput(keyEventUP));
        assertEquals(GameInput.DOWN, gameInputHandler.getGameInput(keyEventDOWN));
        assertEquals(GameInput.RIGHT, gameInputHandler.getGameInput(keyEventRIGHT));
        assertEquals(GameInput.LEFT, gameInputHandler.getGameInput(keyEventLEFT));
        assertEquals(GameInput.ENTER, gameInputHandler.getGameInput(keyEventENTER));
        assertEquals(GameInput.INSPECT, gameInputHandler.getGameInput(keyEventINSPECT));
        assertEquals(GameInput.INVENTORY, gameInputHandler.getGameInput(keyEventINVENTORY));
        assertEquals(GameInput.TOSS, gameInputHandler.getGameInput(keyEventTOSS));
        assertEquals(GameInput.YES, gameInputHandler.getGameInput(keyEventYES));
        assertEquals(GameInput.NO, gameInputHandler.getGameInput(keyEventNO));
        assertEquals(GameInput.ESC, gameInputHandler.getGameInput(keyEventESC));

    }
}