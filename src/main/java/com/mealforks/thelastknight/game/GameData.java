package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;

public class GameData {
    private final GameSetting _setting;
    private GameState _state;
    private ArrayList<GameObject> _gameObjects;
    private GameArea _area;
    private GameInput _input;

    public GameData()
    {
        _setting = GameSetting.getDefault();
        _state = GameState.READY;
        _gameObjects = new ArrayList<>();
        _input = GameInput.NONE;
        _area = new GameArea(new GameTile[][]{}, new GameCollision[][]{});
    }

    public GameSetting getGameSetting()
    {
        return _setting;
    }

    public GameState getGameState()
    {
        return _state;
    }

    public void setGameState(GameState state)
    {
        _state = state;
    }

    public GameArea getGameArea()
    {
        return _area;
    }

    public void setGameArea(GameArea area)
    {
        _area = area;
    }

    public ArrayList<GameObject> getGameObjects()
    {
        return _gameObjects;
    }

    public void clearScene()
    {
        _gameObjects = new ArrayList<>();
    }

    public void addObjectToScene(GameObject obj)
    {
        _gameObjects.add(obj);
    }

    public GameInput getInput() {
        return _input;
    }

    public void setInput(GameInput input) {
        _input = input;
    }
}
