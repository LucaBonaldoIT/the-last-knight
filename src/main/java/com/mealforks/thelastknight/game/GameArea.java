package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

/**
 * The GameArea class represents the game area where the game takes place.
 * It contains information about the tiles and collisions present in the game area.
 */
public class GameArea implements GameObject {

    private final GameTile[][] _tiles;
    private final GameCollision[][] _collision;

    /**
     * Constructor for the GameArea class.
     *
     * @param tiles     Matrix of GameTile representing the tiles present in the game area.
     * @param collision Matrix of GameCollision representing the collisions present in the game area.
     */
    public GameArea(GameTile[][] tiles, GameCollision[][] collision) {
        _tiles = tiles;
        _collision = collision;
    }

    /**
     * Checks if the tile at the specified coordinates is empty.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return true if the tile is empty, false otherwise.
     */
    public boolean IsTileEmpty(int x, int y) {
        return _collision[y][x] == GameCollision.EMPTY ||
                _collision[y][x] == GameCollision.DOOR_NORTH_WALL ||
                _collision[y][x] == GameCollision.DOOR_EAST_WALL ||
                _collision[y][x] == GameCollision.DOOR_WEST_WALL ||
                _collision[y][x] == GameCollision.DOOR_SOUTH_WALL;
    }

    /**
     * Retrieves the tile at the specified coordinates.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The GameTile object at the specified coordinates.
     */
    public GameTile getTile(int x, int y) {
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