package main.java.com.mealforks.thelastknight.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.dgc.Lease;

public class GameFrame extends JFrame {
    private static final int TARGET_FPS = 120;

    private static final int DELAY = 1000 / TARGET_FPS;

    private static GameFrame _instance;

    private boolean _isRunning;

    private final GameCanvas _canvas;

    private GameData _data;

    private GameFrame()
    {
        _data = new GameData();

        this.setTitle(GameConstants.getTitle());
        this.setSize(GameConstants.getWidth(), GameConstants.getHeight());
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(null);

        _canvas = new GameCanvas();

        this.add(_canvas);

        this.setVisible(true);

        _isRunning = false;
    }

    public static GameFrame getInstance()
    {
        if (_instance == null)
        {
            _instance = new GameFrame();
        }

        return _instance;
    }

    public void run()
    {
        if (_isRunning)
        {
            return;
        }

        _isRunning = true;

        this.addKeyListener(new KeyListener() {
            private boolean _pressed = false;
            private Timer _timer = new Timer(250, (a) -> {
                _pressed = false;
            });

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (_pressed)
                {
                    return;
                }

                _timer.setRepeats(false);

                _timer.start();

                _pressed = true;

                _data.setInput(GameInputHandler.getInstance().getGameInput(e));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                _data.setInput(GameInput.NONE);
                _pressed = false;
                _timer.stop();
            }
        });

        ActionListener loop = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _update();
                _render();
            }
        };

        new Timer(DELAY, loop).start();
    }

    private void _update()
    {
        switch(_data.getGameState())
        {
            case UNKNOWN:
            {
                break;
            }
            case READY:
            {
                // Example of use case, should be more complex
                GameEvents.TestDialog1(_data);
            }
            case RUNNING:
            {
                // For the moment nothing
                break;
            }
            case DIALOG:
            {
                // Process dialog-state
                break;
            }
        }

        _data = _data.getPlayer().update(_data);

        for (GameObject obj : _data.getGameObjects())
        {
            _data = obj.update(_data);
        }

        _data.getGameObjects().removeIf(GameObject::toDelete);

        _data = _data.getGameRoom().update(_data);

        _data.setInput(GameInput.NONE);


    }

    private void _render()
    {
        _canvas.update(_data);
        _canvas.repaint();
    }
}
