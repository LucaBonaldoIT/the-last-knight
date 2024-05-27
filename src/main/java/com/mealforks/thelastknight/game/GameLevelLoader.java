package main.java.com.mealforks.thelastknight.game;

import javax.json.*;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;


public class GameLevelLoader {
    public GameLevelLoader()
    {
    }

    public GameLevel loadLevel(String filename) {
        String jsonString  = "";
        try {
            jsonString  = new String(Files.readAllBytes(Paths.get(filename)));
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

        for(int i = 0; i < numberOfRooms; i++)
        {
            JsonObject room = jsonObject.getJsonObject("Room1");
            String id = room.getString("id");
            String type = room.getString("type");

            GameRoomType roomType = roomsType(type);

            String tiles = room.getString("tiles");
            String collisions = room.getString("collisions");

            GameArea area = gameArea(tiles, collisions);


        }

        GameLevel game = new GameLevel();

        return game;
    }

    public GameRoomType roomsType(String type)
    {
        switch (type)
        {
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

    public GameArea gameArea(String tiles, String collisions)
    {
        GameTile[][] gameTiles = new GameTile[15][8];
        GameCollision[][] gameCollision = new GameCollision[15][8];

        Scanner scanTiles = new Scanner(tiles);
        Scanner scanCollision = new Scanner(collisions);



        for (int j = 0; j < 8; j++)
        {
            for (int k = 0; k < 15; k++)
            {
                if (scanTiles.hasNext())
                {

                }
            }
        }
        return new GameArea(null, null);
    }

    public GameTile getTile(String tile)
    {
        switch (tile)
        {
            case "0":
                return GameTile.EMPTY;

            case "1":
                return GameTile.BRICK;

            case "2":
                return GameTile.FADING_BRICK;

            default:
                return GameTile.EMPTY;
        }

    }

}

