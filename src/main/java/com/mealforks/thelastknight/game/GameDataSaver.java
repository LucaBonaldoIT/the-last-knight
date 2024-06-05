package main.java.com.mealforks.thelastknight.game;

import javax.json.Json;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * This class handles saving game data to local storage or cloud service.
 */
public class GameDataSaver {
    private static GameDataSaver _instance;

    private  GameDataSaver()
    {}

    /**
     * Get the singleton instance of GameDataSaver.
     *
     * @return The singleton instance of GameDataSaver.
     */
    public static GameDataSaver getInstance() {
        if (_instance == null)
        {
            _instance = new GameDataSaver();
        }

        return _instance;
    }

    /**
     * Save game data to local storage.
     *
     * @param save The game data to save.
     */
    public void saveToLocal(GameDataSave save)
    {

        String saveString = this.getSaveString(save);

        try (FileWriter fileWriter = new FileWriter(GameConstants.getSaveFileName())) {
            fileWriter.write(saveString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getSaveString(GameDataSave save)
    {
        // Convert GameDataSave to JSON string
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("\"currentLevel\":").append(save.getCurrentLevel()).append(", \n");
        json.append("\"playerData\":");
        json.append("{\n\t");
        json.append("\"xp\":").append(save.getPlayerData().getXp()).append(", \n\t");
        json.append("\"level\":").append(save.getPlayerData().getXp()).append(", \n\t");
        json.append("\"inventory\":").append("{ \n\t");

        boolean isFirst = true;
        for (Map.Entry<GameItemType, Integer> entry : save.getPlayerData().getInventory().entrySet()) {

            if (!isFirst) {
                json.append(", \n\t");
            }
            json.append("\"").append(entry.getKey().toString()).append("\": ").append(entry.getValue());
            isFirst = false;
        }

        json.append("}").append(", \n\t");
        json.append("\"playerClass\": \"" + save.getPlayerData().getPlayerClass().toString() + "\",\n\t");
        json.append("\"attributes\":");
        json.append("{\n\t\t");
        GamePlayerAttributes currentAttributes = save.getPlayerData().getAttributes();
        json.append("\"strength\":").append(currentAttributes.getStrength()).append(",\n\t\t");
        json.append("\"endurance\":").append(currentAttributes.getEndurance()).append(",\n\t\t");
        json.append("\"luck\":").append(currentAttributes.getLuck()).append(",\n\t\t");
        json.append("\"intelligence\":").append(currentAttributes.getIntelligence()).append(",\n\t\t");
        json.append("\"agility\":").append(currentAttributes.getAgility()).append("\n\t");
        json.append("}").append(",\n\t");

        json.append("\"health\":").append(save.getPlayerData().getHealth()).append(",\n\t");
        json.append("\"stamina\":").append(save.getPlayerData().getStamina()).append(",\n\t");
        json.append("\"mana\":").append(save.getPlayerData().getMana()).append(",\n\t");
        json.append("\"maxWeight\":").append(save.getPlayerData().getMaxWeight()).append(",\n\t");
        json.append("\"inventoryWeight\":").append(save.getPlayerData().getInventoryWeight()).append(",\n\t");
        json.append("\"coins\":").append(save.getPlayerData().getCoins());
        json.append("\n\t} \n");
        json.append("}");
        return json.toString();
    }

    /**
     * Save game data to a cloud service.
     *
     * @param save The game data to save.
     */
    public void saveToCloud(GameDataSave save)
    {
        String saveString = this.getSaveString(save);
        saveString = saveString.replaceAll("\\s+", "");

        HttpClient client = HttpClient.newHttpClient();

        try {
            URL url = new URL(GameConstants.getSaveFileUrl()+"/set-save-file");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Create the JSON data string
            String jsonData = "{\"data\": \""+ saveString + "\"}"; // Replace with actual data

            // Write the JSON data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code to verify the request was successful
            int responseCode = connection.getResponseCode();

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {

        }
    }
}
