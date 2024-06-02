package main.java.com.mealforks.thelastknight.game;

public class GameDataLoader {
    private GameDataLoader _instance;

    private GameDataLoader()
    {}

    public GameDataLoader getInstance() {
        return _instance;
    }

    public GameData loadFromJson(String fileName)
    {
        // Qui dovete solo leggere il contenuto del save file e passare il contenuto come string a loadfrom string

        return this.loadFromString(jsonString);
    }

    public GameDataSave loadFromString(String jsonString)
    {

        // Effettuare il parsing del jsonString all oggetto GameDataSave

        // Dal json dovete leggere questo file intermedio che permette il salvataggio
        GameDataSave save = new GameDataSave();

        // Qui faro io per trasformare da GameDataSave a GameData

        return new GameDataSave();
    }

    public GameData loadFromCloud(string urlString)
    {
        // Questo lo faro io una volta che sara pronto il resto

        // Obtain string from server

        String serverString = "";

        return this.loadFromString(serverString);
    }
}
