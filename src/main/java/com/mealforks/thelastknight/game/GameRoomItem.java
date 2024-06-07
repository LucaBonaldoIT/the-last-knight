package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

/**
 * Represents an item within a game room.
 */
public class GameRoomItem implements GameObject {

    /** Indicates whether the item should be deleted. */
    private boolean _toDelete;

    /** The type of the item. */
    private GameItemType _type;

    /** The x-coordinate of the item. */
    private int _x;

    /** The y-coordinate of the item. */
    private int _y;

    /**
     * Constructs a new GameRoomItem with the specified type and coordinates.
     *
     * @param type The type of the item.
     * @param x    The x-coordinate of the item.
     * @param y    The y-coordinate of the item.
     */
    public GameRoomItem(GameItemType type, int x, int y)
    {
        _type = type;
        _toDelete = false;
        _x = x;
        _y = y;
    }

    /**
     * Retrieves the ID of the object.
     *
     * @return The ID of the object.
     */
    @Override
    public String getId() {
        return "";
    }

    /**
     * Retrieves the coordinates of the object.
     *
     * @return The coordinates of the object.
     */
    @Override
    public GamePoint getCoordinates() {
        return new GamePoint(_x, _y);
    }

    /**
     * Retrieves the index of the object.
     *
     * @return The index of the object.
     */
    @Override
    public int getIndex() {
        return 0;
    }

    /**
     * Renders the item on the graphics context.
     *
     * @param g The graphics context.
     */
    @Override
    public void render(Graphics g) {
        int tileSize = GameConstants.getTileSize();
        g.drawImage(GameConstants.getItemSprite(_type), _x * tileSize, _y * tileSize, null);
    }

    /**
     * Updates the item's state.
     *
     * @param d The game data.
     * @return The updated game data.
     */
    @Override
    public GameData update(GameData d) {

        if (!d.getGameState().equals(GameState.RUNNING))
        {
            return  d;
        }

        int x = d.getPlayer().getX();
        int y = d.getPlayer().getY();

        if (!d.isPlayerLookingAt(this))
        {
            return d;
        }

        if (d.getInput().equals(GameInput.INSPECT))
        {
            GameItem item = GameConstants.getItem(_type);
            GamePlayerData playerData = d.getPlayerData();

            if (playerData.getInventory().entrySet().size() >= GamePlayerData.MAX_ITEMS)
            {
                d.addObjectToScene(new GameDialog("ITEM_FOUND", "You cannot carry this item, you have reached the maximum number of items carried at once."));
                d.setGameState(GameState.DIALOG);
            }
            else if (playerData.getMaxWeight() >= playerData.getInvetoryWeight() + item.getWeight())
            {
                playerData.addItem(_type);

                d.addObjectToScene(new GameDialog("ITEM_FOUND", "You have collected a " + GameConstants.getItem(_type).getName() + "."));
                d.setGameState(GameState.DIALOG);
                d.addSoundToBuffer(GameSound.ITEM_PICK);

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

    /**
     * Indicates whether the item should be deleted.
     *
     * @return True if the item should be deleted, false otherwise.
     */
    @Override
    public boolean toDelete() {
        return _toDelete;
    }
}
