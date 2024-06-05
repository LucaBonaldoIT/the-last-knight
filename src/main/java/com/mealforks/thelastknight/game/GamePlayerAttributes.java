/**
 * The GamePlayerAttributes class represents the attributes of a player character in the game.
 * It stores attributes such as strength, endurance, luck, intelligence, and agility.
 */
package main.java.com.mealforks.thelastknight.game;

public class GamePlayerAttributes {
    private int _strength; // Strength attribute of the player
    private int _endurance; // Endurance attribute of the player
    private int _luck; // Luck attribute of the player
    private int _intelligence; // Intelligence attribute of the player
    private int _agility; // Agility attribute of the player

    /**
     * Constructor for the GamePlayerAttributes class.
     * Initializes all attributes to 0.
     */
    public GamePlayerAttributes() {
        _strength = 0;
        _endurance = 0;
        _luck = 0;
        _intelligence = 0;
        _agility = 0;
    }

    /**
     * Retrieves the endurance attribute of the player.
     *
     * @return The endurance attribute value.
     */
    public int getEndurance() {
        return _endurance;
    }

    /**
     * Retrieves the agility attribute of the player.
     *
     * @return The agility attribute value.
     */
    public int getAgility() {
        return _agility;
    }

    /**
     * Retrieves the strength attribute of the player.
     *
     * @return The strength attribute value.
     */
    public int getStrength() {
        return _strength;
    }

    /**
     * Retrieves the intelligence attribute of the player.
     *
     * @return The intelligence attribute value.
     */
    public int getIntelligence() {
        return _intelligence;
    }

    /**
     * Retrieves the luck attribute of the player.
     *
     * @return The luck attribute value.
     */
    public int getLuck() {
        return _luck;
    }

    /**
     * Sets the agility attribute of the player.
     *
     * @param agility The new value of the agility attribute.
     */
    public void setAgility(int agility) {
        _agility = agility;
    }

    /**
     * Sets the luck attribute of the player.
     *
     * @param luck The new value of the luck attribute.
     */
    public void setLuck(int luck) {
        _luck = luck;
    }

    /**
     * Sets the strength attribute of the player.
     *
     * @param strength The new value of the strength attribute.
     */
    public void setStrength(int strength) {
        _strength = strength;
    }

    /**
     * Sets the endurance attribute of the player.
     *
     * @param endurance The new value of the endurance attribute.
     */
    public void setEndurance(int endurance) {
        _endurance = endurance;
    }

    /**
     * Sets the intelligence attribute of the player.
     *
     * @param intelligence The new value of the intelligence attribute.
     */
    public void setIntelligence(int intelligence) {
        _intelligence = intelligence;
    }

    /**
     * Updates player attributes based on the player's class when reaching a new level.
     *
     * @param playerClass The class of the player character.
     */
    public void onNewLevel(GamePlayerClass playerClass) {
        switch (playerClass) {
            case KNIGHT: {
                _strength += 3;
                _endurance += 2;
                _intelligence += 1;
                _agility += 1;
                break;
            }
            case MAGE: {
                _strength += 1;
                _endurance += 1;
                _intelligence += 4;
                _luck += 1;
                break;
            }
            case THIEF: {
                _agility += 3;
                _endurance += 2;
                _strength += 1;
                _intelligence += 1;
                break;
            }
        }
    }
}
