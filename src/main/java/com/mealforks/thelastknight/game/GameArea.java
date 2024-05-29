package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GameArea implements GameObject {
    private final GameTile[][] _tiles;
    private final GameCollision[][] _collision;

    public GameArea(GameTile[][] tiles, GameCollision[][] collision)
    {
        _tiles = tiles;
        _collision = collision;
    }

    public boolean IsTileEmpty(int x, int y)
    {
        return _collision[y][x] == GameCollision.EMPTY || _collision[y][x] == GameCollision.DOOR_NORTH_WALL || _collision[y][x] == GameCollision.DOOR_EAST_WALL || _collision[y][x] == GameCollision.DOOR_WEST_WALL || _collision[y][x] == GameCollision.DOOR_SOUTH_WALL ;
    }

    public GameTile getTile(int x, int y)
    {
        return _tiles[y][x];
    }

    @Override
    public String getId() {
        return "GAME_AREA";
    }

    @Override
    public GamePoint getCoordinates() {
        return GamePoint.NONE;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {
        int tileSize = GameConstants.getTileSize();
        for (int y = 0; y < _tiles.length; y++) {
            for (int x = 0; x < _tiles[y].length; x++) {
                GameTile tile = _tiles[y][x];
                g.drawImage(GameConstants.getTile(tile), x * tileSize, y * tileSize, null);
            }
        }
    }

    @Override
    public GameData update(GameData d) {
        return d;
    }

    @Override
    public boolean toDelete() {
        return false;
    }
}

