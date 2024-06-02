package main.java.com.mealforks.thelastknight.game;

public class GameDataSaver {
    private static GameDataSaver _instance;

    private  GameDataSaver()
    {}

    public static GameDataSaver getInstance() {
        if (_instance == null)
        {
            _instance = new GameDataSaver();
        }

        return _instance;
    }

    public void saveToLocal(GameDataSave save)
    {
        // Questo lo faro io una volta che sara pronto il resto

        String saveString = this.getSaveString(save);

        // Scrivi in locale il json contente il save

    }

    private String getSaveString(GameDataSave save)
    {
        // Questo dovete farlo voi
        // Da gamedatasave dovete ottenere un json

        return new String();
    }

    public void saveToCloud(GameDataSave save)
    {
        // Questo lo faro io una volta che sara pronto il resto

        String saveString = this.getSaveString(save);

        // Scrivi a server la stringa di save
    }
}
