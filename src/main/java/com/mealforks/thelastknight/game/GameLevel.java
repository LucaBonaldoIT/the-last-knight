package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;
import java.util.HashMap;

public class GameLevel {
    private String _startRoomId;
    private HashMap<String, GameRoom> _rooms;

    public String getStartRoomId()
    {
        return _startRoomId;
    }

    public GameRoom getRoom(String roomId)
    {
        return _rooms.get(roomId);
    }

    public GameRoom[] getRooms()
    {
        return (GameRoom[])_rooms.values().toArray();
    }

    public GameLevel()
    {
        _startRoomId = null;
        _rooms = new HashMap<>();
    }

    public GameLevel(String startRoomId, HashMap<String, GameRoom> rooms)
    {
        _startRoomId = startRoomId;
        _rooms = rooms;
    }
}
