package main.java.com.mealforks.thelastknight.game;

import java.util.HashMap;

/**
 * Represents the data associated with a player in the game.
 */
public class GamePlayerData {
    private static final int MAX_LEVEL = 30;
    private static final int XP_PER_LEVEL = 50;
    public static final int MAX_ITEMS = 10;

    private int _xp;
    private int _level;
    private HashMap<GameItemType, Integer> _inventory;
    private GamePlayerAttributes _attributes;
    private int _health;
    private int _stamina;
    private int _mana;
    private GamePlayerClass _class;
    private int _maxWeight;
    private int _inventoryWeight;
    private int _coins;

    /**
     * Constructs a new GamePlayerData object with default values.
     */
    public GamePlayerData() {
        _xp = 0;
        _level = 0;
        _inventory = new HashMap<>();
        _attributes = new GamePlayerAttributes();
        _class = GamePlayerClass.NONE;
        _health = 0;
        _stamina = 0;
        _mana = 0;
        _maxWeight = 100;
        _coins = 0;
        _inventoryWeight = 0;
    }

    /**
     * Returns the player's inventory.
     *
     * @return The player's inventory.
     */
    public HashMap<GameItemType, Integer> getInventory() {
        return _inventory;
    }

    /**
     * Uses an item from the player's inventory.
     *
     * @param type The type of item to use.
     * @param data The game data object.
     */
    public void useItem(GameItemType type, GameData data) {
        switch (type) {
            case HEALTH_POTION:
                _health += 10;
                data.addObjectToScene(new GameDialog("HEALTH_POTION_USED", "You drank the potion and felt relieved! (+10 HP)"));
                this.removeItem(type);
                break;
            case STRENGTH_AMULET:
                _attributes.setStrength(_attributes.getStrength() + 1);
                data.addObjectToScene(new GameDialog("STRENGTH_AMULET_USED", "You equipped the amulet and now you are feeling stronger! (+1 strength)"));
                this.removeItem(type);
                break;
            default:
                data.addObjectToScene(new GameDialog("KING_WORDS", "The king's words echoed... \"There's a time and place for everything but not now!\""));
                break;
        }
    }

    /**
     * Adds coins to the player's inventory.
     *
     * @param coinsToAdd The number of coins to add.
     */
    public void addCoins(int coinsToAdd) {
        if (coinsToAdd < 0) {
            return;
        }

        _coins += coinsToAdd;
    }

    /**
     * Removes coins from the player's inventory.
     *
     * @param coinsToRemove The number of coins to remove.
     */
    public void removeCoins(int coinsToRemove) {
        if (coinsToRemove < 0) {
            return;
        }

        _coins += coinsToRemove;
    }

    /**
     * Inflicts damage to the player.
     *
     * @param damage The amount of damage to inflict.
     * @param d      The game data object.
     */
    public void takeDamage(int damage, GameData d) {
        int physicalDefense = _attributes.getEndurance() + _attributes.getStrength(); // Physical defense based on Endurance and Strength
        int magicalDefense = _attributes.getIntelligence() + _attributes.getLuck(); // Magical defense based on Intelligence and Luck

        int totalDefense = physicalDefense + magicalDefense;

        int modifiedDamage = Math.max(damage - totalDefense, 0); // Ensures damage is not negative

        _health -= modifiedDamage;

        if (_health < 0) {
            d.setGameState(GameState.GAME_OVER);
        }
    }

