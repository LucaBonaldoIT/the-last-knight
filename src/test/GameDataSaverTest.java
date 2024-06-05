package Test;

import main.java.com.mealforks.thelastknight.game.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class GameDataSaverTest {

    @Test
    public void testSaveToLocal() {
        // Setup
        GameDataSave save = new GameDataSave();
        GamePlayerData playerData = new GamePlayerData();
        playerData.setXp(0);
        playerData.setLevel(0);
        playerData.addItem(GameItemType.DOOR_KEY);
        playerData.addItem(GameItemType.IRON_SWORD);
        playerData.setPlayerClass(GamePlayerClass.KNIGHT);
        GamePlayerAttributes attributes = new GamePlayerAttributes();
        attributes.setStrength(20);
        attributes.setEndurance(15);
        attributes.setLuck(10);
        attributes.setIntelligence(5);
        attributes.setAgility(12);
        playerData.setAttributes(attributes);
        playerData.setHealth(90);
        playerData.setStamina(80);
        playerData.setMana(70);
        playerData.setMaxWeight(50);
        playerData.setInventoryWeight(30);
        playerData.setCoins(1000);
        save.setPlayerData(playerData);
        save.setCurrentLevel(5);

        // Execute
        GameDataSaver.getInstance().saveToLocal(save);

        // Verify
        String saveFilePath = GameConstants.getSaveFileName();
        assertTrue(Files.exists(Paths.get(saveFilePath)), "Save file should exist");
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(saveFilePath)));
            String expectedJson = "{\n" +
                    "\"currentLevel\":5, \n" +
                    "\"playerData\":{\n\t" +
                    "\"xp\":0, \n\t" +
                    "\"level\":0, \n\t" +
                    "\"inventory\":{ \n\t" +
                    "\"DOOR_KEY\": 1, \n\t" +
                    "\"IRON_SWORD\": 1" +
                    "}, \n\t" +
                    "\"playerClass\": \"KNIGHT\",\n\t" +
                    "\"attributes\":{\n\t\t" +
                    "\"strength\":20,\n\t\t" +
                    "\"endurance\":15,\n\t\t" +
                    "\"luck\":10,\n\t\t" +
                    "\"intelligence\":5,\n\t\t" +
                    "\"agility\":12\n\t" +
                    "},\n\t" +
                    "\"health\":90,\n\t" +
                    "\"stamina\":80,\n\t" +
                    "\"mana\":70,\n\t" +
                    "\"maxWeight\":50,\n\t" +
                    "\"inventoryWeight\":30,\n\t" +
                    "\"coins\":1000\n\t" +
                    "} \n" +
                    "}";
            assertEquals(expectedJson, fileContent, "File content should match expected JSON structure");
        } catch (IOException e) {
            fail("Failed to read save file: " + e.getMessage());
        }

        // Clean up
        File saveFile = new File(saveFilePath);
        saveFile.delete();
    }
}