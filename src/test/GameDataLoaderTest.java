package Test;

import main.java.com.mealforks.thelastknight.game.GameDataLoader;
import main.java.com.mealforks.thelastknight.game.GameDataSave;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameDataLoaderTest {

    private static GameDataLoader dataLoader;

    @BeforeAll
    public static void setUp() {
        dataLoader = GameDataLoader.getInstance();
    }

    @Test
    public void testLoadFromString() {
        String jsonString = "{\"currentLevel\":1,\"playerData\":{\"xp\":100,\"level\":5,\"inventory\":{\"HEALTH_POTION\":3},\"attributes\":{\"strength\":10,\"endurance\":8,\"luck\":5,\"intelligence\":3,\"agility\":6},\"health\":90,\"stamina\":80,\"mana\":50,\"playerClass\":\"KNIGHT\",\"maxWeight\":100,\"inventoryWeight\":50,\"coins\":150}}";
        GameDataSave save = dataLoader.loadFromString(jsonString);
        assertNotNull(save);
    }

    @Test
    public void testLoadFromNonExistentFile() {
        String fileName = "nonexistent.json";
        GameDataSave save = dataLoader.loadFromJson(fileName);
        assertNull(save);
    }
}