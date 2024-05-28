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

            String northRoomId = room.getString("northRoomId");
            String southRoomId = room.getString("southRoomId");
            String eastRoomId = room.getString("eastRoomId");
            String westRoomId = room.getString("westRoomId");

            ArrayList<GameRoomItem> items = new ArrayList<>();
            JsonObject jsonItems = room.getJsonObject("items");

            for (Map.Entry<String, JsonValue> entry : jsonItems.entrySet()) {
                String key = entry.getKey();
                JsonValue value = entry.getValue();
                String coordinates = ((JsonString) value).getString();

                String[] coordinatesArray = coordinates.split(" ");
                int x = Integer.parseInt(coordinatesArray[0]);
                int y = Integer.parseInt(coordinatesArray[1]);
                GameItemType itemType = getItemType(key);
                GameRoomItem item = new GameRoomItem(itemType, x, y);
                items.add(item);
            }
        }


        GameLevel game = new GameLevel();

        return game;
    }


    public GameItemType getItemType(String type) {
        switch (type) {
            case "key":
                return GameItemType.DOOR_KEY;
            case "health_potion":
                return GameItemType.HEALTH_POTION;
            case "luck_amulet":
                return GameItemType.LUCK_AMULET;
            case "strength_amulet":
                return GameItemType.STRENGTH_AMULET;
            case "wooden_sword":
                return GameItemType.WOODEN_SWORD;
            case "iron_sword":
                return GameItemType.IRON_SWORD;
            case "infernal_sword":
                return GameItemType.INFERNAL_SWORD;
            case "wooden_wand":
                return GameItemType.WOODEN_WAND;
            case "crystal_wand":
                return GameItemType.CRYSTAL_WAND;
            case "celestial_wand":
                return GameItemType.CELESTIAL_WAND;
            case "leather_armor":
                return GameItemType.LEATHER_ARMOR;
            case "iron_armor":
                return GameItemType.IRON_ARMOR;
            default:
                return GameItemType.NONE;
        }
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
