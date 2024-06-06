package test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GameRoomTest {
    private GameRoom gameRoom;
    private GameRoomType type;
    private GameArea area;
    private GamePoint point;
    private GameTile tile;
    private GameData data;
    private GamePlayer player;


    @Before
    public void setUp() {
        GameTile[][] tiles = new GameTile[10][10];
        GameCollision[][] collisions = new GameCollision[10][10];

        // Inizializzare i tiles e collisions con valori di esempio
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = GameTile.EMPTY;
                collisions[i][j] = GameCollision.NONE;
            }
        }

        type = GameRoomType.DEFAULT;
        area = new GameArea(tiles, collisions);
        point = new GamePoint(1, 1);
        tile = GameTile.DOOR_EAST_WALL;
        data = new GameData();

        gameRoom = new GameRoom(type, area);
    }

    @Test
    public void testAddObjectToLoad() {
        GameObject obj = new GameObject() {
            @Override
            public String getId() {
                return "test";
            }

            @Override
            public GamePoint getCoordinates() {
                return new GamePoint(0, 0);
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
                return false;
            }
        };

        gameRoom.addObjectToLoad(obj);
        assertEquals(1, gameRoom.getObjectsToLoad().size());
    }

    @Test
    public void testLoadRoomData() {
        gameRoom.saveRoomData(data);
        GameData newData = gameRoom.loadRoomData(data);
        assertNotNull(newData);
        // Add further assertions based on the expected state of newData
    }

    @Before
    public void setUp1() {
        // Inizializzare i tiles e collisions con valori di esempio
        GameTile[][] tiles = new GameTile[10][10];
        GameCollision[][] collisions = new GameCollision[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = GameTile.EMPTY;
                collisions[i][j] = GameCollision.NONE;
            }
        }

        // Configura la stanza con una porta a est
        HashMap<GamePoint, GameTile> doors = new HashMap<>();
        doors.put(new GamePoint(1, 1), GameTile.DOOR_EAST_WALL);
        gameRoom = new GameRoom("room1", GameRoomType.DEFAULT, new GameArea(tiles, collisions), new String[]{"room2", null, null, null}, doors, new GamePoint(0, 0), new HashMap<>());

        // Configura i dati del gioco e il giocatore
        data = new GameData();
        player = new GamePlayer(1, 1);
        data.setPlayer(player);
    }

    @Test
    public void testUpdate() {
        // Inizializzare i tiles e collisions con valori di esempio
        GameTile[][] tiles = new GameTile[10][10];
        GameCollision[][] collisions = new GameCollision[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = GameTile.EMPTY;
                collisions[i][j] = GameCollision.NONE;
            }
        }
        GameData testData = new GameData();
        GameRoom room = new GameRoom("roomId", GameRoomType.DEFAULT, new GameArea(tiles, collisions), new String[]{"north", "east", "south", "west"}, new HashMap<>(), new GamePoint(1, 1), new HashMap<>());
        GamePlayer player = new GamePlayer(1,1);
        testData.setPlayer(player);

        // Simulate player moving through east door
        GameData updatedData = room.update(testData);

        assertEquals("", updatedData.getGameRoom().getId());
    }

    @Test
    public void testToDelete() {
        assertFalse(gameRoom.toDelete());
    }
}


