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
        return _collision[y][x] == GameCollision.EMPTY;
    }

    @Override
    public String getId() {
        return "GAME_AREA";
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
}
