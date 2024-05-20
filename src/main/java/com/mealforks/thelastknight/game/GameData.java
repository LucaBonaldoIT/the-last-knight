package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;

public class GameData {
    private final GameSetting _setting;
    private GameState _state;
    private ArrayList<GameObject> _gameObjects;
    private GameRoom _room;
    private GameRoom[][] _rooms;
    private GameInput _input;

    public GameData()
    {
        _setting = GameSetting.getDefault();
        _state = GameState.READY;
        _gameObjects = new ArrayList<>();
        _input = GameInput.NONE;
        _room = GameRoom.NONE;
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

    public GameRoom getGameRoom()
    {
        return _room;
    }

    public void setGameRoom(GameRoom room)
    {
        _room = room;
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
