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

        switch (keyEvent.getKeyCode())
        {
            case 87:
            {
                return GameInput.UP;
            }
            case 83:
            {
                return GameInput.DOWN;
            }
            case 68:
            {
                return GameInput.RIGHT;
            }
            case 65:
            {
                return GameInput.LEFT;
            }
            default:
            {
                return GameInput.NONE;
            }
        }
    }
}
