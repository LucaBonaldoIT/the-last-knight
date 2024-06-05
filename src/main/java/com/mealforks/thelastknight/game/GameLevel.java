package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a game level consisting of multiple rooms and a starting room.
 */
public class GameLevel {
    private String _startRoomId;
    private HashMap<String, GameRoom> _rooms;
    private int _levelIndex;

    /**
     * Returns the ID of the starting room for this level.
     *
     * @return the starting room ID.
     */
    public String getStartRoomId() {
        return _startRoomId;
    }

    /**
     * Returns the room object corresponding to the given room ID.
     *
     * @param roomId the ID of the room to retrieve.
     * @return the room object with the specified ID, or null if no such room exists.
     */
    public GameRoom getRoom(String roomId) {
        return _rooms.get(roomId);
    }

    /**
     * Constructs a default GameLevel object with no starting room and an empty set of rooms.
     */
    public GameLevel() {
        _startRoomId = null;
        _rooms = new HashMap<>();
    }

    /**
     * Constructs a GameLevel object with the specified starting room ID, set of rooms, and level index.
     *
     * @param startRoomId the ID of the starting room.
     * @param rooms a map of room IDs to room objects.
     * @param levelIndex the index of the level.
     */
    public GameLevel(String startRoomId, HashMap<String, GameRoom> rooms, int levelIndex) {
        _startRoomId = startRoomId;
        _rooms = rooms;
        _levelIndex = levelIndex;
    }

    /**
     * Returns the index of this level.
     *
     * @return the level index.
     */
    public int getLevelIndex() {
        return _levelIndex;
    }
}
