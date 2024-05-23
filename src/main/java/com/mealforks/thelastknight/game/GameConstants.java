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
                    default:
                    {
                        break;
                    }
                }
            }
        }

    }

    public static Font getDialogFont()
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
}