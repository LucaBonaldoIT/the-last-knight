package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GamePlayer implements GameObject {

    private int _x;
    private int _y;

    private int _updateCount;
    private GameDirection _facingDirection;

    public GamePlayer(int x, int y)
    {
        _x = x;
        _y = y;
        _updateCount = 0;
        _facingDirection = GameDirection.DOWN;
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

        GameTile playerSprite = switch (_facingDirection)
        {
            case UP -> GameTile.BRICK;
            case DOWN -> GameTile.BRICK;
            case LEFT -> GameTile.FADING_BRICK;
            case RIGHT -> GameTile.FADING_BRICK;
            default -> GameTile.FADING_BRICK;
        };

        g.drawImage(GameConstants.getTile(playerSprite), _x * tileSize, _y * tileSize, null);
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
                        _facingDirection = GameDirection.UP;
                        break;
                    }
                    case DOWN:
                    {
                        y++;
                        _facingDirection = GameDirection.DOWN;
                        break;
                    }
                    case RIGHT:
                    {
                        x++;
                        _facingDirection = GameDirection.RIGHT;
                        break;
                    }
                    case LEFT:
                    {
                        x--;
                        _facingDirection = GameDirection.LEFT;
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

    @Override
    public boolean toDelete() {
        return false;
    }
}
