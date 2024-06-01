package main.java.com.mealforks.thelastknight.game;

import java.io.File;
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
    private GameInventory _inventory;
    private GamePauseMenu _pauseMenu;

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
        _inventory = new GameInventory();
        _pauseMenu = new GamePauseMenu();
        _sounds = new ArrayList<>();
        _musics = new ArrayList<>();
    }

    public GamePlayerData getPlayerData()
    {
        return _playerData;
    }

    public GamePauseMenu getPauseMenu()
    {
        return _pauseMenu;
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

    public void addSoundToBuffer(GameSound sound)
    {
        _sounds.add(sound);
    }

    public GameSound dequeueSound()
    {
        if (_sounds.isEmpty())
        {
            return GameSound.NONE;
        }

        GameSound sound = _sounds.get(0);
        _sounds.remove(0);
        return sound;
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

    public GameInventory getInventory()
    {
        return  _inventory;
    }

    public boolean isPlayerLookingAt(GameObject obj)
    {
        GamePlayer player = this.getPlayer();

        int x = player.getX();
        int y = player.getY();

        GamePoint objCoords = obj.getCoordinates();

        if (Math.abs(x - objCoords.x) + Math.abs(y - objCoords.y) >= 2)
        {
            return false;
        }

        if (x - objCoords.x == 1)
        {
            return player.getFacingDirection().equals(GameDirection.LEFT);
        }

        if (x - objCoords.x == -1)
        {
            return player.getFacingDirection().equals(GameDirection.RIGHT);
        }

        if (y - objCoords.y == 1)
        {
            return player.getFacingDirection().equals(GameDirection.UP);
        }

        if (y - objCoords.y == -1)
        {
            return player.getFacingDirection().equals(GameDirection.DOWN);
        }

        return false;
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
        if (!_room.equals(GameRoom.NONE))
        {
            _room.saveRoomData(this);
        }

        this.clearScene();
        this.setGameState(GameState.RUNNING);

        GameRoom room = _level.getRoom(roomId);

        for (GameObject obj : room.getObjectsToLoad())
        {
            this.addObjectToScene(obj);
        }

        room.loadRoomData(this);

        this.setGameRoom(room);
        this.setPlayer(new GamePlayer(room.getDefaultStartPoint().x, room.getDefaultStartPoint().y));
    }

    public void loadRoom(String roomId, GameTile comingFrom)
    {
        if (!_room.equals(GameRoom.NONE))
        {
            _room.saveRoomData(this);
        }

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

        room.loadRoomData(this);

        this.setPlayer(new GamePlayer(room.getStartPoint(comingFrom).x, room.getStartPoint(comingFrom).y, facing));
    }
}
