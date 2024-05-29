package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;

public class GameRoomData {
    public ArrayList<GameObject> _gameObject;

    public GameRoomData(GameData data)
    {
        _gameObject = data.getGameObjects();
    }

    public ArrayList<GameObject> getGameObjects()
    {
        return _gameObject;
    }
}
