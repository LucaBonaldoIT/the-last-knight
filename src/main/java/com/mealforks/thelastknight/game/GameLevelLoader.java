package main.java.com.mealforks.thelastknight.game;

import javax.json.*;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static main.java.com.mealforks.thelastknight.game.GameConstants.getGameObject;

/**
 * Singleton class responsible for loading game levels from JSON files.
 */
public class GameLevelLoader {
    private static GameLevelLoader _instance;

    /**
     * Private constructor to prevent instantiation.
     */
    private GameLevelLoader() {
    }

    /**
     * Returns the singleton instance of the GameLevelLoader.
     *
     * @return the singleton instance.
     */
    public static GameLevelLoader getInstance() {
        if (_instance == null) {
            _instance = new GameLevelLoader();
        }
        return _instance;
    }

    /**
     * Loads a game level from a JSON file.
     *
     * @param filename the name of the JSON file containing the level data.
     * @return the loaded GameLevel object.
     */
    public GameLevel loadLevel(String filename) {
        String jsonString = "";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String startRoomId = "0";
        HashMap<String, GameRoom> rooms = new HashMap<>();
        int numberOfRooms = jsonObject.getInt("roomNumber");
        int levelIndex = jsonObject.getInt("levelIndex");

        // Parse each room
        for (int i = 0; i < numberOfRooms; i++) {
            JsonObject room = jsonObject.getJsonObject("Room" + i);
            String id = room.getString("id");
            String type = room.getString("type");
            GameRoomType roomType = getRoomType(type);

            String startPoint = room.getString("defaultStartPoint");
            String[] startPoints = startPoint.split(" ");
            GamePoint defaultStartPoint = new GamePoint(Integer.parseInt(startPoints[0]), Integer.parseInt(startPoints[1]));

            JsonObject areaJson = room.getJsonObject("area");
            String tiles = areaJson.getString("tiles");
            String collisions = areaJson.getString("collisions");
            GameArea area = getGameArea(tiles, collisions);

            String northRoomId = room.getString("northRoomId");
            String southRoomId = room.getString("southRoomId");
            String eastRoomId = room.getString("eastRoomId");
            String westRoomId = room.getString("westRoomId");
            String[] adjacentRooms = {northRoomId, eastRoomId, southRoomId, westRoomId};

            HashMap<GamePoint, GameTile> doors = new HashMap<>();
            JsonObject jsonDoors = room.getJsonObject("doors");
            JsonObject startPointDoorsJson = room.getJsonObject("startPointByComingDoor");
            HashMap<GameTile, GamePoint> startPointByComingDoor = new HashMap<>();

            for (Map.Entry<String, JsonValue> entry : startPointDoorsJson.entrySet()) {
                String doorId = entry.getKey();
                JsonValue value = entry.getValue();
                String coordinates = ((JsonString) value).getString();
                String[] coordinatesArray = coordinates.split(" ");
                int x = Integer.parseInt(coordinatesArray[0]);
                int y = Integer.parseInt(coordinatesArray[1]);
                GameTile door;
                int newX = 0;
                int newY = 0;

                switch (doorId) {
                    case "north":
                        door = GameTile.DOOR_NORTH_WALL;
                        newX = x;
                        newY = y;
                        break;

                    case "south":
                        door = GameTile.DOOR_SOUTH_WALL;
                        newX = x;
                        newY = y;
                        break;

                    case "east":
                        door = GameTile.DOOR_EAST_WALL;
                        newX = x;
                        newY = y;
                        break;

                    case "west":
                        door = GameTile.DOOR_WEST_WALL;
                        newX = x;
                        newY = y;
                        break;
                    default:
                        door = GameTile.NONE;
                        newX = x;
                        newY = y;
                        break;
                }
                startPointByComingDoor.put(door, new GamePoint(x, y));
            }

            for (Map.Entry<String, JsonValue> entry : jsonDoors.entrySet()) {
                String doorId = entry.getKey();
                JsonValue value = entry.getValue();
                String coordinates = ((JsonString) value).getString();
                String[] coordinatesArray = coordinates.split(" ");
                int x = Integer.parseInt(coordinatesArray[0]);
                int y = Integer.parseInt(coordinatesArray[1]);
                GameTile door;

                switch (doorId) {
                    case "north":
                        door = GameTile.DOOR_NORTH_WALL;
                        break;
                    case "south":
                        door = GameTile.DOOR_SOUTH_WALL;
                        break;
                    case "east":
                        door = GameTile.DOOR_EAST_WALL;
                        break;
                    case "west":
                        door = GameTile.DOOR_WEST_WALL;
                        break;
                    default:
                        door = GameTile.NONE;
                        break;
                }
                doors.put(new GamePoint(x, y), door);
            }

            GameRoom currentRoom = new GameRoom(id, roomType, area, adjacentRooms, doors, defaultStartPoint, startPointByComingDoor);

            JsonObject jsonObjectsToLoad = room.getJsonObject("objectsToLoad");
            for (Map.Entry<String, JsonValue> entry : jsonObjectsToLoad.entrySet()) {
                String objectId = entry.getKey();
                JsonValue value = entry.getValue();
                String coordinates = ((JsonString) value).getString();
                String[] coordinatesArray = coordinates.split(" ");
                int x = Integer.parseInt(coordinatesArray[0]);
                int y = Integer.parseInt(coordinatesArray[1]);
                GameObject object = getGameObject(objectId, new GamePoint(x, y));
                currentRoom.addObjectToLoad(object);
            }

            rooms.put(id, currentRoom);
        }

        GameLevel game = new GameLevel(startRoomId, rooms, levelIndex);
        return game;
    }

