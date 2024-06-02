package main.java.com.mealforks.thelastknight.game;

public class GameDataSave {
    private int _currentLevel;
    private GamePlayerData _playerData;

    public int getCurrentLevel() {
        return _currentLevel;
    }

    public GamePlayerData getPlayerData() {
        return _playerData;
    }

    public void setPlayerData(GamePlayerData _playerData) {
        this._playerData = _playerData;
    }

    public void setCurrentLevel(int _currentLevel) {
        this._currentLevel = _currentLevel;
    }
}
