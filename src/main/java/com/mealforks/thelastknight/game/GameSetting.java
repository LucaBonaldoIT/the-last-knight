package main.java.com.mealforks.thelastknight.game;
/**
 * Represents the settings of the game.
 */
public class GameSetting implements Cloneable {

    /** Indicates whether to show the FPS counter. */
    private boolean _showFpsCounter;

    /** The default game setting. */
    private static final GameSetting _default;

    /**
     * Retrieves the default game setting.
     *
     * @return The default game setting.
     */
    public static GameSetting getDefault()
    {
        GameSetting setting;

        return (GameSetting)_default.clone();
    }

    /** Initializes the default game setting. */
    static
    {
        _default = new GameSetting();
    }

    /** Constructs a new GameSetting object with default values. */
    private GameSetting()
    {
        _showFpsCounter = true;
    }

    /**
     * Retrieves whether to show the FPS counter.
     *
     * @return True if the FPS counter is to be shown, false otherwise.
     */
    public boolean getShowFpsCounter()
    {
        return _showFpsCounter;
    }

    /**
     * Sets whether to show the FPS counter.
     *
     * @param showFpsCounter True to show the FPS counter, false otherwise.
     */
    public void setShowFpsCounter(boolean showFpsCounter)
    {
        _showFpsCounter = showFpsCounter;
    }

    /**
     * Clones the game setting.
     *
     * @return A clone of the game setting.
     */
    @Override
    public Object clone()
    {
        GameSetting setting = new GameSetting();

        setting.setShowFpsCounter(_showFpsCounter);

        return setting;
    }
}
