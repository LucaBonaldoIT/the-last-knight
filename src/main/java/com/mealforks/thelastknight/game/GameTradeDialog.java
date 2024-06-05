package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

/**
 * Represents a trade dialog in the game.
 */
public class GameTradeDialog implements GameObject {

    /**
     * The delay between displaying each character of the text.
     */
    private static final long DELAY = 1;

    /**
     * The unique identifier of the dialog.
     */
    private String _id;

    /**
     * The index of the dialog.
     */
    private int _index;

    /**
     * The text displayed in the dialog.
     */
    private String _text;

    /**
     * Indicates whether this is the first update of the dialog.
     */
    private boolean _firstUpdate;

    /**
     * Indicates whether the dialog should be deleted.
     */
    private boolean _toDelete;

    /**
     * The margin on the left side of the dialog.
     */
    private int _marginLeft;

    /**
     * The margin on the top side of the dialog.
     */
    private int _marginTop;

    /**
     * The height of the dialog.
     */
    private int _height;

    /**
     * The width of the dialog.
     */
    private int _width;

    /**
     * The number of visible lines in the dialog box.
     */
    private int _visibleLines;

    /**
     * The total number of lines in the text.
     */
    private int _totalLines;

    /**
     * The index of the first line to be displayed.
     */
    private int _startLineIndex;

    /**
     * The number of characters to be shown in the dialog.
     */
    private int _charsToShow;

    /**
     * The time of the last update.
     */
    private long _lastUpdateTime;

    /**
     * Constructs a new GameTradeDialog with the specified ID and text.
     *
     * @param id   The unique identifier of the dialog.
     * @param text The text to be displayed in the dialog.
     */
    public GameTradeDialog(String id, String text) {
        _id = id;
        _text = text;
        _marginLeft = GameConstants.getWidth() / 4;
        _width = GameConstants.getWidth() - _marginLeft * 2;
        _height = GameConstants.getHeight() / 4;
        _marginTop = GameConstants.getWidth() / 3;
        _charsToShow = 0;
        _lastUpdateTime = 0;
        _startLineIndex = 0;
        _totalLines = 0;
        _visibleLines = 0;
        _toDelete = false;
        _firstUpdate = true;
    }

    /**
     * Retrieves the text of the dialog.
     *
     * @return The text of the dialog.
     */
    public String getText() {
        return _text;
    }

    /**
     * Updates the dialog based on the game data.
     *
     * @param d The game data.
     * @return The updated game data.
     */
    @Override
    public GameData update(GameData d) {
        if (_firstUpdate) {
            d.setGameState(GameState.TRADE);
        }

        if (System.currentTimeMillis() - _lastUpdateTime > DELAY) {
            _charsToShow = Math.min(_charsToShow + 1, _text.length());
            _lastUpdateTime = System.currentTimeMillis();
        }

        if (d.getGameState().equals(GameState.TRADE)) {
            if ((d.getInput().equals(GameInput.YES) || d.getInput().equals(GameInput.NO)) && _charsToShow == _text.length()) {
                _toDelete = true;
                d.setGameState(GameState.RUNNING);
            }
        }

        return d;
    }

    /**
     * Checks whether the dialog should be deleted.
     *
     * @return True if the dialog should be deleted, false otherwise.
     */
    @Override
    public boolean toDelete() {
        return _toDelete;
    }

    /**
     * Retrieves the unique identifier of the dialog.
     *
     * @return The unique identifier of the dialog.
     */
    @Override
    public String getId() {
        return _id;
    }

    /**
     * Retrieves the coordinates of the dialog.
     *
     * @return The coordinates of the dialog.
     */
    @Override
    public GamePoint getCoordinates() {
        return GamePoint.NONE;
    }

    /**
     * Retrieves the index of the dialog.
     *
     * @return The index of the dialog.
     */
    @Override
    public int getIndex() {
        return 999;
    }

