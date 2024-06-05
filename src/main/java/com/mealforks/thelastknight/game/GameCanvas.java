package main.java.com.mealforks.thelastknight.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The GameCanvas class represents the canvas where the game is rendered.
 * It extends JPanel and provides methods for rendering the game and updating its state.
 */
public class GameCanvas extends JPanel {
    private GameData _data;

    private long _frameCount;
    private long _lastFrameTime;
    private int _avgFps;

    /**
     * Constructor for the GameCanvas class.
     * Initializes the canvas and sets its size, layout, and background color.
     */
    public GameCanvas() {
        _frameCount = 0;
        _lastFrameTime = 0;
        _avgFps = 0;

        _data = new GameData();

        this.setLayout(null);
        this.setSize(GameConstants.getWidth(), GameConstants.getHeight());
        this.setBackground(Color.BLACK);
    }

    /**
     * Displays the current frames per second (FPS) on the canvas.
     *
     * @param g The Graphics object used for rendering.
     */
    private void showFps(Graphics g) {
        _frameCount++;

        long now = System.currentTimeMillis();

        if (now - _lastFrameTime > 250) {
            _avgFps = (int) (_frameCount / (float) (now - _lastFrameTime) * 1000);
            _lastFrameTime = now;
            _frameCount = 0;
        }

        g.drawString(String.format("FPS: %d", _avgFps), 5, g.getFontMetrics().getHeight());
    }

    /**
     * Scales the canvas to fit the frame while maintaining aspect ratio.
     *
     * @param g2d The Graphics2D object used for rendering.
     */
    private void scaleToFrame(Graphics2D g2d) {
        GameFrame game = GameFrame.getInstance();

        Dimension dim = game.getSize();

        float frameAspectRatio = dim.width / (float) dim.height;

        int panelWidth;
        int panelHeight;

        if (frameAspectRatio > GameConstants.getAspectRatio()) {
            panelHeight = Math.min(dim.height, GameConstants.getMaxWidth());
            panelWidth = (int) (panelHeight * GameConstants.getAspectRatio());
        } else {
            panelWidth = Math.min(dim.width, GameConstants.getMaxHeight());
            panelHeight = (int) (panelWidth / GameConstants.getAspectRatio());
        }

        g2d.scale(panelWidth / (float) GameConstants.getWidth(), panelHeight / (float) GameConstants.getHeight());

        this.setSize(new Dimension(panelWidth, panelHeight));
        this.setLocation((dim.width - panelWidth) / 2, (dim.height - panelHeight) / 2);
        this.revalidate();
    }

    /**
     * Overrides the paintComponent method to render the game.
     *
     * @param g The Graphics object used for rendering.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (_data.getGameSetting() == null) {
            return;
        }

        super.paintComponent(g);

        this.scaleToFrame((Graphics2D) g);

        if (_data.getGameSetting().getShowFpsCounter()) {
            this.showFps(g);
        }

        if (_data.getGameState().equals(GameState.GAME_OVER) || _data.getGameState().equals(GameState.GAME_END)) {
            for (GameObject r : _data.getGameObjects()) {
                r.render(g);
            }

            return;
        }

        _data.getGameRoom().render(g);

        _data.getGameObjects().sort((o1, o2) -> o1.getIndex() - o2.getIndex());

        _data.getPlayer().render(g);

        for (GameObject r : _data.getGameObjects()) {
            r.render(g);
        }

        _data.getInventory().render(g);
        _data.getPauseMenu().render(g);
    }

    /**
     * Updates the game data associated with the canvas.
     *
     * @param data The updated game data.
     */
    public void update(GameData data) {
        _data = data;
    }
}