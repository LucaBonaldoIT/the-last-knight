package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GameDoor implements GameObject {
    private GamePoint _coordinates;

    private boolean _toDelete;

    public GameDoor(GamePoint coordinates)
    {
        _coordinates = coordinates;
    }

    public GameDoor()
    {
        _coordinates = new GamePoint(0, 0);
    }

    @Override
    public GamePoint getCoordinates()
    {
        return _coordinates;
    }

    public void setCoordinates(GamePoint coordinates)
    {
        _coordinates = coordinates;
    }

    @Override
    public String getId() {
        return "GAME_DOOR";
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public GameData update(GameData d) {

        if (!d.getGameState().equals(GameState.RUNNING))
        {
            return d;
        }

        int x = d.getPlayer().getX();
        int y = d.getPlayer().getY();

        if (!d.isPlayerLookingAt(this))
        {
            return d;
        }

        switch (d.getInput())
        {
            case INSPECT:
            {
                d.addObjectToScene(new GameDialog("DOOR_DIALOG", "You are looking at a closed door."));
                d.setGameState(GameState.DIALOG);
                break;
            }
            case ENTER:
            {
                if (d.getPlayerData().getInventory().getOrDefault(GameItemType.DOOR_KEY, 0) > 0)
                {
                    d.addObjectToScene(new GameDialog("DOOR_DIALOG", "You use one of your keys to open this door."));
                    d.setGameState(GameState.DIALOG);
                    d.getPlayerData().removeItem(GameItemType.DOOR_KEY);
                    _toDelete = true;
                }
                else
                {
                    d.addObjectToScene(new GameDialog("DOOR_DIALOG", "You don't have any key."));
                    d.setGameState(GameState.DIALOG);
                }
                break;
            }
            default:
            {
                break;
            }
        }

        return d;
    }

    @Override
    public boolean toDelete() {
        return _toDelete;
    }
}
