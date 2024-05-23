package main.java.com.mealforks.thelastknight.game;

import java.awt.event.KeyEvent;

public class GameInputHandler {
    private static GameInputHandler _intance;

    private GameInputHandler()
    {
    }

    public static GameInputHandler getInstance()
    {
        if (_intance == null)
        {
            _intance = new GameInputHandler();
        }
        return _intance;
    }

    public GameInput getGameInput(KeyEvent keyEvent)
    {
        System.out.println(keyEvent.getKeyCode());

        return switch (keyEvent.getKeyCode()) {
            case 87 -> GameInput.UP;
            case 83 -> GameInput.DOWN;
            case 68 -> GameInput.RIGHT;
            case 65 -> GameInput.LEFT;
            case 69 -> GameInput.INSPECT;
            default -> GameInput.NONE;
        };
    }
}
