package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;
import java.util.HashMap;

public class GameLevel {
    private String _startRoomId;
    private HashMap<String, GameRoom> _rooms;
    private int _levelIndex;

    public String getStartRoomId()
    {
        return _startRoomId;
    }

    public GameRoom getRoom(String roomId)
    {
        return _rooms.get(roomId);
    }

    public GameLevel()
    {
        _startRoomId = null;
        _rooms = new HashMap<>();
    }

    public GameLevel(String startRoomId, HashMap<String, GameRoom> rooms, int levelIndex)
    {
        _startRoomId = startRoomId;
        _rooms = rooms;
        _levelIndex = levelIndex;
    }

    public int getLevelIndex() {
        return _levelIndex;
    }
}
