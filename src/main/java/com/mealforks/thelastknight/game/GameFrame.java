package main.java.com.mealforks.thelastknight.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.dgc.Lease;

/**
 * This class represents the main frame of the game.
 */
public class GameFrame extends JFrame {
    private static final int TARGET_FPS = 120;

    private static final int DELAY = 1000 / TARGET_FPS;

    private static GameFrame _instance;

    private boolean _isRunning;

    private final GameCanvas _canvas;

    private GameData _data;

    /**
     * Singleton, so private constructor.
     */
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

    /**
     * Get the instance of GameFrame.
     *
     * @return The instance of GameFrame.
     */
    public static GameFrame getInstance()
    {
        if (_instance == null)
        {
            _instance = new GameFrame();
        }

        return _instance;
    }

    /**
     * Run the game.
     */
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

    /**
     * checks if there is a save file and loads the correct level
     */
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

                GameDataSave save = null;

                if (Files.exists(Paths.get(GameConstants.getSaveFileName())))
                {
                    save = GameDataLoader.getInstance().loadFromJson(GameConstants.getSaveFileName());
                }

                if (save == null)
                {
                    save = GameDataLoader.getInstance().loadFromCloud(GameConstants.getSaveFileUrl() + "/get-save-file");
                }

                if (save == null)
                {
                    GameEvents.LoadLevel1(_data);
                }
                else
                {
                    switch (save.getCurrentLevel())
                    {
                        case 0:
                        {
                            GameEvents.LoadLevel1(_data, save);
                            break;
                        }
                        case 1:
                        {
                            GameEvents.LoadLevel2(_data, save);
                            break;
                        }
                        case 2:
                        {
                            GameEvents.LoadLevel3(_data, save);
                            break;
                        }
                        case 3:
                        {
                            GameEvents.LoadLevelBoss(_data, save);
                            break;
                        }
                    }
                }
                break;
            }
            case LOAD_NEXT_LEVEL:
            {
                switch (_data.getCurrentLevel().getLevelIndex())
                {
                    case 0:
                    {
                        GameEvents.LoadLevel2(_data);
                        break;
                    }
                    case 1:
                    {
                        GameEvents.LoadLevel3(_data);
                        break;
                    }
                    case 2:
                    {
                        GameEvents.LoadLevelBoss(_data);
                        break;
                    }
                }
                break;
            }
            case GAME_END:
            {
                if (_data.getGameObjects().stream().noneMatch(x -> x instanceof GameNarratorDialog))
                {
                    _data.clearScene();
                    _data.addObjectToScene(GameNarratorDialog.getGameEndDialog());
                }

                for (Object obj : _data.getGameObjects().toArray())
                {
                    _data = ((GameObject)obj).update(_data);
                }

                return;
            }
            case GAME_OVER:
            {
                if (_data.getGameObjects().stream().noneMatch(x -> x instanceof GameNarratorDialog))
                {
                    _data.clearScene();
                    _data.addObjectToScene(GameNarratorDialog.getGameOverDialog());
                }

                for (Object obj : _data.getGameObjects().toArray())
                {
                    _data = ((GameObject)obj).update(_data);
                }

                return;
            }
        }

        _data = _data.getPlayer().update(_data);

        for (Object obj : _data.getGameObjects().toArray())
        {
            _data = ((GameObject)obj).update(_data);
        }

        _data.getGameObjects().removeIf(GameObject::toDelete);

        _data = _data.getGameRoom().update(_data);

        _data = _data.getInventory().update(_data);

        _data = _data.getPauseMenu().update(_data);

        GameAudioHandler.getInstance().process(_data);

        if (_data.getInput().equals(GameInput.INSPECT) && _data.getGameObjects().stream().noneMatch(x -> _data.isPlayerLookingAt(x)) && _data.getGameState().equals(GameState.RUNNING))
        {
            _data.addObjectToScene(new GameDialog("NOTHING_TO_LOOK_AT", "There is nothing to look at..."));
        }

        _data.setInput(GameInput.NONE);
    }

    /**
     * Update the rendering of the game.
     */
    private void _render()
    {
        _canvas.update(_data);
        _canvas.repaint();
    }
}
