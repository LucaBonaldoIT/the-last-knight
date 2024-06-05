package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

/**
 * Represents a dialog box for narrating game events in The Last Knight game.
 * Implements the GameObject interface.
 */
public class GameNarratorDialog implements GameObject {
    private static final long DELAY = 25;
    private String _id;
    private int _index;
    private String _text;

    private boolean _firstUpdate;

    private boolean _toDelete;

    private int _marginLeft;
    private int _marginTop;
    private int _height;
    private int _width;

    private int _visibleLines; // Number of lines visible in the dialog box
    private int _totalLines; // Total number of lines in the text
    private int _startLineIndex; // Index of the first line to be displayed

    private int _charsToShow;
    private long _lastUpdateTime;

    private boolean _isGameOverDialog;
    private boolean _isGameEndDialog;

    /**
     * Sets whether this dialog is a game over dialog.
     *
     * @param gameOver true if this is a game over dialog, false otherwise.
     */
    public void setGameOverDialog(boolean gameOver)
    {
        _isGameOverDialog = gameOver;
    }

    /**
     * Sets whether this dialog is a game end dialog.
     *
     * @param gameEnd true if this is a game end dialog, false otherwise.
     */
    public void setGameEndDialog(boolean gameEnd)
    {
        _isGameEndDialog = gameEnd;
    }

    /**
     * Checks if this is a game over dialog.
     *
     * @return true if this is a game over dialog, false otherwise.
     */
    public boolean isGameOverDialog()
    {
        return _isGameOverDialog;
    }

    /**
     * Checks if this is a game end dialog.
     *
     * @return true if this is a game end dialog, false otherwise.
     */
    public boolean isGameEndDialog()
    {
        return _isGameEndDialog;
    }

    /**
     * Constructs a new GameNarratorDialog with the specified text.
     *
     * @param text the text to display in the dialog.
     */
    public GameNarratorDialog(String text)
    {
        _text = text;

        _marginLeft = GameConstants.getWidth() / 8;
        _width = GameConstants.getWidth() - _marginLeft * 2;
        _height = GameConstants.getHeight() * 3 / 4;
        _marginTop = GameConstants.getWidth() / 16;
        _charsToShow = 0;
        _lastUpdateTime = 0;
        _startLineIndex = 0;
        _totalLines = 0;
        _visibleLines = 0;
        _toDelete = false;
        _firstUpdate = true;
    }

    /**
     * Creates a GameNarratorDialog for the end of the game.
     *
     * @return a GameNarratorDialog instance with the end game text.
     */
    public static GameNarratorDialog getGameEndDialog()
    {
        GameNarratorDialog d = new GameNarratorDialog("You won. The end.");
        d.setGameEndDialog(true);
        return d;
    }

    /**
     * Creates a GameNarratorDialog for the game over scenario.
     *
     * @return a GameNarratorDialog instance with the game over text.
     */
    public static GameNarratorDialog getGameOverDialog()
    {
        GameNarratorDialog d = new GameNarratorDialog("You lose. Try again.");
        d.setGameOverDialog(true);
        return d;
    }

    /**
     * Returns the ID of the game object.
     *
     * @return the ID of the game object.
     */
    @Override
    public String getId() {
        return "NARRATOR_DIALOG";
    }

    /**
     * Returns the coordinates of the game object.
     *
     * @return the coordinates of the game object.
     */
    @Override
    public GamePoint getCoordinates() {
        return GamePoint.NONE;
    }

    /**
     * Updates the game data based on the state of the dialog.
     *
     * @param d the current game data.
     * @return the updated game data.
     */
    @Override
    public GameData update(GameData d)
    {
        if (_firstUpdate && !_isGameEndDialog && _isGameOverDialog)
        {
            d.setGameState(GameState.DIALOG);
        }

        if (System.currentTimeMillis() - _lastUpdateTime > DELAY) {
            _charsToShow = Math.min(_charsToShow + 1, _text.length());
            _lastUpdateTime = System.currentTimeMillis();
        }

        if (d.getGameState().equals(GameState.DIALOG) || _isGameEndDialog || _isGameOverDialog)
        {
            if (_isGameEndDialog || _isGameOverDialog)
            {
                System.exit(0);
            }

            if (d.getInput().equals(GameInput.ENTER) && _charsToShow == _text.length())
            {
                _toDelete = true;
                d.setGameState(GameState.RUNNING);
            }
        }

        return d;
    }

