package main.java.com.mealforks.thelastknight.game;

public class GameDataSave {
    private int _currentLevel;
    private GamePlayerData _playerData;

    public int get_currentLevel() {
        return _currentLevel;
    }

    public GamePlayerData get_playerData() {
        return _playerData;
    }

    public void set_playerData(GamePlayerData _playerData) {
        this._playerData = _playerData;
    }

    public void set_currentLevel(int _currentLevel) {
        this._currentLevel = _currentLevel;
    }
}
