package main.java.com.mealforks.thelastknight.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameCombatMenu implements GameObject {
    private List<String> options;
    private int selectedIndex;

    private int _marginLeft = 10;
    private int _marginTop = 10;
    private int _width = 200;
    private int _height = 100;

    private String _message;
    private long _messageTime;
    private boolean _displayMessage = false;

    public boolean getDisplayMessage()
    {
        return _displayMessage;
    }

    public void displayMessage(String message)
    {
        _messageTime = System.currentTimeMillis();
        _message = message;
        _displayMessage = true;
    }

    public GameCombatMenu() {
        options = new ArrayList<>();
        options.add("Melee Attack");
        options.add("Cast Spell");
        options.add("Use Item");
        options.add("Flee");
        _marginLeft = GameConstants.getWidth() / 4;
        _width = GameConstants.getWidth() - _marginLeft * 2;
        _height = GameConstants.getHeight() / 4;
        _marginTop = GameConstants.getWidth() / 3;
        selectedIndex = 0;
    }

    public void render(Graphics g) {
        int tileSize = GameConstants.getTileSize();

        g.setFont(GameConstants.getDialogFont());

        int x = _marginLeft + 20;
        int y = _marginTop + g.getFontMetrics(g.getFont()).getHeight() + 5;

        // Draw menu background
        g.setColor(Color.WHITE);
        g.fillRect(_marginLeft - 2, _marginTop - 2, _width + 4, _height + 4);
        g.setColor(Color.BLACK);
        g.drawRect(_marginLeft, _marginTop, _width, _height);

        if (_displayMessage)
        {
            if (System.currentTimeMillis() - _messageTime > 2000)
            {
                _displayMessage = false;
            }

            g.drawString(_message, _marginLeft + 7, _marginTop + 15);
        }
        else
        {
            for (int i = 0; i < options.size(); i++) {
                int row = i / 2; // Calculate row (0 or 1)
                int col = i % 2; // Calculate column (0 or 1)
                int optionX = x + col * (_width / 2); // Adjust x position based on column
                int optionY = y + row * 25; // Adjust y position based on row

                String option = (i == selectedIndex) ? "> " + options.get(i) : options.get(i);
                g.drawString(option, optionX, optionY + 8);
            }
        }

    }

    public void nextOption() {
        selectedIndex = (selectedIndex + 1) % options.size();
    }

    public void previousOption() {
        selectedIndex = (selectedIndex - 1 + options.size()) % options.size();
    }

    public String getSelectedOption() {
        return options.get(selectedIndex);
    }

    @Override
    public String getId() {
        return "COMBAT_MENU";
    }

    @Override
    public GamePoint getCoordinates() {
        return new GamePoint(0, 0);
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public GameData update(GameData d) {
        return d;
    }

    @Override
    public boolean toDelete() {
        return false;
    }
}