    /**
     * Returns the total defense of the player.
     *
     * @return The total defense of the player.
     */
    public int getDefense() {
        int physicalDefense = _attributes.getEndurance() + _attributes.getStrength();
        int magicalDefense = _attributes.getIntelligence() + _attributes.getLuck();

        return physicalDefense + magicalDefense;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param type The type of item to add.
     */
    public void addItem(GameItemType type) {
        GameItem item = GameConstants.getItem(type);

        _inventoryWeight += item.getWeight();

        if (_inventory.containsKey(item.getItemType())) {
            _inventory.put(item.getItemType(), _inventory.get(item.getItemType()) + 1);
        } else {
            _inventory.put(item.getItemType(), 1);
        }
    }

    /**
     * Removes an item from the player's inventory.
     *
     * @param type The type of item to remove.
     */
    public void removeItem(GameItemType type) {
        if (_inventory.containsKey(type)) {
            int count = _inventory.get(type);

            if (count > 0) {
                GameItem item = GameConstants.getItem(type);

                _inventoryWeight -= item.getWeight();

                _inventory.put(type, count - 1);
            }
        }
    }

    /**
     * Returns the maximum weight the player can carry.
     *
     * @return The maximum weight the player can carry.
     */
    public int getMaxWeight() {
        return _maxWeight;
    }

    /**
     * Adds experience points to the player and handles level-up logic.
     *
     * @param xp The experience points to add.
     */
    public void addXp(int xp) {
        _xp += xp;

        while (_xp > XP_PER_LEVEL * (_level + 1)) {
            _xp = _xp - XP_PER_LEVEL * (_level + 1);
            _level++;

            this.onNewLevel();
        }
    }

    /**
     * Returns the experience points of the player.
     *
     * @return The experience points of the player.
     */
    public int getXp() {
        return _xp;
    }

    /**
     * Sets the experience points of the player.
     *
     * @param xp The experience points to set.
     */
    public void setXp(int xp) {
        this._xp = xp;
    }

    /**
     * Retrieves the weight of the player's inventory.
     *
     * @return The weight of the player's inventory.
     */
    public int getInvetoryWeight()
    {
        return _inventoryWeight;
    }

    /**
     * Returns the player's level.
     *
     * @return The player's level.
     */
    public int getLevel() {
        return _level;
    }

    /**
     * Sets the player's level.
     *
     * @param level The level to set.
     */
    public void setLevel(int level) {
        this._level = level;
    }

    /**
     * Sets the player's inventory.
     *
     * @param inventory The inventory to set.
     */
    public void setInventory(HashMap<GameItemType, Integer> inventory) {
        this._inventory = inventory;
    }

    /**
     * Returns the player's attributes.
     *
     * @return The player's attributes.
     */
    public GamePlayerAttributes getAttributes() {
        return _attributes;
    }

    /**
     * Sets the player's attributes.
     *
     * @param attributes The attributes to set.
     */
    public void setAttributes(GamePlayerAttributes attributes) {
        this._attributes = attributes;
    }

    /**
     * Returns the player's health.
     *
     * @return The player's health.
     */
    public int getHealth() {
        return _health;
    }

    /**
     * Sets the player's health.
     *
     * @param health The health to set.
     */
    public void setHealth(int health) {
        this._health = health;
    }

    /**
     * Returns the player's stamina.
     *
     * @return The player's stamina.
     */
    public int getStamina() {
        return _stamina;
    }

    /**
     * Sets the player's stamina.
     *
     * @param stamina The stamina to set.
     */
    public void setStamina(int stamina) {
        this._stamina = stamina;
    }

    /**
     * Returns the player's mana.
     *
     * @return The player's mana.
     */
    public int getMana() {
        return _mana;
    }

    /**
     * Sets the player's mana.
     *
     * @param mana The mana to set.
     */
    public void setMana(int mana) {
        this._mana = mana;
    }

    /**
     * Returns the player's class.
     *
     * @return The player's class.
     */
    public GamePlayerClass getPlayerClass() {
        return _class;
    }

    /**
     * Sets the player's class.
     *
     * @param playerClass The class to set.
     */
    public void setPlayerClass(GamePlayerClass playerClass) {
        this._class = playerClass;
    }

    /**
     * Sets the player's maximum weight.
     *
     * @param maxWeight The maximum weight to set.
     */
    public void setMaxWeight(int maxWeight) {
        this._maxWeight = maxWeight;
    }

    /**
     * Returns the weight of the player's inventory.
     *
     * @return The weight of the player's inventory.
     */
    public int getInventoryWeight() {
        return _inventoryWeight;
    }

    /**
     * Sets the player's inventory weight.
     *
     * @param inventoryWeight The inventory weight to set.
     */
    public void setInventoryWeight(int inventoryWeight) {
        this._inventoryWeight = inventoryWeight;
    }

    /**
     * Returns the player's coins.
     *
     * @return The player's coins.
     */
    public int getCoins() {
        return _coins;
    }

    /**
     * Sets the player's coins.
     *
     * @param coins The coins to set.
     */
    public void setCoins(int coins) {
        this._coins = coins;
    }


    private void onNewLevel() {
        _attributes.onNewLevel(_class);
    }
}
