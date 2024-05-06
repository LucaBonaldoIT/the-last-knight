package main.java.com.mealforks.thelastknight.game;

public class GameSetting implements Cloneable {
    private boolean _showFpsCounter;

    private static final GameSetting _default;

    public static GameSetting getDefault()
    {
        GameSetting setting;

        return (GameSetting)_default.clone();
    }

    static
    {
        _default = new GameSetting();
    }

    private GameSetting()
    {
        _showFpsCounter = true;
    }

    public boolean getShowFpsCounter()
    {
        return _showFpsCounter;
    }

    public void setShowFpsCounter(boolean showFpsCounter)
    {
        _showFpsCounter = showFpsCounter;
    }

    @Override
    public Object clone()
    {
        GameSetting setting = new GameSetting();

        setting.setShowFpsCounter(_showFpsCounter);

        return setting;
    }
}