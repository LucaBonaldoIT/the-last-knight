package main.java.com.mealforks.thelastknight.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

public class GameConstants
{
    public static Font _font;
    public static Map<GameTile, Image> _tiles; //tiles for gameElements
    public static Map<GameSprite, Image> _sprites; //game Sprites
    public static Map<GameSound, File> _sounds;

    static
    {
        try {
            //create the font to use.
            _font = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\resources\\fonts\\font.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(_font);
} catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

        _tiles = new HashMap<GameTile, Image>();
        _sprites = new HashMap<GameSprite, Image>();

        BufferedImage tileset = null;

        //tileset to use for game tiles
        try {
            tileset = ImageIO.read(new File("src\\main\\resources\\images\\tileset.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int tileSize = 32; // Change this to the actual size of your tiles
        int rows = tileset.getHeight() / tileSize;
        int cols = tileset.getWidth() / tileSize;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int tileIndex = y * cols + x;
                switch(tileIndex)
                {
                    case 0: {
                        _tiles.put(GameTile.EMPTY, tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 1: {
                        _tiles.put(GameTile.BRICK, tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 10: {
                        _tiles.put(GameTile.FADING_BRICK, tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 61: {
                        _tiles.put(GameTile.DOOR_NORTH_WALL, tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    }
                    case 62: {
                        _tiles.put(GameTile.DOOR_WEST_WALL, tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    }
                    case 80: {
                        _tiles.put(GameTile.DOOR_SOUTH_WALL, tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    }
                    case 81: {
                        _tiles.put(GameTile.DOOR_EAST_WALL, tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        }


        //tileset for NPCS
        BufferedImage npcImage = null;
        try {
            npcImage = ImageIO.read(new File("src\\main\\resources\\images\\NPCS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int row = tileset.getHeight() / tileSize;
        int col = tileset.getWidth() / tileSize;

        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                int tileIndex = y * col + x;
                switch (tileIndex) {
                    case 40:
                        _sprites.put(GameSprite.TRADER, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    case 37:
                        _sprites.put(GameSprite.GIFT_CHARACTER, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    case 36:
                        _sprites.put(GameSprite.TEXT_ONLY, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    case 24:
                        _sprites.put(GameSprite.ENEMY, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    case 82:
                        _sprites.put(GameSprite.MINI_BOSS, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    case 83:
                        _sprites.put(GameSprite.MINI_BOSS1, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    case 81:
                        _sprites.put(GameSprite.MINI_BOSS2, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    case 85:
                        _sprites.put(GameSprite.BOSS, npcImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                }
            }
        }


        //Item Sprites
        BufferedImage itemsSprite = null;

        try {
            itemsSprite = ImageIO.read(new File("src\\main\\resources\\images\\ItemSprites.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int rowsItems = itemsSprite.getHeight() / tileSize;
        int colsItems = itemsSprite.getWidth() / tileSize;

        for (int y = 0; y < rowsItems; y++) {
            for (int x = 0; x < colsItems; x++) {
                int tileIndex = y * colsItems + x;
                switch(tileIndex)
                {
                    case 0: {
                        _sprites.put(GameSprite.DOOR_KEY, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 1: {
                        _sprites.put(GameSprite.HEALTH_POTION, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 2: {
                        _sprites.put(GameSprite.LUCK_AMULET, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 3: {
                        _sprites.put(GameSprite.STRENGTH_AMULET, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 4: {
                        _sprites.put(GameSprite.WOODEN_SWORD, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 5: {
                        _sprites.put(GameSprite.IRON_SWORD, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 6: {
                        _sprites.put(GameSprite.INFERNAL_SWORD, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 7: {
                        _sprites.put(GameSprite.WOODEN_WAND, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 8: {
                        _sprites.put(GameSprite.CRYSTAL_WAND, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 9: {
                        _sprites.put(GameSprite.CELESTIAL_WAND, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 10: {
                        _sprites.put(GameSprite.LEATHER_ARMOR, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 11: {
                        _sprites.put(GameSprite.IRON_ARMOR, itemsSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        }

        //Player sprites
        BufferedImage spritesheet = null;

        try {
            spritesheet = ImageIO.read(new File("src\\main\\resources\\images\\character.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        rows = spritesheet.getHeight() / tileSize;
        cols = spritesheet.getWidth() / tileSize;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int tileIndex = y * cols + x;
                switch(tileIndex)
                {
                    case 0: {
                        _sprites.put(GameSprite.PLAYER_FACING_DOWN, spritesheet.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 1: {
                        _sprites.put(GameSprite.PLAYER_FACING_LEFT, spritesheet.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 2: {
                        _sprites.put(GameSprite.PLAYER_FACING_RIGHT, spritesheet.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                        break;
                    }
                    case 3: {
                        _sprites.put(GameSprite.PLAYER_FACING_UP, spritesheet.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize));
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        }

        _sounds = new HashMap<>();

        _sounds.put(GameSound.STEP_BUMP, new File("src\\main\\resources\\sound\\step_bump.wav"));
    }

    public static File getSound(GameSound sound)
    {
        return _sounds.getOrDefault(sound, null);
    }

    //return the name of the item given his enum value
    public static GameItem getItem(GameItemType type)
    {
        return switch (type) {
            case DOOR_KEY -> new GameItem(type, 0, 0, "door key");
            case IRON_SWORD -> new GameItem(type, 15, 100, "iron sword");
            case HEALTH_POTION -> new GameItem(type, 1, 50, "health potion");
            case LUCK_AMULET -> new GameItem(type, 2, 100, "luck amulet");
            case STRENGTH_AMULET -> new GameItem(type, 2, 100, "strength amulet");
            case WOODEN_SWORD -> new GameItem(type, 10, 100, "wood sword");
            case INFERNAL_SWORD -> new GameItem(type, 20, 100, "infernal sword");
            case WOODEN_WAND -> new GameItem(type, 10, 100, "wooden wand");
            case CRYSTAL_WAND -> new GameItem(type, 10, 100, "crystal wand");
            case CELESTIAL_WAND -> new GameItem(type, 10, 100, "celestial wand");
            case LEATHER_ARMOR -> new GameItem(type, 10, 100, "leather armor");
            case IRON_ARMOR -> new GameItem(type, 20, 100, "iron armor");
            default -> new GameItem();
        };
    }
    //gives the type of the item from the name and handles characters and game dialogues
    public static GameObject getGameObject(String objectId, GamePoint coordinates)
    {
        switch (objectId)
        {
            case "key":
                return new GameRoomItem(GameItemType.DOOR_KEY, coordinates.x, coordinates.y);

            case "health_potion":
                return new GameRoomItem(GameItemType.HEALTH_POTION, coordinates.x, coordinates.y);

            case "luck_amulet":
                return new GameRoomItem(GameItemType.LUCK_AMULET, coordinates.x, coordinates.y);

            case "strength_amulet":
                return new GameRoomItem(GameItemType.STRENGTH_AMULET, coordinates.x, coordinates.y);

            case "wooden_sword":
                return new GameRoomItem(GameItemType.WOODEN_SWORD, coordinates.x, coordinates.y);

            case "iron_sword":
                return new GameRoomItem(GameItemType.IRON_SWORD, coordinates.x, coordinates.y);

            case "infernal_sword":
                return new GameRoomItem(GameItemType.INFERNAL_SWORD, coordinates.x, coordinates.y);

            case "wooden_wand":
                return new GameRoomItem(GameItemType.WOODEN_WAND, coordinates.x, coordinates.y);

            case "crystal_wand":
                return new GameRoomItem(GameItemType.CRYSTAL_WAND, coordinates.x, coordinates.y);

            case "celestial_wand":
                return new GameRoomItem(GameItemType.CELESTIAL_WAND, coordinates.x, coordinates.y);

            case "leather_armor":
                return new GameRoomItem(GameItemType.LEATHER_ARMOR, coordinates.x, coordinates.y);

            case "iron_armor":
                return new GameRoomItem(GameItemType.IRON_ARMOR, coordinates.x, coordinates.y);

            case "locked_door": {
                return new GameDoor(coordinates);
            }
            case "initial_narrator":
            {
                return new GameNarratorDialog("Once, Castle Serenity stood as a bastion of peace in Aldoria. But when the malevolent sorcerer Malphas seized control, darkness blanketed the land. Now, a lone warrior named Alistair rises, vowing to free the castle and restore the kingdom's rightful ruler. Armed with courage and determination, he faces the sorcerer's dark forces, ready to reclaim Serenity and bring light back to Aldoria.");
            }
            case "dialog_room0":
            {
                return new GameDialog("Room 0 entered", "The king is located on the north... Pick the sword to help yourself.");
            }
            case "dialog_room1":
            {
                return new GameDialog("Room 1 entered", "It looks like the doors disappear... I can't go back.");
            }
            case "dialog_room2":
            {
                return new GameDialog("Room 2 entered", "It looks like the doors disappear... I can't go back.");
            }
            case "dialog_room3":
            {
                return new GameDialog("Room 3 entered", "There are multiple paths but only one will get me closer to the king.");
            }
            case "dialog_room4":
            {
                return new GameDialog("Room 4 entered", "There are multiple paths but only one will get me closer to the king.");
            }
            case "dialog_room5":
            {
                return new GameDialog("Room 5 entered", "I need to clear my way to go to the next room.");
            }
            case "dialog_room6":
            {
                return new GameDialog("Room 6 entered", "I need to clear my way to go to the next room.");
            }
            case "dialog_room7":
            {
                return new GameDialog("Room 7 entered", "Looks like this is the last room before the boss. I need to get ready.");
            }

            case "gift_character":
            {
                return GameCharacter.getGiftCharacter(new GameItem(GameItemType.HEALTH_POTION,1, 50, "health potion"), GameSprite.GIFT_CHARACTER, coordinates);
            }
            case "dialog_maze":
            {
                return new GameDialog("MAZE_ENTERED", "I can't see anything, Better hurry!");
            }
            case "dialog_enter" :
            {
                return new GameDialog("Room entered", "The king is located on the north... I need to find a way...");
            }
            case "dialog_enter2" :
            {
                return new GameDialog("Room entered 2", "A civilian! maybe he will give me something useful.");
            }
            case "dialog_enter3" :
            {
                return new GameDialog("Room entered 2", "Finally! now I need to beat this guy");
            }
            case "trader":
            {
                return GameCharacter.getTradeCharacter(GameSprite.TRADER, coordinates);
            }
            case "gift_key":
            {
                return GameCharacter.getGiftCharacter(new GameItem(GameItemType.DOOR_KEY,0, 0, "door key"), GameSprite.GIFT_CHARACTER, coordinates);
            }
            case "save_us":
            {
                return  GameCharacter.getTextCharacter("I have been waiting for you... I will tell you some tricks, maybe you will stay alive. Press \"I\" to open the inventory and \"T\" to toss an item. You can also use \"Q\" to inspect and \"E\" to interact with objects ", GameSprite.TEXT_ONLY, coordinates);
            }
            case "enemy":
            {
                return GameCharacter.getCombatCharacter(GameSprite.ENEMY, coordinates, GameCombatType.NORMAL, 50, 10, 20);
            }
            case "mini_boss":
            {
                return new GameDialog("Mini Boss entered", "This guy looks tuff! I need to get ready.");
            }
            case "mini_boss_character" :
            {
                return GameCharacter.getCombatCharacter(GameSprite.MINI_BOSS ,coordinates, GameCombatType.BOSS, 200, 10, 20);
            }
            case "mini_boss_character1" :
            {
                return GameCharacter.getCombatCharacter(GameSprite.MINI_BOSS1 ,coordinates, GameCombatType.BOSS, 250, 10, 20);
            }
            case "mini_boss_character2" :
            {
                return GameCharacter.getCombatCharacter(GameSprite.MINI_BOSS2 ,coordinates, GameCombatType.BOSS, 250, 10, 20);
            }
            case "dialog_room8":
            {
                return new GameDialog("Room 0 entered", "Opss... This room is completely empty, try another one.");
            }
            case "dialog_room9":
            {
                return new GameDialog("Room 1 entered", "Take your choice. Left or Right. Be careful, a door will take you back to the first room.");
            }
            case "dialog_room_boss":
            {
                return new GameDialog("Boss", "I can feel the presence of the evil mage who took control of the castle...");
            }
            case "boss_character":
            {
                return GameCharacter.getCombatCharacter(GameSprite.BOSS, coordinates, GameCombatType.FINAL_BOSS, 300, 10, 20);
            }
            default: {
                return new GameRoomItem(GameItemType.NONE, -1, -1) {
                };
            }
        }
    }

    public static Image getItemSprite(GameItemType type)
    {
        switch (type)
        {
            case DOOR_KEY:
            {
                return _sprites.get(GameSprite.DOOR_KEY);
            }
            case HEALTH_POTION:
            {
                return _sprites.get(GameSprite.HEALTH_POTION);
            }
            case LUCK_AMULET:
            {
                return _sprites.get(GameSprite.LUCK_AMULET);
            }
            case STRENGTH_AMULET:
            {
                return _sprites.get(GameSprite.STRENGTH_AMULET);
            }
            case WOODEN_SWORD:
            {
                return _sprites.get(GameSprite.WOODEN_SWORD);
            }
            case IRON_SWORD:
            {
                return _sprites.get(GameSprite.IRON_SWORD);
            }
            case INFERNAL_SWORD:
            {
                return _sprites.get(GameSprite.INFERNAL_SWORD);
            }
            case WOODEN_WAND:
            {
                return _sprites.get(GameSprite.WOODEN_WAND);
            }
            case CRYSTAL_WAND:
            {
                return _sprites.get(GameSprite.CRYSTAL_WAND);
            }
            case CELESTIAL_WAND:
            {
                return _sprites.get(GameSprite.CELESTIAL_WAND);
            }
            case LEATHER_ARMOR:
            {
                return _sprites.get(GameSprite.LEATHER_ARMOR);
            }
            case IRON_ARMOR:
            {
                return _sprites.get(GameSprite.IRON_ARMOR);
            }
            default:
            {
                return _tiles.get(GameTile.FADING_BRICK);
            }
        }
    }

    public static Font getDialogFont()
    {
        return _font.deriveFont(8f);
    }
    public static Font getNarratorDialogFont()
    {
        return _font.deriveFont(10f);
    }
    public static Font getInventoryFont()
    {
        return _font.deriveFont(8f);
    }

    public static Image getTile(GameTile tile)
    {
        return _tiles.get(tile);
    }
    public static Image getSprite(GameSprite sprite)
    {
        return _sprites.get(sprite);
    }

    public static String getTitle()
    {
        return "Game";
    }

    public static int getWidth()
    {
        return 480;
    }

    public static int getHeight()
    {
        return 270;
    }

    public static int getMaxWidth()
    {
        return 480 * 30;
    }

    public static int getMaxHeight()
    {
        return 270 * 30;
    }

    public static int getTileSize()
    {
        return 32;
    }

    public static float getAspectRatio()
    {
        return GameConstants.getWidth() / (float)GameConstants.getHeight();
    }

    public static Font getPauseMenuFont() {
        return _font.deriveFont(11f);
    }

    //save file online url
    public static String getSaveFileUrl()
    {
        return "http://127.0.0.1:8000";
    }

    //local save file
    public static String getSaveFileName()
    {
        return "src\\main\\resources\\save\\save_file.json";
    }
}