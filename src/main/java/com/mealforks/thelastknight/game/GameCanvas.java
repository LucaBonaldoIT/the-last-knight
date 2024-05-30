package main.java.com.mealforks.thelastknight.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameCanvas extends JPanel {
    private GameData _data;

    private long _frameCount;
    private long _lastFrameTime;
    private int _avgFps;

    public GameCanvas()
    {
        _frameCount = 0;
        _lastFrameTime = 0;
        _avgFps = 0;

        _data = new GameData();

        this.setLayout(null);
        this.setSize(GameConstants.getWidth(), GameConstants.getHeight());
        this.setBackground(Color.BLACK);
    }

    private void showFps(Graphics g)
    {
        _frameCount++;

        long now = System.currentTimeMillis();

        if (now - _lastFrameTime > 250)
        {
            _avgFps = (int)(_frameCount / (float)(now - _lastFrameTime) * 1000);
            _lastFrameTime = now;
            _frameCount = 0;
        }

        g.drawString(String.format("FPS: %d", _avgFps), 5, g.getFontMetrics().getHeight());
    }

    private void scaleToFrame(Graphics2D g2d)
    {
        GameFrame game = GameFrame.getInstance();

        Dimension dim = game.getSize();

        float frameAspectRatio = dim.width / (float)dim.height;

        int panelWidth;
        int panelHeight;

        if (frameAspectRatio > GameConstants.getAspectRatio())
        {
            panelHeight = Math.min(dim.height, GameConstants.getMaxWidth());
            panelWidth = (int) (panelHeight * GameConstants.getAspectRatio());
        }
        else
        {
            panelWidth = Math.min(dim.width, GameConstants.getMaxHeight());
            panelHeight = (int) (panelWidth / GameConstants.getAspectRatio());
        }

        g2d.scale(panelWidth / (float)GameConstants.getWidth(), panelHeight / (float)GameConstants.getHeight());

        this.setSize(new Dimension(panelWidth, panelHeight));
        this.setLocation((dim.width - panelWidth) / 2, (dim.height - panelHeight) / 2);
        this.revalidate();
    }

    public void paintComponent(Graphics g)
    {
        if (_data.getGameSetting() == null)
        {
            return;
        }

        super.paintComponent(g);

        this.scaleToFrame((Graphics2D)g);

        if (_data.getGameSetting().getShowFpsCounter())
        {
            this.showFps(g);
        }

        _data.getGameRoom().render(g);

        _data.getGameObjects().sort((o1, o2) -> o1.getIndex() - o2.getIndex());

        _data.getPlayer().render(g);

        for (GameObject r : _data.getGameObjects())
        {
            r.render(g);
        }

        _data.getInventory().render(g);
    }

    public void update(GameData data)
    {
        _data = data;
    }
}