    /**
     * Checks if this game object should be deleted.
     *
     * @return true if the object should be deleted, false otherwise.
     */
    @Override
    public boolean toDelete() {
        return _toDelete;
    }

    /**
     * Returns the index of the game object.
     *
     * @return the index of the game object.
     */
    @Override
    public int getIndex()
    {
        return 999;
    }

    /**
     * Renders the dialog on the screen.
     *
     * @param g the Graphics object used for drawing.
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameConstants.getWidth(), GameConstants.getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(_marginLeft - 2, _marginTop - 2, _width + 4, _height + 4);
        g.setColor(Color.BLACK);
        g.drawRect(_marginLeft, _marginTop, _width, _height);

        g.setFont(GameConstants.getNarratorDialogFont());
        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();

        if (_totalLines == 0)
        {
            _totalLines = this.calculateTotalLines(fm);
            _visibleLines = _height / lineHeight - 1;
        }

        int padding = 5; // Padding between text and dialog box borders

        String visibleText = _text.substring(0, _charsToShow); // Get the visible part of the text
        String[] words = visibleText.split(" ");
        StringBuilder wrappedText = new StringBuilder(words[0]);
        int textWidth;
        // Reset startLineIndex if necessary
        if (_totalLines > _visibleLines && _startLineIndex + _visibleLines > _totalLines) {
            _startLineIndex = _totalLines - _visibleLines;
        }

        int lineCount = 0;
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            textWidth = fm.stringWidth(wrappedText.toString() + " " + word);
            if (textWidth < _width - 2 * padding) { // Subtract padding from width
                wrappedText.append(" ").append(word);
            } else {
                int textX = _marginLeft + padding;
                int textY = _marginTop + lineHeight + padding + ((lineCount - _startLineIndex) * lineHeight);
                // Check if the line is within the visible range
                if (lineCount >= _startLineIndex && lineCount < _startLineIndex + _visibleLines) {
                    g.drawString(wrappedText.toString(), textX, textY);
                }
                lineCount++;
                wrappedText = new StringBuilder(word);
            }
        }
        // Render the last line
        int textX = _marginLeft + padding;
        int textY = _marginTop + lineHeight + padding + ((lineCount - _startLineIndex) * lineHeight);
        if (lineCount >= _startLineIndex && lineCount < _startLineIndex + _visibleLines) {
            g.drawString(wrappedText.toString(), textX, textY);
        }

        // Calculate total lines again as the text might have wrapped differently
        _totalLines = lineCount + 1;

        // Adjust startLineIndex for scrolling
        if (_totalLines > _visibleLines && lineCount == _totalLines - 1) {
            _startLineIndex++;
        }
    }

    /**
     * Calculates the total number of lines required to display the text.
     *
     * @param fm the FontMetrics object used for measuring text dimensions.
     * @return the total number of lines required to display the text.
     */
    private int calculateTotalLines(FontMetrics fm) {
        int lineHeight = fm.getHeight();
        int padding = 5; // Padding between text and dialog box borders

        String[] words = _text.split(" ");
        StringBuilder wrappedText = new StringBuilder(words[0]);
        int textWidth;
        int lineCount = 0;
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            textWidth = fm.stringWidth(wrappedText.toString() + " " + word);
            if (textWidth < _width - 2 * padding) { // Subtract padding from width
                wrappedText.append(" ").append(word);
            } else {
                lineCount++;
                wrappedText = new StringBuilder(word);
            }
        }
        return lineCount + 1;
    }
}
