package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GameRoomItem implements GameObject {
    private boolean _toDelete;

    private GameItemType _type;
    private int _x;
    private int _y;

    public GameRoomItem(GameItemType type, int x, int y)
    {
        _type = type;
        _toDelete = false;
        _x = x;
        _y = y;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {
        int tileSize = GameConstants.getTileSize();
        g.drawImage(GameConstants.getItemSprite(_type), _x * tileSize, _y * tileSize, null);
    }

    @Override
    public GameData update(GameData d) {

        int x = d.getPlayer().getX();
        int y = d.getPlayer().getY();

        if (Math.abs(x - _x) + Math.abs(y - _y) > 1)
        {
            return d;
        }

        if (d.getInput().equals(GameInput.INSPECT))
        {
            GameItem item = GameConstants.getItem(_type);
            GamePlayerData playerData = d.getPlayerData();

            if (playerData.getMaxWeight() >= playerData.getInvetoryWeight() + item.getWeight())
            {
                playerData.addItem(_type);

                // Todo: add better text
                d.addObjectToScene(new GameDialog("ITEM_FOUND", "You have collected a item."));
                d.setGameState(GameState.DIALOG);

                _toDelete = true;
            }
            else
            {
                d.addObjectToScene(new GameDialog("ITEM_FOUND", "You cannot carry this item, you have reached the weight limit."));
                d.setGameState(GameState.DIALOG);
            }
        }

        return d;
    }

    @Override
    public boolean toDelete() {
        return _toDelete;
    }
}
