package main.java.com.mealforks.thelastknight.game;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Constructs a new GameData instance with default settings.
     */
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

    /**
     * Returns the player data.
     *
     * @return The player data.
     */
    public GamePlayerData getPlayerData()
    {
        return _playerData;
    }

    /**
     * Returns the pause menu.
     *
     * @return The pause menu.
     */
    public GamePauseMenu getPauseMenu()
    {
        return _pauseMenu;
    }

    /**
     * @return the save file.
     */
    public GameDataSave getSave()
    {
        GameDataSave save = new GameDataSave();

        save.setCurrentLevel(_level.getLevelIndex());
        save.setPlayerData(_playerData);

        return save;
    }
    /**
     * load save data
     *
     */
    public void loadFromSave(GameDataSave save)
    {
        _playerData = save.getPlayerData();
    }
    /**
     * Set player data.
     *
     */
    public void setPlayerData(GamePlayerData data)
    {
        _playerData = data;
    }

    /**
     * Gets the game setting.
     *
     * @return The game setting.
     */
    public GameSetting getGameSetting()
    {
        return _setting;
    }

    /**
     * @return The game state.
     */
    public GameState getGameState()
    {
        return _state;
    }

    /**
     * add music to buffer
     *
     * @param music the music to add
     */
    public void addMusicToBuffer(GameMusic music)
    {
        _musics.add(music);
    }

    /**
     * add sound to buffer
     *
     */
    public void addSoundToBuffer(GameSound sound)
    {
        _sounds.add(sound);
    }

    /**
     * dequeue sound
     *
     */
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

    /**
     * Sets the game state.
     *
     */
    public void setGameState(GameState state)
    {
        _state = state;
    }

    /**
     *
     * @return The game room.
     */
    public GameRoom getGameRoom()
    {
        return _room;
    }

    /**
     * Sets the game room.
     *
     */
    public void setGameRoom(GameRoom room)
    {
        _room = room;
    }

    /**
     * @return gameObjects.
     */
    public ArrayList<GameObject> getGameObjects()
    {
        return _gameObjects;
    }

    /**
     * @return inventory.
     */
    public GameInventory getInventory()
    {
        return  _inventory;
    }

    /**
     * @return the direction the player is looking.
     */
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

    /**
     * starts level 1 and initialize the character
     * @param skipCharacterCreation says if the character has to be created
     */
    public void startLevel(boolean skipCharacterCreation)
    {
        if (_level.getLevelIndex() == 0 && !skipCharacterCreation)
        {
            Random rng = new Random();

            GamePlayerClass playerClass = switch (rng.nextInt(3))
            {
                case 0 -> GamePlayerClass.KNIGHT;
                case 1 -> GamePlayerClass.MAGE;
                case 2 -> GamePlayerClass.THIEF;
                default -> GamePlayerClass.KNIGHT;
            };

            _playerData.setPlayerClass(playerClass);
            GamePlayerAttributes attributes = new GamePlayerAttributes();

            switch (playerClass) {
                case KNIGHT -> {
                    _playerData.setHealth(100 + rng.nextInt(5) * 10);
                    _playerData.setStamina(80 + rng.nextInt(5) * 10);
                    _playerData.setMana(10 + rng.nextInt(5) * 10);
                    attributes.setStrength(10 + rng.nextInt(5));
                    attributes.setEndurance(8 + rng.nextInt(5));
                    attributes.setLuck(3 + rng.nextInt(5));
                    attributes.setIntelligence(2 + rng.nextInt(5));
                    attributes.setAgility(4 + rng.nextInt(5));
                }
                case MAGE -> {
                    _playerData.setMana(100 + rng.nextInt(5) * 10);
                    _playerData.setStamina(60 + rng.nextInt(5) * 10);
                    _playerData.setHealth(30 + rng.nextInt(5) * 10);
                    attributes.setIntelligence(10 + rng.nextInt(5));
                    attributes.setLuck(7 + rng.nextInt(5));
                    attributes.setEndurance(5 + rng.nextInt(5));
                    attributes.setStrength(2 + rng.nextInt(5));
                    attributes.setAgility(4 + rng.nextInt(5));
                }
                case THIEF -> {
                    _playerData.setStamina(100 + rng.nextInt(5) * 10);
                    _playerData.setHealth(80 + rng.nextInt(5) * 10);
                    _playerData.setMana(10 + rng.nextInt(5) * 10);
                    attributes.setAgility(10 + rng.nextInt(5));
                    attributes.setEndurance(7 + rng.nextInt(5));
                    attributes.setLuck(6 + rng.nextInt(5));
                    attributes.setStrength(3 + rng.nextInt(5));
                    attributes.setIntelligence(2 + rng.nextInt(5));
                }
            }

            _playerData.setAttributes(attributes);

        }

        this.loadRoom(_level.getStartRoomId());
    }

    public void startLevel()
    {
        startLevel(false);
    }

    /**
     * loads the room
     * @param roomId id of the room.
     */
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

    /**
     * load the room
     * @param roomId room id
     * @param comingFrom tile where the player is coming
     */
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
