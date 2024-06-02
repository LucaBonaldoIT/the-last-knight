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
    public static Map<GameTile, Image> _tiles;
    public static Map<GameSprite, Image> _sprites;
    public static Map<GameSound, File> _sounds;

    static
    {
        try {
            //create the font to use. Specify the size!
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


    public static GameItem getItem(GameItemType type)
    {
        return switch (type) {
            case DOOR_KEY -> new GameItem(type, 0, 0, "door key");
            case IRON_SWORD -> new GameItem(type, 10, 100, "iron sword");
            case HEALTH_POTION -> new GameItem(type, 1, 50, "health potion");
            default -> new GameItem();
        };
    }

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
            case "dialog_maze":
            {
                return new GameDialog("MAZE_ENTERED", "OMG I DON'T SEE A FUCK!");
            }
            case "pedophile_pedo":
            {
                return GameCharacter.getTextCharacter("I like children", GameSprite.PLAYER_FACING_UP, coordinates);
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

    public static String getSaveFileName()
    {
        return "save_file.json";
    }
}