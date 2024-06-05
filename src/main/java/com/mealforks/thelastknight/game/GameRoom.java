package main.java.com.mealforks.thelastknight.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a room in the game world.
 */
public class GameRoom implements GameObject {

    /** Represents a null room. */
    public final static GameRoom NONE = new GameRoom(GameRoomType.NONE);

    private String _id;
    private GameArea _area;
    private GameRoomType _type;
    private ArrayList<GameObject> _objectsToLoad;
    private HashMap<GamePoint, GameTile> _doors;
    private boolean _firstLoad;

    private GamePoint _defaultStartPoint;

    private HashMap<GameTile, GamePoint> _startPointByComingDoor;

    private String _northRoomId;
    private String _southRoomId;
    private String _eastRoomId;
    private String _westRoomId;

    private GameRoomData _roomData;

    /**
     * Constructs a new GameRoom with the specified type.
     *
     * @param type The type of the room.
     */
    private GameRoom(GameRoomType type)
    {
        _id = null;
        _type = type;
        _firstLoad = true;
        _doors = new HashMap<>();

        _northRoomId = null;
        _southRoomId = null;
        _westRoomId = null;
        _eastRoomId = null;
        _objectsToLoad = new ArrayList<>();
        _roomData = null;
    }

    /**
     * Constructs a new GameRoom with the specified type and area.
     *
     * @param type The type of the room.
     * @param area The area of the room.
     */
    public GameRoom(GameRoomType type, GameArea area)
    {
        _type = type;
        _area = area;
        _doors = new HashMap<>();
        _objectsToLoad = new ArrayList<>();
        _firstLoad = true;
        _roomData = null;
    }

    /**
     * Constructs a new GameRoom with the specified parameters.
     *
     * @param id                   The ID of the room.
     * @param type                 The type of the room.
     * @param area                 The area of the room.
     * @param rooms                The IDs of adjacent rooms.
     * @param doors                The doors in the room.
     * @param defaultStartPoint    The default start point of the room.
     * @param startPointByComingDoor The start point by coming door.
     */
    public GameRoom(String id, GameRoomType type, GameArea area, String[] rooms, HashMap<GamePoint, GameTile> doors, GamePoint defaultStartPoint, HashMap<GameTile, GamePoint> startPointByComingDoor)
    {
        _firstLoad = true;
        _id = id;
        _type = type;
        _area = area;
        _roomData = null;

        _northRoomId = rooms[0];
        _eastRoomId = rooms[1];
        _southRoomId = rooms[2];
        _westRoomId = rooms[3];

        _doors = doors;

        _defaultStartPoint = defaultStartPoint;
        _objectsToLoad = new ArrayList<>();
        _startPointByComingDoor = startPointByComingDoor;
    }

    /**
     * Constructs a new GameRoom.
     */
    public GameRoom()
    {
        _type = GameRoomType.NONE;
        _objectsToLoad = new ArrayList<>();
        _firstLoad = true;
        _roomData = null;
    }

    /**
     * Adds an object to load in the room.
     *
     * @param object The object to load.
     */
    public void addObjectToLoad(GameObject object)
    {
        _objectsToLoad.add(object);
    }

    /**
     * Gets the objects to load in the room.
     *
     * @return The objects to load.
     */
    public ArrayList<GameObject> getObjectsToLoad()
    {
        if (!_firstLoad)
        {
            return new ArrayList<>();
        }

        _firstLoad = false;

        return _objectsToLoad;
    }

    /**
     * Gets the start point for the given coming door.
     *
     * @param comingDoor The coming door.
     * @return The start point.
     */
    public GamePoint getStartPoint(GameTile comingDoor)
    {
        return _startPointByComingDoor.get(comingDoor);
    }

    /**
     * Gets the default start point of the room.
     *
     * @return The default start point.
     */
    public GamePoint getDefaultStartPoint()
    {
        return _defaultStartPoint;
    }

    /**
     * Gets the game area of the room.
     *
     * @return The game area.
     */
    public GameArea getGameArea()
    {
        return _area;
    }

    /**
     * Saves room data to the given game data.
     *
     * @param data The game data to save to.
     */
    public void saveRoomData(GameData data)
    {
        _roomData = new GameRoomData(data);
    }

    /**
     * Loads room data from the given game data.
     *
     * @param data The game data to load from.
     * @return The modified game data.
     */
    public GameData loadRoomData(GameData data)
    {
        if (_roomData != null)
        {
            data.clearScene();

            for (GameObject obj : _roomData.getGameObjects())
            {
                data.addObjectToScene(obj);
            }
        }

        return data;
    }

    /**
     * Retrieves the ID of the object.
     *
     * @return The ID of the object.
     */
    @Override
    public String getId() {
        return "";
    }

    /**
     * Retrieves the coordinates of the object.
     *
     * @return The coordinates of the object.
     */
    @Override
    public GamePoint getCoordinates() {
        return GamePoint.NONE;
    }

    /**
     * Retrieves the index of the object.
     *
     * @return The index of the object.
     */
    @Override
    public int getIndex() {
        return 0;
    }


    /**
     * Renders the room on the graphics context.
     *
     * @param g The graphics context.
     */
    @Override
    public void render(Graphics g)
    {
        if (_area == null)
        {
            return;
        }

        _area.render(g);

        for (Map.Entry<GamePoint, GameTile> entry : _doors.entrySet())
        {
            GamePoint point = entry.getKey();
            Image tile = GameConstants.getTile(entry.getValue());
            g.drawImage(tile, point.x * GameConstants.getTileSize(), point.y * GameConstants.getTileSize(), null);
        }
    }

    /**
     * Updates the room's state.
     *
     * @param d The game data.
     * @return The updated game data.
     */
    @Override
    public GameData update(GameData d)
    {
        GamePlayer player = d.getPlayer();

        GameTile standingDoor = _doors.getOrDefault(new GamePoint(player.getX(), player.getY()), null);

        if (standingDoor != null)
        {
            switch (standingDoor)
            {
                case DOOR_EAST_WALL:
                {
                    d.loadRoom(_eastRoomId, standingDoor);
                    break;
                }
                case DOOR_WEST_WALL:
                {
                    d.loadRoom(_westRoomId, standingDoor);
                    break;
                }
                case DOOR_NORTH_WALL:
                {
                    d.loadRoom(_northRoomId, standingDoor);
                    break;
                }
                case DOOR_SOUTH_WALL:
                {
                    d.loadRoom(_southRoomId, standingDoor);
                    break;
                }
                default:
                    break;
            }
        }

        return d;
    }

    /**
     * Indicates whether the room should be deleted.
     *
     * @return True if the room should be deleted, false otherwise.
     */

    @Override
    public boolean toDelete() {
        return false;
    }
}