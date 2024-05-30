package main.java.com.mealforks.thelastknight.game;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameInventory implements GameObject {
    private boolean _active;
    private GameData _data;

    private int _marginLeft;
    private int _marginTop;
    private int _height;
    private int _width;
    private int _selectedIndex;

    private GameItemType _selectedItem;

    public GameInventory()
    {
        _active = false;
        _data = null;

        _selectedIndex = 0;
        _selectedItem = GameItemType.NONE;

        _marginLeft = GameConstants.getWidth() / 4;
        _width = GameConstants.getWidth() - _marginLeft * 2;
        _height = GameConstants.getHeight() * 3 / 5;
        _marginTop = GameConstants.getWidth() / 8;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public GamePoint getCoordinates() {
        return null;
    }

    @Override
    public int getIndex() {
        return 9999;
    }

    @Override
    public void render(Graphics g) {
        if (_data == null || !_data.getGameState().equals(GameState.INVENTORY))
        {
            _active = false;
        }

        if (_active && _data != null)
        {
            g.setColor(Color.WHITE);
            g.fillRect(_marginLeft - 2, _marginTop - 2, _width + 4, _height + 4);
            g.setColor(Color.BLACK);
            g.drawRect(_marginLeft, _marginTop, _width, _height);

            g.setFont(GameConstants.getInventoryFont());
            FontMetrics fm = g.getFontMetrics();
            int lineHeight = fm.getHeight();

            GamePlayerData playerData = _data.getPlayerData();

            if (playerData != null) {
                int maxWeight = playerData.getMaxWeight();
                int weight = playerData.getInvetoryWeight();

                HashMap<GameItemType, Integer> inventory = playerData.getInventory();
                if (inventory != null) {
                    // Define starting positions for the text
                    int startX = _marginLeft + 20;
                    int startY = _marginTop + 20;

                    // Draw the header for the columns
                    g.drawString("Item", startX, startY);
                    g.drawString("Quantity", _marginLeft + _width / 2 + 10, startY);

                    g.drawString("Weight: " + Integer.toString(weight) + "/" + Integer.toString(maxWeight), startX - 13, _marginTop + _height - 10);

                    // Start drawing the inventory items
                    int currentY = startY + lineHeight;
                    int index = 0; // Index to keep track of current item in the inventory

                    for (Map.Entry<GameItemType, Integer> entry : inventory.entrySet()) {
                        if (entry.getValue() == 0)
                        {
                            _selectedItem = GameItemType.NONE;
                            continue;
                        }

                        if (index == _selectedIndex) {
                            g.drawString("X", startX - 13, currentY);
                            _selectedItem = entry.getKey();
                        }
                        g.drawString(GameConstants.getItem(entry.getKey()).getName(), startX, currentY);
                        g.drawString(entry.getValue().toString(), _marginLeft + _width / 2 + 10, currentY);
                        currentY += lineHeight;
                        index++;
                    }
                }
            }
        }
    }

    @Override
    public GameData update(GameData d) {
        _data = d;

        if (d.getInput().equals(GameInput.INVENTORY))
        {
            if (d.getGameState().equals(GameState.RUNNING))
            {
                d.setGameState(GameState.INVENTORY);
                _active = true;
            }
            else if (d.getGameState().equals(GameState.INVENTORY))
            {
                d.setGameState(GameState.RUNNING);
                _active = false;
            }
        }
        else if (d.getGameState().equals(GameState.INVENTORY)) {
            // Handle item selection
            HashMap<GameItemType, Integer> inventory = _data.getPlayerData().getInventory();
            int inventorySize = inventory != null ? inventory.size() : 0;

            if (d.getInput().equals(GameInput.UP)) {
                _selectedIndex = (_selectedIndex - 1 + inventorySize) % inventorySize;
            } else if (d.getInput().equals(GameInput.DOWN)) {
                _selectedIndex = (_selectedIndex + 1) % inventorySize;
            } else if (d.getInput().equals(GameInput.ENTER) && !_selectedItem.equals(GameItemType.NONE))
            {
                d.getPlayerData().useItem(_selectedItem, _data);
                _active = false;
            } else if (d.getInput().equals(GameInput.TOSS) && !_selectedItem.equals(GameItemType.NONE))
            {
                d.getPlayerData().removeItem(_selectedItem);
                _active = false;
                d.addObjectToScene(new GameDialog("ITEM_TOSSED", "You have tossed a " + GameConstants.getItem(_selectedItem).getName() + "."));
            }
        }
        
        return d;
    }

    @Override
    public boolean toDelete() {
        return false;
    }
}
