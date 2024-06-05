package main.java.com.mealforks.thelastknight.game;

/**
 * This class represents the saved game data, including the current level and player data.
 */
public class GameDataSave {
    private int _currentLevel;
    private GamePlayerData _playerData;

    /**
     * Get the current level.
     *
     * @return The current level.
     */
    public int getCurrentLevel() {
        return _currentLevel;
    }

    /**
     * Get the player data.
     *
     * @return The player data.
     */
    public GamePlayerData getPlayerData() {
        return _playerData;
    }


    /**
     * Set the player data.
     *
     * @param _playerData The player data to set.
     */
    public void setPlayerData(GamePlayerData _playerData) {
        this._playerData = _playerData;
    }

    /**
     * Set the current level.
     *
     * @param _currentLevel The current level to set.
     */
    public void setCurrentLevel(int _currentLevel) {
        this._currentLevel = _currentLevel;
    }
}
