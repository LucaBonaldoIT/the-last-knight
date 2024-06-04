package main.java.com.mealforks.thelastknight.game;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class GameDataLoader {
    private static GameDataLoader _instance;

    private GameDataLoader()
    {}

    public static GameDataLoader getInstance() {
        if (_instance == null)
        {
            _instance = new GameDataLoader();
        }

        return _instance;
    }

    public GameDataSave loadFromJson(String fileName)
    {
        // Qui dovete solo leggere il contenuto del save file e passare il contenuto come string a loadfrom string

        if (!Files.exists(Paths.get(fileName)))
        {
            return null;
        }

        String jsonString = "";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.loadFromString(jsonString);
    }

    public GameDataSave loadFromString(String jsonString)
    {

        // Effettuare il parsing del jsonString all oggetto GameDataSave
        GamePlayerData playerData = new GamePlayerData();

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        int levelIndex = jsonObject.getInt("currentLevel");

        JsonObject playerDataJson = jsonObject.getJsonObject("playerData");

        int xp = playerDataJson.getInt("xp");
        playerData.setXp(xp);

        int level = playerDataJson.getInt("level");
        playerData.setLevel(level);

        JsonObject inventoryJson = playerDataJson.getJsonObject("inventory");
        if(inventoryJson != null)
        {
            for (Map.Entry<String, JsonValue> entry : inventoryJson.entrySet()) {

                String key = entry.getKey();
                JsonValue value = entry.getValue();
                GameItemType item = GameItemType.valueOf(key);
                int amount = inventoryJson.getInt(key);
                System.out.println(amount + "\n");

                for (int i = 0; i < amount; i++) {
                    playerData.addItem(item);
                }
            }
        }
        JsonObject attributesJson = playerDataJson.getJsonObject("attributes");
        GamePlayerAttributes playerAttributes = new GamePlayerAttributes();

        int strength = attributesJson.getInt("strength");
        playerAttributes.setStrength(strength);

        int endurance = attributesJson.getInt("endurance");
        playerAttributes.setEndurance(endurance);

        int luck = attributesJson.getInt("luck");
        playerAttributes.setLuck(luck);

        int intelligence = attributesJson.getInt("intelligence");
        playerAttributes.setIntelligence(intelligence);

        int agility = attributesJson.getInt("agility");
        playerAttributes.setAgility(agility);

        playerData.setAttributes(playerAttributes);

        int health = playerDataJson.getInt("health");
        playerData.setHealth(health);

        int stamina = playerDataJson.getInt("stamina");
        playerData.setStamina(stamina);

        int mana = playerDataJson.getInt("mana");
        playerData.setMana(mana);

        GamePlayerClass playerClass = GamePlayerClass.valueOf(playerDataJson.getString("playerClass"));
        playerData.setPlayerClass(playerClass);

        int maxWeight = playerDataJson.getInt("maxWeight");
        playerData.setMaxWeight(maxWeight);

        int inventoryWeight = playerDataJson.getInt("inventoryWeight");
        playerData.setInventoryWeight(inventoryWeight);

        int coins = playerDataJson.getInt("coins");
        playerData.setCoins(coins);

        GameDataSave save = new GameDataSave();
        save.setPlayerData(playerData);
        save.setCurrentLevel(levelIndex);

        return save;
    }

    public GameDataSave loadFromCloud(String urlString)
    {
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(urlString)) // Assuming urlString is the endpoint for retrieving data from the server
                    .GET()
                    .build();

            HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            if (getResponse.statusCode() == 200) {
                String serverString = getResponse.body();
                return this.loadFromString(serverString);
            } else {
                System.out.println("GET Response: Unexpected status code: " + getResponse.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
