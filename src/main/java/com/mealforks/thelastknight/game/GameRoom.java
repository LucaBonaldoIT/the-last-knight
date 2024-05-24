package main.java.com.mealforks.thelastknight.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameRoom implements GameObject {

    public final static GameRoom NONE = new GameRoom(GameRoomType.NONE);

    private String _id;
    private GameArea _area;
    private GameRoomType _type;
    private ArrayList<GameCharacter> _characters;
    private ArrayList<GameItem> _items;
    private HashMap<GamePoint, GameTile> _doors;

    private GamePoint _defaultStartPoint;

    private HashMap<GameTile, GamePoint> _startPointByComingDoor;

    private String _northRoomId;
    private String _southRoomId;
    private String _eastRoomId;
    private String _westRoomId;

    private GameRoom(GameRoomType type)
    {
        _id = null;
        _type = type;
        _characters = new ArrayList<>();
        _items = new ArrayList<>();

        _doors = new HashMap<>();

        _northRoomId = null;
        _southRoomId = null;
        _westRoomId = null;
        _eastRoomId = null;
    }

    public GameRoom(GameRoomType type, GameArea area)
    {
        _type = type;
        _area = area;
        _characters = new ArrayList<>();
        _items = new ArrayList<>();
        _doors = new HashMap<>();
    }

    public GameRoom(String id, GameRoomType type, GameArea area, String[] rooms, HashMap<GamePoint, GameTile> doors, GamePoint defaultStartPoint, HashMap<GameTile, GamePoint> startPointByComingDoor)
    {
        _id = id;
        _type = type;
        _area = area;
        _characters = new ArrayList<>();
        _items = new ArrayList<>();

        _northRoomId = rooms[0];
        _eastRoomId = rooms[1];
        _southRoomId = rooms[2];
        _westRoomId = rooms[3];

        _doors = doors;

        _defaultStartPoint = defaultStartPoint;

        _startPointByComingDoor = startPointByComingDoor;
    }

    public GameRoom()
    {
        _characters = new ArrayList<>();
        _items = new ArrayList<>();
        _type = GameRoomType.NONE;
    }

    public GamePoint getStartPoint(GameTile comingDoor)
    {
        return _startPointByComingDoor.get(comingDoor);
    }

    public GamePoint getDefaultStartPoint()
    {
        return _defaultStartPoint;
    }

    public GameArea getGameArea() {
        return _area;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {
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

        for (GameCharacter character : _characters) {
            character.render(g);
        }

        for (GameItem item : _items)
        {
            item.render(g);
        }
    }

    @Override
    public GameData update(GameData d) {

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

    @Override
    public boolean toDelete() {
        return false;
    }
}