    /**
     * Returns the GameRoomType corresponding to the given type string.
     *
     * @param type the type string.
     * @return the corresponding GameRoomType.
     */
    public GameRoomType getRoomType(String type) {
        switch (type) {
            case "unknown":
                return GameRoomType.UNKNOWN;
            case "entry":
                return GameRoomType.ENTRY;
            case "exit":
                return GameRoomType.EXIT;
            case "default":
                return GameRoomType.DEFAULT;
            case "boss":
                return GameRoomType.BOSS;
            default:
                return GameRoomType.NONE;
        }
    }

    /**
     * Returns a GameArea object constructed from tile and collision data strings.
     *
     * @param tiles the tile data string.
     * @param collisions the collision data string.
     * @return the constructed GameArea object.
     */
    public GameArea getGameArea(String tiles, String collisions) {
        GameTile[][] gameTiles = new GameTile[9][15];
        GameCollision[][] gameCollision = new GameCollision[9][15];

        Scanner scanTiles = new Scanner(tiles);
        Scanner scanCollision = new Scanner(collisions);

        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 15; k++) {
                GameTile t = GameTile.EMPTY;
                GameCollision c = GameCollision.EMPTY;

                if (scanTiles.hasNext()) {
                    String s = scanTiles.next();
                    switch (s) {
                        case "0":
                            t = GameTile.EMPTY;
                            break;
                        case "1":
                            t = GameTile.BRICK;
                            break;
                        case "2":
                            t = GameTile.FADING_BRICK;
                            break;
                        case "3":
                            t = GameTile.DOOR_NORTH_WALL;
                            break;
                        case "4":
                            t = GameTile.DOOR_SOUTH_WALL;
                            break;
                        case "5":
                            t = GameTile.DOOR_EAST_WALL;
                            break;
                        case "6":
                            t = GameTile.DOOR_WEST_WALL;
                            break;
                        default:
                            t = GameTile.NONE;
                            break;
                    }
                }
                if (scanCollision.hasNext()) {
                    String s = scanCollision.next();
                    switch (s) {
                        case "0":
                            c = GameCollision.EMPTY;
                            break;
                        case "1":
                            c = GameCollision.BLOCK;
                            break;
                        case "3":
                            c = GameCollision.DOOR_NORTH_WALL;
                            break;
                        case "4":
                            c = GameCollision.DOOR_SOUTH_WALL;
                            break;
                        case "5":
                            c = GameCollision.DOOR_EAST_WALL;
                            break;
                        case "6":
                            c = GameCollision.DOOR_WEST_WALL;
                            break;
                        default:
                            c = GameCollision.NONE;
                            break;
                    }
                }
                gameTiles[j][k] = t;
                gameCollision[j][k] = c;
            }
        }
        scanTiles.close();
        scanCollision.close();
        return new GameArea(gameTiles, gameCollision);
    }
}
