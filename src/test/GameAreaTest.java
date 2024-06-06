package test;

import static org.junit.jupiter.api.Assertions.*;


import main.java.com.mealforks.thelastknight.game.GameArea;
import main.java.com.mealforks.thelastknight.game.GameCollision;
import main.java.com.mealforks.thelastknight.game.GamePoint;
import main.java.com.mealforks.thelastknight.game.GameTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameAreaTest {

    private GameTile[][] tiles;
    private GameCollision[][] collisions;
    private GameArea gameArea;

    @BeforeEach
    void setUp() {
        // Setup the tiles and collisions for testing
        tiles = new GameTile[5][5];
        collisions = new GameCollision[5][5];

        // Fill the arrays with dummy data
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                tiles[y][x] = (GameTile.BRICK);
                collisions[y][x] = GameCollision.EMPTY;
            }
        }

        gameArea = new GameArea(tiles, collisions);
    }

    @Test
    void testIsTileEmpty() {
        // Set specific collision types for testing
        collisions[1][1] = GameCollision.EMPTY;
        collisions[2][2] = GameCollision.DOOR_NORTH_WALL;
        collisions[3][3] = GameCollision.BLOCK;

        assertTrue(gameArea.IsTileEmpty(1, 1));
        assertTrue(gameArea.IsTileEmpty(2, 2));
        assertFalse(gameArea.IsTileEmpty(3, 3));
    }

    @Test
    void testGetTile() {
        assertEquals(tiles[1][1], gameArea.getTile(1, 1));
        assertEquals(tiles[3][3], gameArea.getTile(3, 3));
    }
}