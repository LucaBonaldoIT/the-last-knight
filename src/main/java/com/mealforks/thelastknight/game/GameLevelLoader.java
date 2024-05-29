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


public class GameLevelLoader {
    public GameLevelLoader() {
    }

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
        HashMap<String, GameRoom> rooms;

        int numberOfRooms = jsonObject.getInt("roomNumber");

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
            String[] adjacentRooms = {northRoomId,southRoomId,eastRoomId,westRoomId};

            ArrayList<GameObject> objectsToLoad = new ArrayList<>();
            JsonObject jsonItems = room.getJsonObject("ObjectToLoad");

            for (Map.Entry<String, JsonValue> entry : jsonItems.entrySet()) {
                String ObjectId = entry.getKey();
                JsonValue value = entry.getValue();
                String coordinates = ((JsonString) value).getString();

                String[] coordinatesArray = coordinates.split(" ");
                int x = Integer.parseInt(coordinatesArray[0]);
                int y = Integer.parseInt(coordinatesArray[1]);
                GameObject object = getGameObject(ObjectId, new GamePoint(x,y));
                objectsToLoad.add(object);
            }

            HashMap<GamePoint, GameTile> doors = new HashMap<>();
            JsonObject jsonDoors = room.getJsonObject("doors");

            for (Map.Entry<String, JsonValue> entry : jsonDoors.entrySet()) {
                String doorId = entry.getKey();
                JsonValue value = entry.getValue();

                String coordinates = ((JsonString) value).getString();
                String[] coordinatesArray = coordinates.split(" ");
                int x = Integer.parseInt(coordinatesArray[0]);
                int y = Integer.parseInt(coordinatesArray[1]);
                GameTile door;

                switch (doorId){
                    case "north" :
                        door = GameTile.DOOR_NORTH_WALL;
                    case "south" :
                        door = GameTile.DOOR_SOUTH_WALL;
                    case "east" :
                        door = GameTile.DOOR_EAST_WALL;
                    case "west" :
                        door = GameTile.DOOR_WEST_WALL;
                    default:
                        door = GameTile.NONE;
                }
                doors.put(new GamePoint(x, y), door);
            }
            GameRoom currentRoom = new GameRoom(id, roomType, area, adjacentRooms, doors, defaultStartPoint, );
        }

        GameLevel game = new GameLevel();

        return game;
    }


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

    public GameArea getGameArea(String tiles, String collisions) {
        GameTile[][] gameTiles = new GameTile[15][8];
        GameCollision[][] gameCollision = new GameCollision[15][8];

        Scanner scanTiles = new Scanner(tiles);
        Scanner scanCollision = new Scanner(collisions);

        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 15; k++) {
                GameTile t = GameTile.EMPTY;
                GameCollision c = GameCollision.EMPTY;

                if (scanTiles.hasNext()) {
                    String s = scanTiles.next();

                    switch (s) {
                        case "0":
                            t = GameTile.EMPTY;
                        case "1":
                            t = GameTile.BRICK;
                        case "2":
                            t = GameTile.FADING_BRICK;
                        case "3":
                            t = GameTile.DOOR_NORTH_WALL;
                        case "4":
                            t = GameTile.DOOR_SOUTH_WALL;
                        case "5":
                            t = GameTile.DOOR_EAST_WALL;
                        case "6":
                            t = GameTile.DOOR_WEST_WALL;
                        default:
                            t = GameTile.NONE;
                    }
                }
                if (scanCollision.hasNext()) {
                    String s = scanCollision.next();

                    switch (s) {
                        case "0":
                            c = GameCollision.EMPTY;
                        case "1":
                            c = GameCollision.BLOCK;
                        case "2":
                            c = GameCollision.DOOR_NORTH_WALL;
                        case "3":
                            c = GameCollision.DOOR_SOUTH_WALL;
                        case "4":
                            c = GameCollision.DOOR_EAST_WALL;
                        case "5":
                            c = GameCollision.DOOR_WEST_WALL;
                        default:
                            c = GameCollision.NONE;
                    }
                }
                gameTiles[j][k] = t;
            }
        }
        return new GameArea(gameTiles, gameCollision);
    }
}
