package main.java.com.mealforks.thelastknight.game;

import java.awt.*;
import java.util.ArrayList;

public class GameRoom implements GameObject {

    public final static GameRoom NONE = new GameRoom(GameRoomType.NONE);

    private String _id;
    private GameArea _area;
    private GameRoomType _type;
    private ArrayList<GameCharacter> _characters;
    private ArrayList<GameItem> _items;

    private GameRoom(GameRoomType type)
    {
        _type = type;
        _characters = new ArrayList<>();
        _items = new ArrayList<>();
    }

    public GameRoom(GameRoomType type, GameArea area)
    {
        _type = type;
        _area = area;
        _characters = new ArrayList<>();
        _items = new ArrayList<>();
    }

    public GameRoom()
    {
        _characters = new ArrayList<>();
        _items = new ArrayList<>();
        _type = GameRoomType.NONE;
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
        return null;
    }
}
