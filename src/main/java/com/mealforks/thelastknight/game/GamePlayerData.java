package main.java.com.mealforks.thelastknight.game;

import java.util.HashMap;

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

    public GamePlayerData()
    {
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

    public HashMap<GameItemType, Integer> getInventory()
    {
        return _inventory;
    }

    public void useItem(GameItemType type, GameData data)
    {
        switch (type)
        {
            default:
            {
                data.addObjectToScene(new GameDialog("KING_WORDS", "The king's words echoed... \"There's a time and place for everything but not now!\""));
                break;
            }
        }
    }

    public void addItem(GameItemType type)
    {
        GameItem item = GameConstants.getItem(type);

        _inventoryWeight += item.getWeight();

        if (_inventory.containsKey(item.getItemType()))
        {
            _inventory.put(item.getItemType(), _inventory.get(item.getItemType()) + 1);
        }
        else
        {
            _inventory.put(item.getItemType(), 1);
        }
    }

    public void removeItem(GameItemType type)
    {
        if (_inventory.containsKey(type))
        {
            int count = _inventory.get(type);

            if (count > 0)
            {
                GameItem item = GameConstants.getItem(type);

                _inventoryWeight -= item.getWeight();

                _inventory.put(type, count - 1);
            }
        }
    }


    public int getMaxWeight()
    {
        return _maxWeight;
    }

    public int getInvetoryWeight()
    {
        return _inventoryWeight;
    }

    public void addXp(int xp)
    {
        _xp += xp;

        if (_xp > XP_PER_LEVEL * (_level + 1))
        {
            _xp %= XP_PER_LEVEL * (_level + 1);
            _level++;

            this.onNewLevel();
        }
    }

    public int getXp() {
        return _xp;
    }

    public void setXp(int xp) {
        this._xp = xp;
    }

    public int getLevel() {
        return _level;
    }

    public void setLevel(int level) {
        this._level = level;
    }

    public void setInventory(HashMap<GameItemType, Integer> inventory) {
        this._inventory = inventory;
    }

    public GamePlayerAttributes getAttributes() {
        return _attributes;
    }

    public void setAttributes(GamePlayerAttributes attributes) {
        this._attributes = attributes;
    }

    public int getHealth() {
        return _health;
    }

    public void setHealth(int health) {
        this._health = health;
    }

    public int getStamina() {
        return _stamina;
    }

    public void setStamina(int stamina) {
        this._stamina = stamina;
    }

    public int getMana() {
        return _mana;
    }

    public void setMana(int mana) {
        this._mana = mana;
    }

    public GamePlayerClass getPlayerClass() {
        return _class;
    }

    public void setPlayerClass(GamePlayerClass playerClass) {
        this._class = playerClass;
    }

    public void setMaxWeight(int maxWeight) {
        this._maxWeight = maxWeight;
    }

    public int getInventoryWeight() {
        return _inventoryWeight;
    }

    public void setInventoryWeight(int inventoryWeight) {
        this._inventoryWeight = inventoryWeight;
    }

    public int getCoins() {
        return _coins;
    }

    public void setCoins(int coins) {
        this._coins = coins;
    }

    private void onNewLevel()
    {
        _attributes.onNewLevel(_class);
    }
}
