package main.java.com.mealforks.thelastknight.game;

public class GameEvents {
    public static void TestDialog1(GameData d)
    {
        d.clearScene();
        //d.addObjectToScene(new GameDialog("TEST_DIALOG_1", "This is a test message. No more. No less. If you want to know more just ask the developer. If you want to know more just ask the developer. If you want to know more just ask the developer."));
        d.setGameState(GameState.RUNNING);

        GameTile[][] tiles = new GameTile[][] {
            {GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.EMPTY, GameTile.BRICK},
            {GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK, GameTile.BRICK}
        };

        GameCollision[][] collisions = new GameCollision[][] {
            {GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.EMPTY, GameCollision.BLOCK},
            {GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK, GameCollision.BLOCK},
        };

        GameArea area = new GameArea(tiles, collisions);

        d.addObjectToScene(area);
        d.setGameArea(area);
        d.addObjectToScene(new GamePlayer(1 , 1));
    }
}