    /**
     * Renders the dialog on the screen.
     *
     * @param g The graphics object used for rendering.
     */
    @Override
    public void render(Graphics g) {
        // Set the color for drawing
        g.setColor(Color.WHITE);
        // Fill the background of the dialog box
        g.fillRect(_marginLeft - 2, _marginTop - 2, _width + 4, _height + 4);
        // Set the color for drawing the border
        g.setColor(Color.BLACK);
        // Draw the border of the dialog box
        g.drawRect(_marginLeft, _marginTop, _width, _height);

        // Set the font for drawing text
        g.setFont(GameConstants.getDialogFont());
        // Get font metrics for calculating text dimensions
        FontMetrics fm = g.getFontMetrics();
        // Get the height of each line of text
        int lineHeight = fm.getHeight();

        // Calculate total lines and visible lines if not already calculated
        if (_totalLines == 0) {
            _totalLines = this.calculateTotalLines(fm);
            _visibleLines = _height / lineHeight - 1;
        }

        // Set padding between text and dialog box borders
        int padding = 5;

        // Get the visible part of the text
        String visibleText = _text.substring(0, _charsToShow);
        // Split the visible text into words
        String[] words = visibleText.split(" ");
        // Initialize a StringBuilder to wrap the text
        StringBuilder wrappedText = new StringBuilder(words[0]);
        // Initialize variables for text rendering
        int textWidth;
        int lineCount = 0;

        // Loop through each word to wrap the text
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            // Calculate the width of the wrapped text including the next word
            textWidth = fm.stringWidth(wrappedText.toString() + " " + word);
            // Check if the wrapped text fits within the dialog box width
            if (textWidth < _width - 2 * padding) {
                wrappedText.append(" ").append(word);
            } else {
                // Calculate the position to draw the wrapped text
                int textX = _marginLeft + padding;
                int textY = _marginTop + lineHeight + padding + ((lineCount - _startLineIndex) * lineHeight);
                // Check if the line is within the visible range
                if (lineCount >= _startLineIndex && lineCount < _startLineIndex + _visibleLines) {
                    // Draw the wrapped text
                    g.drawString(wrappedText.toString(), textX, textY);
                }
                // Increment line count and reset wrapped text for the next line
                lineCount++;
                wrappedText = new StringBuilder(word);
            }
        }
        // Render the last line of text
        int textX = _marginLeft + padding;
        int textY = _marginTop + lineHeight + padding + ((lineCount - _startLineIndex) * lineHeight);
        // Check if the last line is within the visible range
        if (lineCount >= _startLineIndex && lineCount < _startLineIndex + _visibleLines) {
            // Draw the last line of text
            g.drawString(wrappedText.toString(), textX, textY);
        }

        // Calculate total lines again as the text might have wrapped differently
        _totalLines = lineCount + 1;

        // Adjust startLineIndex for scrolling if necessary
        if (_totalLines > _visibleLines && lineCount == _totalLines - 1) {
            _startLineIndex++;
        }
    }

    /**
     * Calculates the total number of lines in the text.
     *
     * @param fm The font metrics used for text measurement.
     * @return The total number of lines in the text.
     */
    private int calculateTotalLines(FontMetrics fm) {
        // Initialization
        int lineHeight = fm.getHeight();
        int padding = 5;
        String[] words = _text.split(" ");
        StringBuilder wrappedText = new StringBuilder(words[0]);
        int textWidth;
        int lineCount = 0;

        // Loop through each word to calculate the total number of lines
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            // Calculate the width of the wrapped text including the next word
            textWidth = fm.stringWidth(wrappedText.toString() + " " + word);
            // Check if the wrapped text fits within the dialog box width
            if (textWidth < _width - 2 * padding) {
                wrappedText.append(" ").append(word);
            } else {
                // Increment line count and reset wrapped text for the next line
                lineCount++;
                wrappedText = new StringBuilder(word);
            }
        }
        // Increment line count for the last line of text
        return lineCount + 1;
    }
}