package main.java.com.mealforks.thelastknight.game;

import javax.json.*;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;


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

        System.out.println(numberOfRooms);

        for (int i = 0; i < numberOfRooms; i++) {
            JsonObject room = jsonObject.getJsonObject("Room" + i);
            String id = room.getString("id");
            String type = room.getString("type");

            GameRoomType roomType = getRoomType(type);

            String tiles = room.getString("tiles");
            String collisions = room.getString("collisions");

            GameArea area = getGameArea(tiles, collisions);


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
                            defualt:
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
