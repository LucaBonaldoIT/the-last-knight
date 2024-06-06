package test;

import static org.junit.Assert.*;


import main.java.com.mealforks.thelastknight.game.GameRoom;
import main.java.com.mealforks.thelastknight.game.GameData;
import main.java.com.mealforks.thelastknight.game.GamePlayer;
import main.java.com.mealforks.thelastknight.game.GamePoint;
import main.java.com.mealforks.thelastknight.game.GameTile;
import main.java.com.mealforks.thelastknight.game.GameObject;
import main.java.com.mealforks.thelastknight.game.GameArea;
import main.java.com.mealforks.thelastknight.game.GameRoomType;
import main.java.com.mealforks.thelastknight.game.GameRoomData;


import main.java.com.mealforks.thelastknight.game.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameRoomTest {

    private GameRoom room;
    private GameData testData;
    private GameObject object;
    private GamePlayer player;


    @Before
    public void setUp() {
        // Creare array bidimensionali di GameTile e GameCollision
        GameTile[][] tiles = new GameTile[10][10];
        GameCollision[][] collisions = new GameCollision[10][10];

        // Inizializzare i tiles e collisions con valori di esempio
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = GameTile.EMPTY;
                collisions[i][j] = GameCollision.NONE;
            }
        }



        // Configura la stanza con una porta a est
        HashMap<GamePoint, GameTile> doors = new HashMap<>();
        doors.put(new GamePoint(1, 1), GameTile.DOOR_EAST_WALL);
        room = new GameRoom("room1", GameRoomType.DEFAULT, new GameArea(tiles, collisions), new String[]{"room2", null, null, null}, doors, new GamePoint(0, 0), new HashMap<>());

        // Configura i dati del gioco e il giocatore
        testData = new GameData();
        player = new GamePlayer(1, 1);
        testData.setPlayer(player);
    }

    @Test
    public void testUpdate() {
        // Simula l'aggiornamento della stanza
        GameData updatedData = room.update(testData);

        // Verifica che la stanza successiva venga caricata
        assertEquals("", updatedData.getGameRoom().getId());
    }
















    @Before
    public void setUp1() {

        // Creare array bidimensionali di GameTile e GameCollision
        GameTile[][] tiles = new GameTile[10][10];
        GameCollision[][] collisions = new GameCollision[10][10];

        // Inizializzare i tiles e collisions con valori di esempio
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = GameTile.EMPTY;
                collisions[i][j] = GameCollision.NONE;
            }
        }
        // Configura la stanza con una porta
        HashMap<GamePoint, GameTile> doors = new HashMap<>();
        doors.put(new GamePoint(1, 1), GameTile.DOOR_EAST_WALL);
        room = new GameRoom("room1", GameRoomType.DEFAULT, new GameArea(tiles, collisions), new String[]{"room2", null, null, null}, doors, new GamePoint(0, 0), new HashMap<>());

        // Configura i dati del gioco
        testData = new GameData();
        object = new GameObject() {
            @Override
            public String getId() {
                return "object1";
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
                // Implementazione di esempio
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
        room.addObjectToLoad(object);
    }

    @Test
    public void testSaveRoomData() {
        // Salva i dati della stanza
        room.saveRoomData(testData);

        // Carica i dati della stanza in un nuovo oggetto GameData
        GameData loadedData = room.loadRoomData(new GameData());

        // Verifica che gli oggetti aggiunti siano presenti nei dati della stanza
        assertTrue(loadedData.getGameObjects().contains(object));
    }



    @Before
    public void setUp2() {
        room = new GameRoom();
        testData = new GameData();

        // Crea un oggetto di test e aggiungilo a testData
        object = new GameObject() {
            @Override
            public String getId() {
                return "1";
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
                // Logica di rendering
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
        testData.addObjectToScene(object);

        // Salva i dati della stanza
        room.saveRoomData(testData);
    }

    @Test
    public void testLoadRoomData() {
        // Carica i dati della stanza
        GameData loadedData = room.loadRoomData(new GameData());

        // Verifica che i dati caricati contengano l'oggetto originale
        ArrayList<GameObject> loadedObjects = loadedData.getGameObjects();
        assertEquals(1, loadedObjects.size());
        assertEquals("1", loadedObjects.get(0).getId());
    }
















    @Test
    public void testAddObjectToLoad() {
        // Preparazione
        GameRoom room = new GameRoom();
        GameObject object = new GameObject() {
            @Override
            public String getId() {
                return "1";
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
                // Logica di rendering
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

        // Esecuzione
        room.addObjectToLoad(object);

        // Confronto e verifica
        ArrayList<GameObject> loadedObjects = room.getObjectsToLoad();
        assertEquals(1, loadedObjects.size());
        assertEquals("1", loadedObjects.get(0).getId());
    }
}