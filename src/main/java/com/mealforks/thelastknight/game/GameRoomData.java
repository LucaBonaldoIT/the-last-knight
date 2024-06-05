package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;

/**
 * Represents the data of a game room.
 */
public class GameRoomData {

    /** The list of game objects in the room. */
    public ArrayList<GameObject> _gameObject;

    /**
     * Constructs a GameRoomData object from the specified game data.
     *
     * @param data The game data.
     */
    public GameRoomData(GameData data)
    {
        _gameObject = data.getGameObjects();
    }

    /**
     * Retrieves the list of game objects.
     *
     * @return The list of game objects.
     */
    public ArrayList<GameObject> getGameObjects()
    {
        return _gameObject;
    }
}
