package main.java.com.mealforks.thelastknight.game;

import java.util.HashMap;

public class GameEvents {
    public static void LoadLevel1(GameData d)
    {
        LoadLevel1(d, null);
    }

    public static void LoadLevel1(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level1.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel();
    }

    public static void LoadLevel2(GameData d)
    {
        LoadLevel2(d, null);
    }

    public static void LoadLevel2(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level2.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel();
    }

    public static void LoadLevel3(GameData d)
    {
        LoadLevel3(d, null);
    }

    public static void LoadLevel3(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level3.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel();
    }

    public static void LoadLevelBoss(GameData d)
    {
        LoadLevelBoss(d, null);
    }

    public static void LoadLevelBoss(GameData d, GameDataSave save)
    {
        GameLevel level = GameLevelLoader.getInstance().loadLevel("src\\main\\resources\\levels\\level_boss.json");

        if (save != null)
        {
            d.loadFromSave(save);
        }

        d.loadLevel(level);
        d.startLevel();
    }
}