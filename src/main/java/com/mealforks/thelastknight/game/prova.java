package main.java.com.mealforks.thelastknight.game;

public class prova {
    public static void main(String[] args)
    {
        GameLevelLoader game = new GameLevelLoader();
        GameLevel x = game.loadLevel("src\\main\\resources\\levels\\Level1.json");
    }
}
