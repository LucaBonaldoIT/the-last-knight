/**
 * The GamePlayer class represents the player character in the game.
 * It handles the player's position, direction, rendering, and movement logic.
 */
package main.java.com.mealforks.thelastknight.game;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GamePlayer implements GameObject {

    private int _x; // X-coordinate of the player
    private int _y; // Y-coordinate of the player
    private int _updateCount; // Counter for updates (unused)
    private GameDirection _facingDirection; // Direction the player is facing

    /**
     * Constructor for the GamePlayer class.
     * Initializes the player's position and facing direction.
     *
     * @param x               The initial X-coordinate of the player.
     * @param y               The initial Y-coordinate of the player.
     */
    public GamePlayer(int x, int y) {
        _x = x;
        _y = y;
        _updateCount = 0;
        _facingDirection = GameDirection.DOWN; // Default facing direction
    }

    /**
     * Constructor for the GamePlayer class.
     * Initializes the player's position, facing direction, and initial facing direction.
     *
     * @param x               The initial X-coordinate of the player.
     * @param y               The initial Y-coordinate of the player.
     * @param facingDirection The initial facing direction of the player.
     */
    public GamePlayer(int x, int y, GameDirection facingDirection) {
        _x = x;
        _y = y;
        _facingDirection = facingDirection;
        _updateCount = 0;
    }

    /**
     * Retrieves the facing direction of the player.
     *
     * @return The facing direction of the player.
     */
    public GameDirection getFacingDirection() {
        return _facingDirection;
    }

    /**
     * Retrieves the X-coordinate of the player.
     *
     * @return The X-coordinate of the player.
     */
    public int getX() {
        return _x;
    }

    /**
     * Retrieves the Y-coordinate of the player.
     *
     * @return The Y-coordinate of the player.
     */
    public int getY() {
        return _y;
    }

    @Override
    public String getId() {
        return "PLAYER";
    }

    @Override
    public GamePoint getCoordinates() {
        return new GamePoint(_x, _y); // Kept X and Y for retro-compatibility
    }

    @Override
    public int getIndex() {
        return 1;
    }

    /**
     * Renders the player character on the screen based on the facing direction.
     *
     * @param g The graphics context.
     */
    @Override
    public void render(Graphics g) {
        int tileSize = GameConstants.getTileSize();

        GameSprite playerSprite = switch (_facingDirection) {
            case UP -> GameSprite.PLAYER_FACING_UP;
            case DOWN -> GameSprite.PLAYER_FACING_DOWN;
            case LEFT -> GameSprite.PLAYER_FACING_LEFT;
            case RIGHT -> GameSprite.PLAYER_FACING_RIGHT;
            default -> GameSprite.PLAYER_FACING_DOWN;
        };

        g.drawImage(GameConstants.getSprite(playerSprite), _x * tileSize, _y * tileSize, null);
    }

    /**
     * Updates the player's position based on the game state and input.
     * Handles collisions and boundary checks.
     *
     * @param d The current game data.
     * @return The updated game data.
     */
    @Override
    public GameData update(GameData d) {

        if (d.getPlayerData().getHealth() <= 0) {
            d.setGameState(GameState.GAME_OVER);
            return d;
        }

        int x = _x;
        int y = _y;

        switch (d.getGameState()) {
            case RUNNING: {
                switch (d.getInput()) {
                    case UP: {
                        y--;
                        _facingDirection = GameDirection.UP;
                        break;
                    }
                    case DOWN: {
                        y++;
                        _facingDirection = GameDirection.DOWN;
                        break;
                    }
                    case RIGHT: {
                        x++;
                        _facingDirection = GameDirection.RIGHT;
                        break;
                    }
                    case LEFT: {
                        x--;
                        _facingDirection = GameDirection.LEFT;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            default: {
                break;
            }
        }

        if (x >= 0 && x < GameConstants.getWidth() / GameConstants.getTileSize() && y >= 0 && y <= GameConstants.getHeight() / GameConstants.getTileSize()) {
            for (GameObject obj : d.getGameObjects()) {
                if (obj.getCoordinates().x == x && obj.getCoordinates().y == y) {
                    d.addSoundToBuffer(GameSound.STEP_BUMP);
                    return d;
                }
            }

            if (d.getGameRoom() != null && d.getGameRoom().getGameArea() != null && d.getGameRoom().getGameArea().IsTileEmpty(x, y)) {
                _y = y;
                _x = x;
            } else {
                d.addSoundToBuffer(GameSound.STEP_BUMP);
            }
        }

        return d;
    }

    @Override
    public boolean toDelete() {
        return false;
    }
}
