package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public class GameCharacter implements GameObject {
    private String _id;
    private GamePoint _coordinates;
    private boolean _toDelete;
    private GameCharacterType _type;
    private GameSprite _sprite;

    @Override
    public String getId() {
        return _id;
    }

    public GameCharacter()
    {
        _id = "DEFAULT_CHARACTER_ID";
        _coordinates = new GamePoint(4, 6);
        _sprite = GameSprite.PLAYER_FACING_RIGHT;
        _type = GameCharacterType.TEXT_ONLY;
        _toDelete = false;
    }

    @Override
    public GamePoint getCoordinates() {
        return _coordinates;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void render(Graphics g) {
        int tileSize = GameConstants.getTileSize();
        g.drawImage(GameConstants.getSprite(_sprite), _coordinates.x * tileSize, _coordinates.y * tileSize,null);
    }

    @Override
    public GameData update(GameData d) {
        if (!d.getGameState().equals(GameState.RUNNING))
        {
            return d;
        }

        // Todo: movement

        if (!d.isPlayerLookingAt(this) || !d.getInput().equals(GameInput.INSPECT))
        {
            return d;
        }

        switch (_type)
        {
            case TEXT_ONLY:
            {
                d.addObjectToScene(new GameDialog("TEXT_FROM_CHARACTER", "Hello adventurer!"));
                break;
            }
            default:
            {
                break;
            }
        }

        return d;
    }

    @Override
    public boolean toDelete() {
        return _toDelete;
    }
}
