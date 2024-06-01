package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GameTradeDialog implements GameObject
{
    private static final long DELAY = 1;
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

    public GameTradeDialog(String id, String text)
    {
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

    public String getText()
    {
        return _text;
    }

    @Override
    public GameData update(GameData d)
    {
        if (_firstUpdate)
        {
            d.setGameState(GameState.TRADE);
        }

        if (System.currentTimeMillis() - _lastUpdateTime > DELAY) {
            _charsToShow = Math.min(_charsToShow + 1, _text.length());
            _lastUpdateTime = System.currentTimeMillis();
        }

        if (d.getGameState().equals(GameState.TRADE))
        {
            if ((d.getInput().equals(GameInput.YES) || d.getInput().equals(GameInput.NO)) && _charsToShow == _text.length())
            {
                _toDelete = true;
                d.setGameState(GameState.RUNNING);
            }
        }

        return d;
    }

    @Override
    public boolean toDelete() {
        return _toDelete;
    }

    @Override
    public String getId()
    {
        return _id;
    }

    @Override
    public GamePoint getCoordinates() {
        return GamePoint.NONE;
    }

    @Override
    public int getIndex()
    {
        return 999;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(_marginLeft - 2, _marginTop - 2, _width + 4, _height + 4);
        g.setColor(Color.BLACK);
        g.drawRect(_marginLeft, _marginTop, _width, _height);

        g.setFont(GameConstants.getDialogFont());
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