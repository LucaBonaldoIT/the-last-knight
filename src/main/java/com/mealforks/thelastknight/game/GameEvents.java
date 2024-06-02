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

        //GameLevelLoader loader = new GameLevelLoader();
        //GameLevel level = loader.loadLevel("src\\main\\resources\\levels\\level1.json");

        roomOther.addObjectToLoad(GameCharacter.getGiftCharacter(GameConstants.getItem(GameItemType.HEALTH_POTION), GameSprite.PLAYER_FACING_DOWN, new GamePoint(2, 4)));
        roomOther.addObjectToLoad(GameCharacter.getTradeCharacter(GameSprite.PLAYER_FACING_DOWN, new GamePoint(3, 4)));

        if (false)
        room.addObjectToLoad(new GameNarratorDialog("In the age of Elderia, a realm of ancient magic and timeless legends, peace reigned for a thousand years under the watchful eyes of the celestial Guardians. These mystical beings, chosen by fate and bound by duty, wielded the elemental forces of fire, water, earth, and air to safeguard the balance of the world.\n" +
                "\n" +
                "However, the fabric of this harmony began to unravel when the sinister sorcerer, Malakar the Accursed, emerged from the shadows. Possessed by a malevolent force known only as the Void, Malakar sought to harness the elemental powers for his own dark designs. With the promise of eternal dominion, he seduced and corrupted the minds of many, raising an army of twisted creatures and dark-hearted men.\n" +
                "\n" +
                "As Malakar’s influence spread like a blight across the land, the once vibrant kingdoms of Elderia fell into despair. Cities were reduced to ruins, and forests withered under the taint of his dark magic. The Guardians, outnumbered and overpowered, retreated into the hidden sanctuaries of old, their light dimming in the face of overwhelming darkness.\n" +
                "\n" +
                "But hope remained. In the humble village of Ravenwood, a prophecy whispered through the ages began to stir. It spoke of a chosen one, a hero born of both light and shadow, who would rise to challenge the darkness and restore balance to Elderia. This hero, a child of destiny, would possess the heart of a lion, the wisdom of the ancients, and the unyielding spirit of the elements themselves.\n" +
                "\n" +
                "In the stillness of the night, as stars aligned and the celestial bodies danced in the heavens, the signs foretold by the prophecy became clear. You, the chosen one, are called upon to embark on a perilous journey. With the weight of the world on your shoulders, you must seek out the remnants of the Guardians, unlock the ancient secrets of the elemental forces, and confront Malakar in his fortress of shadows.\n" +
                "\n" +
                "The path ahead is fraught with danger and treachery. You will face fearsome beasts, navigate treacherous landscapes, and encounter allies and enemies alike, each with their own hidden agendas. The line between friend and foe will blur as you delve deeper into the mysteries of Elderia, unearthing truths that have been buried for centuries.\n" +
                "\n" +
                "With each step, your resolve will be tested, and your choices will shape the fate of the realm. Will you succumb to the darkness, or will you rise above it, becoming the beacon of hope that Elderia so desperately needs? The time has come to write your legend, to carve your name into the annals of history, and to fulfill the ancient prophecy.\n" +
                "\n" +
                "May the light guide you, brave hero, and may your journey be remembered for all time...\n" +
                "\n" +
                "Prepare yourself, for the adventure of a lifetime awaits.\n" +
                "\n"));

        GameLevel level = new GameLevel("FIRST", rooms);

        d.loadLevel(level);

        d.startLevel();
    }

    public static void LoadLevel1(GameData d)
    {
        LoadLevel1(d, null);
    }

    public static void LoadLevel1(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level1.json");


        if (save != null)
        {

        }


    }

    public static void LoadLevel2(GameData d)
    {
        LoadLevel2(d, null);
    }

    public static void LoadLevel2(GameData d, GameDataSave save)
    {
    }

    public static void LoadLevel3(GameData d)
    {
        LoadLevel3(d, null);
    }

    public static void LoadLevel3(GameData d, GameDataSave save)
    {

    }

    public static void LoadLevelBoss(GameData d)
    {
        LoadLevelBoss(d, null);
    }

    public static void LoadLevelBoss(GameData d, GameDataSave save)
    {

    }
}
