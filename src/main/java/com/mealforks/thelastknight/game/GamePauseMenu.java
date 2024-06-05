/**
 * The GamePauseMenu class represents the pause menu in the game.
 * It allows the player to interact with various options such as resuming the game, saving progress,
 * displaying controls, and closing the game.
 */
package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GamePauseMenu implements GameObject {

    private boolean _active; // Indicates if the pause menu is active
    private int _selectedIndex; // Index of the currently selected menu option
    private String[] _menuOptions; // Array containing menu options
    private boolean _displayControls; // Indicates if controls should be displayed

    /**
     * Constructor for the GamePauseMenu class.
     * Initializes menu options and sets initial state variables.
     */
    public GamePauseMenu() {
        _active = false;
        _selectedIndex = 0;
        _menuOptions = new String[]{"Resume Game", "Save game", "Display Controls", "Close Game"};
        _displayControls = false;
    }

    @Override
    public String getId() {
        return "PAUSE_MENU";
    }

    @Override
    public GamePoint getCoordinates() {
        return GamePoint.NONE;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {
        if (!_active) {
            return;
        }

        // Background
        g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
        g.fillRect(0, 0, GameConstants.getWidth(), GameConstants.getHeight());

        // Check if controls should be displayed
        if (_displayControls) {
            displayControls(g);
            return;
        }

        // Menu box
        int menuWidth = 300;
        int menuHeight = 200;
        int marginLeft = (GameConstants.getWidth() - menuWidth) / 2;
        int marginTop = (GameConstants.getHeight() - menuHeight) / 2;

        g.setColor(Color.WHITE);
        g.fillRect(marginLeft, marginTop, menuWidth, menuHeight);
        g.setColor(Color.BLACK);
        g.drawRect(marginLeft, marginTop, menuWidth, menuHeight);

        // Menu options
        g.setFont(GameConstants.getPauseMenuFont());
        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();
        int startX = marginLeft + 20;
        int startY = marginTop + 40;

        for (int i = 0; i < _menuOptions.length; i++) {
            if (i == _selectedIndex) {
                g.drawString(">", startX - 13, startY + (i * lineHeight));
            }
            g.drawString(_menuOptions[i], startX, startY + (i * lineHeight));
        }
    }

    @Override
    public GameData update(GameData d) {

        if (d.getGameState().equals(GameState.RUNNING)) {
            if (d.getInput().equals(GameInput.ESC)) {
                _active = true;
                d.setGameState(GameState.PAUSE_MENU);
                return d;
            }
        }

        if (!_active || !d.getGameState().equals(GameState.PAUSE_MENU)) {
            _active = false;
            return d;
        }

        if (_displayControls) {
            if (d.getInput().equals(GameInput.ENTER)) {
                _displayControls = false;
            }
            return d;
        }

        if (d.getInput().equals(GameInput.ESC)) {
            _active = false;
            d.setGameState(GameState.RUNNING);
            return d;
        }

        if (d.getInput().equals(GameInput.UP)) {
            _selectedIndex = (_selectedIndex - 1 + _menuOptions.length) % _menuOptions.length;
        } else if (d.getInput().equals(GameInput.DOWN)) {
            _selectedIndex = (_selectedIndex + 1) % _menuOptions.length;
        } else if (d.getInput().equals(GameInput.ENTER)) {
            handleMenuSelection(d);
        }

        return d;
    }

    @Override
    public boolean toDelete() {
        return false;
    }

    /**
     * Handles the selection made in the pause menu.
     * Executes the appropriate action based on the selected menu option.
     *
     * @param d The current game data.
     */
    private void handleMenuSelection(GameData d) {
        switch (_selectedIndex) {
            case 0:
                resumeGame(d);
                break;
            case 1:
                saveGame(d);
                break;
            case 2:
                displayControls(d);
                break;
            case 3:
                closeGame(d);
                break;
        }
    }

    /**
     * Saves the game progress.
     * Updates the game state and displays a success message.
     *
     * @param d The current game data.
     */
    private void saveGame(GameData d) {
        GameDataSaver.getInstance().saveToLocal(d.getSave());
        GameDataSaver.getInstance().saveToCloud(d.getSave());
        _active = false;
        d.setGameState(GameState.RUNNING);
        d.addObjectToScene(new GameDialog("SAVE_SUCCESS", "You saved your progress."));
    }

    /**
     * Resumes the game.
     * Updates the game state.
     *
     * @param d The current game data.
     */
    private void resumeGame(GameData d) {
        _active = false;
        d.setGameState(GameState.RUNNING);
    }

    /**
     * Closes the game.
     * Exits the application.
     *
     * @param d The current game data.
     */
    private void closeGame(GameData d) {
        _active = false;
        System.exit(0);
    }

    /**
     * Displays the controls on the screen.
     * Sets the flag to indicate that controls are being displayed.
     *
     * @param d The current game data.
     */
    private void displayControls(GameData d) {
        _displayControls = true;
        // You can add additional logic here if needed
    }

    /**
     * Displays the controls on the screen.
     * Renders the controls graphics.
     *
     * @param g The graphics context.
     */
    private void displayControls(Graphics g) {
        // Display the controls on the screen
        int controlsWidth = 400;
        int controlsHeight = 300;
        int marginLeft = (GameConstants.getWidth() - controlsWidth) / 2;
        int marginTop = (GameConstants.getHeight() - controlsHeight) / 2;

        g.setColor(Color.WHITE);
        g.fillRect(marginLeft, marginTop, controlsWidth, controlsHeight);
        g.setColor(Color.BLACK);
        g.drawRect(marginLeft, marginTop, controlsWidth, controlsHeight);

        // Controls text
        g.setFont(GameConstants.getPauseMenuFont());
        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();
        int startX = marginLeft + 20;
        int startY = marginTop + 40;

        String[] controls = {
                "Game Controls:",
                "Move Up:    W",
                "Move Down:  S",
                "Move Left:  A",
                "Move Right: D",
                "Inspect:    Q",
                "Enter:      E",
                "Inventory:  I",
                "Toss Item:  T",
                "Yes:        Y",
                "No:         N",
                "Pause:    ESC",
                "",
                "Press 'ENTER' to return"

        };

        for (int i = 0; i < controls.length - 1; i++) {
            g.drawString(controls[i], startX + 120, startY + (i * lineHeight) + 20);
        }

        g.drawString(controls[controls.length - 1], startX + 40, startY + (13 * lineHeight) + 20);
    }
}
