package main.java.com.mealforks.thelastknight.game;

import java.util.HashMap;

/**
 * This class provides methods for loading different levels in the game.
 */
public class GameEvents {
    /**
     * Load level 1.
     *
     * @param d The game data.
     */
    public static void LoadLevel1(GameData d)
    {
        LoadLevel1(d, null);
    }

    /**
     * Load level 1 with a specific save.
     *
     * @param d    The game data.
     * @param save The game data save.
     */
    public static void LoadLevel1(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level1.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel(save != null);
    }

    /**
     * Load level 2.
     *
     * @param d The game data.
     */
    public static void LoadLevel2(GameData d)
    {
        LoadLevel2(d, null);
    }

    /**
     * Load level 2 with a specific save.
     *
     * @param d    The game data.
     * @param save The game data save.
     */
    public static void LoadLevel2(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level2.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel(save != null);
    }

    /**
     * Load level 3.
     *
     * @param d The game data.
     */
    public static void LoadLevel3(GameData d)
    {
        LoadLevel3(d, null);
    }

    /**
     * Load level 3 with a specific save.
     *
     * @param d    The game data.
     * @param save The game data save.
     */

    public static void LoadLevel3(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level3.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel(save != null);
    }

    /**
     * Load the boss level.
     *
     * @param d The game data.
     */
    public static void LoadLevelBoss(GameData d)
    {
        LoadLevelBoss(d, null);
    }

    /**
     * Load the boss level with a specific save.
     *
     * @param d    The game data.
     * @param save The game data save.
     */
    public static void LoadLevelBoss(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level_boss.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel(save != null);
    }
}