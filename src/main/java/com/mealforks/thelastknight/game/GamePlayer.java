package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GamePlayer implements GameObject {

    private int _x;
    private int _y;

    private int _updateCount;

    public GamePlayer(int x, int y)
    {
        _x = x;
        _y = y;
        _updateCount = 0;
    }

    @Override
    public String getId() {
        return "PLAYER";
    }

    @Override
    public int getIndex() {
        return 1;
    }

    @Override
    public void render(Graphics g) {
        int tileSize = GameConstants.getTileSize();
        g.drawImage(GameConstants.getTile(GameTile.FADING_BRICK), _x * tileSize, _y * tileSize, null);
    }

    @Override
    public GameData update(GameData d) {

        int x = _x;
        int y = _y;

        switch (d.getGameState())
        {
            case RUNNING:
            {
                switch (d.getInput())
                {
                    case UP:
                    {
                        y--;
                        break;
                    }
                    case DOWN:
                    {
                        y++;
                        break;
                    }
                    case RIGHT:
                    {
                        x++;
                        break;
                    }
                    case LEFT:
                    {
                        x--;
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                break;
            }
            default:
            {
                break;
            }
        }

        if (x >= 0 && x < GameConstants.getWidth() / GameConstants.getTileSize() && y >= 0 && y < GameConstants.getHeight() / GameConstants.getTileSize())
        {
            if (d.getGameRoom().getGameArea().IsTileEmpty(x, y))
            {
                _y = y;
                _x = x;
            }
        }

        return d;
    }
}
