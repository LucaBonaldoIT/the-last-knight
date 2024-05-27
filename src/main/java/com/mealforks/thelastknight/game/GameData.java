package main.java.com.mealforks.thelastknight.game;

import java.util.ArrayList;

public class GameData {
    private final GameSetting _setting;
    private GameState _state;
    private ArrayList<GameObject> _gameObjects;
    private GameRoom _room;
    private GamePlayer _player;
    private GameInput _input;
    private ArrayList<GameSound> _sounds;
    private ArrayList<GameMusic> _musics;
    private GameLevel _level;
    private GamePlayerData _playerData;

    public GameLevel getCurrentLevel()
    {
        return _level;
    }

    public GameData()
    {
        _setting = GameSetting.getDefault();
        _state = GameState.READY;
        _gameObjects = new ArrayList<>();
        _input = GameInput.NONE;
        _room = GameRoom.NONE;
        _level = new GameLevel();
        _playerData = new GamePlayerData();
        _player = new GamePlayer(0, 0);
    }

    public GamePlayerData getPlayerData()
    {
        return _playerData;
    }

    public void setPlayerData(GamePlayerData data)
    {
        _playerData = data;
    }

    public GameSetting getGameSetting()
    {
        return _setting;
    }

    public GameState getGameState()
    {
        return _state;
    }

    public void addMusicToBuffer(GameMusic music)
    {
        _musics.add(music);
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

    public void setPlayer(GamePlayer player)
    {
        _player = player;
    }

    public GamePlayer getPlayer()
    {
        return _player;
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

    public void loadLevel(GameLevel level)
    {
        _level = level;
    }

    public void startLevel()
    {
        this.loadRoom(_level.getStartRoomId());
    }

    public void loadRoom(String roomId)
    {
        this.clearScene();
        this.setGameState(GameState.RUNNING);

        GameRoom room = _level.getRoom(roomId);

        for (GameObject obj : room.getObjectsToLoad())
        {
            this.addObjectToScene(obj);
        }

        this.setGameRoom(room);
        this.setPlayer(new GamePlayer(room.getDefaultStartPoint().x, room.getDefaultStartPoint().y));
    }

    public void loadRoom(String roomId, GameTile comingFrom)
    {
        this.clearScene();
        this.setGameState(GameState.RUNNING);

        GameRoom room = _level.getRoom(roomId);

        for (GameObject obj : room.getObjectsToLoad())
        {
            this.addObjectToScene(obj);
        }

        this.setGameRoom(room);

        GameDirection facing = GameDirection.DOWN;

        switch (comingFrom)
        {
            case DOOR_NORTH_WALL:
            {
                facing = GameDirection.UP;
                break;
            }
            case DOOR_EAST_WALL:
            {
                facing = GameDirection.RIGHT;
                break;
            }
            case DOOR_WEST_WALL:
            {
                facing = GameDirection.LEFT;
                break;
            }
            default:
            {
                break;
            }
        }

        this.setPlayer(new GamePlayer(room.getStartPoint(comingFrom).x, room.getStartPoint(comingFrom).y, facing));
    }
}
