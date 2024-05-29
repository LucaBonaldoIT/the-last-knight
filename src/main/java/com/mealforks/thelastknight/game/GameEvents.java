package main.java.com.mealforks.thelastknight.game;

import java.util.HashMap;

public class GameEvents {
    public static void TestDialog1(GameData d)
    {
        GameTile[][] tiles = new GameTile[][] {
            {GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK}
        };

        GameCollision[][] collisions = new GameCollision[][] {
            {GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.DOOR_NORTH_WALL, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK},
        };

        GameCollision[][] collisionsOther = new GameCollision[][] {
                {GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
                {GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.DOOR_SOUTH_WALL, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK},
        };

        HashMap<GamePoint, GameTile> doors = new HashMap<>();

        doors.put(new GamePoint(5, 0), GameTile.DOOR_NORTH_WALL);

        GameArea area = new GameArea(tiles, collisions);

        HashMap<GameTile, GamePoint> startPoints = new HashMap<>();

        startPoints.put(GameTile.DOOR_SOUTH_WALL, new GamePoint(5, 1));

        GameRoom room = new GameRoom("FIRST", GameRoomType.ENTRY, area, new String[] {"SECOND", null, null, null}, doors, new GamePoint(1, 1), startPoints);

        HashMap<GamePoint, GameTile> doorsOther = new HashMap<>();

        doorsOther.put(new GamePoint(5, 8), GameTile.DOOR_SOUTH_WALL);

        GameArea areaOther = new GameArea(tiles, collisionsOther);

        HashMap<GameTile, GamePoint> startPointsOther = new HashMap<>();

        startPointsOther.put(GameTile.DOOR_NORTH_WALL, new GamePoint(5, 7));

        GameRoom roomOther = new GameRoom("SECOND", GameRoomType.DEFAULT, areaOther, new String[] {null, null, "FIRST", null}, doorsOther, null, startPointsOther);

        HashMap<String, GameRoom> rooms = new HashMap<>();

        rooms.put("FIRST", room);
        rooms.put("SECOND", roomOther);

        roomOther.addObjectToLoad(new GameRoomItem(GameItemType.DOOR_KEY, 1, 1));

        roomOther.addObjectToLoad(new GameDialog("DOOR_CLOSE_BEHIND", "The door behind you is now locked."));
        roomOther.addObjectToLoad(new GameDoor(new GamePoint(5, 8)));
        roomOther.addObjectToLoad(new GameRoomItem(GameItemType.DOOR_KEY, 5, 3));
        roomOther.addObjectToLoad(new GameRoomItem(GameItemType.DOOR_KEY, 7, 3));

        GameLevel level = new GameLevel("FIRST", rooms);

        d.loadLevel(level);

        d.startLevel();
    }

    public static void StartLevel1(GameData d)
    {
        d.clearScene();

    }

    public static void StartLevel2(GameData d)
    {
        d.clearScene();


    }

    public static void StartLevel3(GameData d)
    {

    }
}
