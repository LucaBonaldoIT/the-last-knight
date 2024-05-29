package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GameCharacter implements GameObject {
    private String _id;
    private GamePoint _coordinates;
    private boolean _toDelete;

    @Override
    public String getId() {
        return _id;
    }

    public GameCharacter()
    {
        _id = "DEFAULT_CHARACTER_ID";
        _coordinates = GamePoint.NONE;
        _toDelete = false;
    }

    @Override
    public GamePoint getCoordinates() {
        return _coordinates;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public GameData update(GameData d) {
        return d;
    }

    @Override
    public boolean toDelete() {
        return _toDelete;
    }
}
