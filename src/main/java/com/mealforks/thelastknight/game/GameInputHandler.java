package main.java.com.mealforks.thelastknight.game;

import java.awt.event.KeyEvent;

/**
 * This class handles the mapping of keyboard inputs to game actions.
 */
public class GameInputHandler {
    private static GameInputHandler _intance;

    private GameInputHandler()
    {
    }

    /**
     * Get the instance of GameInputHandler.
     *
     * @return The instance of GameInputHandler.
     */
    public static GameInputHandler getInstance()
    {
        if (_intance == null)
        {
            _intance = new GameInputHandler();
        }
        return _intance;
    }

    /**
     * Get the corresponding game input for a given key event.
     *
     * @param keyEvent The key event.
     * @return The corresponding game input.
     */
    public GameInput getGameInput(KeyEvent keyEvent)
    {
        return switch (keyEvent.getKeyCode()) {
            case 87 -> GameInput.UP;
            case 83 -> GameInput.DOWN;
            case 68 -> GameInput.RIGHT;
            case 65 -> GameInput.LEFT;
            case 69 -> GameInput.ENTER;
            case 81 -> GameInput.INSPECT;
            case 73 -> GameInput.INVENTORY;
            case 84 -> GameInput.TOSS;
            case 89 -> GameInput.YES;
            case 78 -> GameInput.NO;
            case 27 -> GameInput.ESC;
            default -> GameInput.NONE;
        };
    }
}
