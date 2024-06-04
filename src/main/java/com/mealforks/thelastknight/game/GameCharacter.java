package main.java.com.mealforks.thelastknight.game;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class GameCharacter implements GameObject {
    private String _id;
    private GamePoint _coordinates;
    private boolean _toDelete;
    private GameCharacterType _type;
    private GameSprite _sprite;

    private String _text;

    private GameItem _itemToGift;
    private GameItem _itemToTrade;
    private boolean _tradeBuy;

    private boolean _hasGifted;
    private boolean _hasFought;
    private boolean _hasTraded;

    private GameCombatMenu _combatMenu;
    private GameCombatType _combatType;
    private int _health;
    private int _attackPower;
    private int _defense;
    private boolean _isInCombat;
    private boolean _playerTurn = true;

    @Override
    public String getId() {
        return _id;
    }

    public static GameCharacter getGiftCharacter(GameItem item, GameSprite sprite, GamePoint coordinates)
    {
        return new GameCharacter(item, sprite, coordinates);
    }

    public static GameCharacter getTextCharacter(String text, GameSprite sprite, GamePoint coordinates)
    {
        return new GameCharacter(text, sprite, coordinates);
    }

    public static GameCharacter getTradeCharacter(GameSprite sprite, GamePoint coordinates)
    {
        return new GameCharacter("TRADE_CHARACTER", GameCharacterType.TRADE, coordinates, sprite);
    }

    public static GameCharacter getCombatCharacter(GameSprite sprite, GamePoint coordinates, GameCombatType type, int health, int attackPower, int defense)
    {
        return new GameCharacter(sprite, coordinates, GameCharacterType.COMBAT, type, health, attackPower, defense);
    }

    public GameCharacter(GameSprite sprite, GamePoint coordinates, GameCharacterType type, GameCombatType combatType, int health, int attackPower, int defense) {
        _id = "COMBAT_CHARACTER";
        _coordinates = coordinates;
        _sprite = sprite;
        _type = type;
        _combatType = combatType;

        _health = health;
        _attackPower = attackPower;
        _defense = defense;
        _isInCombat = false;

        _hasFought = false;
        _hasGifted = false;
        _hasTraded = false;
        _toDelete = false;
        _combatMenu = new GameCombatMenu();
    }

    private GameCharacter(GameItem item, GameSprite sprite, GamePoint coordinates)
    {
        _id = "GIFT_CHARACTER";
        _coordinates = coordinates;
        _sprite = sprite;
        _type = GameCharacterType.GIFT;
        _itemToGift = item;

        _hasFought = false;
        _hasGifted = false;
        _hasTraded = false;
        _toDelete = false;
        _combatMenu = new GameCombatMenu();
    }

    private GameCharacter(String text, GameSprite sprite, GamePoint coordinates)
    {
        _id = "TEXT_ONLY_CHARACTER";
        _coordinates = coordinates;
        _sprite = sprite;
        _type = GameCharacterType.TEXT_ONLY;
        _text = text;

        _hasFought = false;
        _hasGifted = false;
        _hasTraded = false;
        _toDelete = false;
        _combatMenu = new GameCombatMenu();
    }

    public GameCharacter(String id, GameCharacterType type, GamePoint coordinates, GameSprite sprite)
    {
        _id = id;
        _coordinates = coordinates;
        _sprite = sprite;
        _type = type;

        _hasFought = false;
        _hasGifted = false;
        _hasTraded = false;
        _toDelete = false;
        _combatMenu = new GameCombatMenu();
    }

    public GameCharacter()
    {
        _id = "DEFAULT_CHARACTER_ID";
        _coordinates = new GamePoint(4, 6);
        _sprite = GameSprite.NONE;
        _type = GameCharacterType.NONE;

        _hasFought = false;
        _hasGifted = false;
        _hasTraded = false;
        _toDelete = false;
        _combatMenu = new GameCombatMenu();
    }

    // Combat methods
    public void attack(GamePlayerData player, GameData d) {
        player.takeDamage((int)(_attackPower * ((new Random()).nextDouble() + 1)), d);
    }

    public void takeDamage(int damage, GameData d) {
        _health -= damage;
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

        if (_isInCombat)
        {
            _combatMenu.render(g);
        }
    }

    private void handleCombat(GameData d) {
        if (_playerTurn) {
            switch (d.getInput()) {
                case UP:
                    _combatMenu.previousOption();
                    break;
                case DOWN:
                    _combatMenu.nextOption();
                    break;
                case ENTER:
                    executePlayerAction(d);
                    break;
                default:
                    break;
            }
        } else {
            enemyTurn(d);
            _combatMenu.displayMessage("The enemy attack you!");
        }
    }

    private void executePlayerAction(GameData d) {
        String action = _combatMenu.getSelectedOption();
        switch (action) {
            case "Melee Attack":
                _combatMenu.displayMessage("You attack the enemy!");
                // Todo: calculate damage to enemy
                takeDamage(10, d);
                break;
            case "Cast Spell":
                _combatMenu.displayMessage("You cast a spell!");
                // Todo: calculate damage to enemy
                takeDamage(10, d);
                break;
            case "Use Item":
                _combatMenu.displayMessage("You tried to use an item, but failed!");
                break;
            case "Flee":
                _combatMenu.displayMessage("You really think you can flee?");
                break;
            default:
                break;
        }
        _playerTurn = false;
    }

    private void enemyTurn(GameData d) {
        attack(d.getPlayerData(), d);
        _playerTurn = true;
    }

    @Override
    public GameData update(GameData d) {
        if (!d.isPlayerLookingAt(this))
        {
            return d;
        }

        if (_isInCombat && d.getGameState().equals(GameState.BATTLE))
        {
            if (_combatMenu.getDisplayMessage())
            {
                return _combatMenu.update(d);
            }

            if (_hasFought)
            {

                d.getPlayerData().addXp(10000);

                switch (_combatType)
                {
                    case BOSS:
                    {
                        if (_health <= 0)
                        {
                            d.setGameState(GameState.LOAD_NEXT_LEVEL);
                        }
                        break;
                    }
                    case FINAL_BOSS:
                    {
                        if (_health <= 0)
                        {
                            d.setGameState(GameState.GAME_END);
                        }

                        break;
                    }
                    case NORMAL:
                    {
                        if (_health <= 0)
                        {
                            d.setGameState(GameState.RUNNING);
                            _toDelete = true;
                            d.addObjectToScene(new GameDialog("COMBAT_WON", "You won. You may proceed for now..."));
                        }
                        break;
                    }
                }
            }

            if (_health > 0)
            {
                _combatMenu.update(d);
            }

            if (_health <= 0) {

                _combatMenu.displayMessage("You defeated me! Arghhh...!");

                _hasFought = true;

                return d;
            }

            handleCombat(d);
        }

        if (d.getGameState() == GameState.TRADE && _type == GameCharacterType.TRADE)
        {
            if (d.getInput().equals(GameInput.YES))
            {
                d.setGameState(GameState.RUNNING);
                d.addObjectToScene(new GameDialog("TRADE_YES", "Smart... and that's something from someone like you..."));
                if (_tradeBuy)
                {
                    d.getPlayerData().removeItem(_itemToTrade.getItemType());
                    d.getPlayerData().addCoins(_itemToTrade.getValue());

                    _hasTraded = true;
                    return d;
                }
                else
                {
                    if (_itemToTrade.getValue() > d.getPlayerData().getCoins())
                    {
                        d.addObjectToScene(new GameDialog("TRADE_NO_COINS", "Go away from me, poor idiot!"));
                        _hasTraded = true;
                        return d;
                    }
                    else
                    {
                        if (_itemToTrade.getWeight() + d.getPlayerData().getInventoryWeight() > d.getPlayerData().getMaxWeight())
                        {
                            d.addObjectToScene(new GameDialog("TOO_MUCH_WEIGHT", "Poor bastard you cannot carry what you bought! I'm gonna keep the change, though!"));
                            d.getPlayerData().removeCoins(_itemToTrade.getValue());
                            _hasTraded = true;
                            return  d;
                        }
                        d.getPlayerData().addItem(_itemToTrade.getItemType());
                        d.getPlayerData().removeCoins(_itemToTrade.getValue());

                        _hasTraded = true;
                        return  d;
                    }
                }
            }
            else if (d.getInput().equals(GameInput.NO))
            {
                d.setGameState(GameState.RUNNING);
                d.addObjectToScene(new GameDialog("TRADE_NO", "Go away then! And in any case... you'll die soon!"));
                _hasTraded = true;
            }
        }

        if (!d.getGameState().equals(GameState.RUNNING))
        {
            return d;
        }

        if (!d.getInput().equals(GameInput.INSPECT))
        {
            return d;
        }

        switch (_type)
        {
            case TEXT_ONLY:
            {
                d.addObjectToScene(new GameDialog("TEXT_FROM_CHARACTER", _text));

                return d;
            }
            case GIFT:
            {
                if (_hasGifted)
                {
                    d.addObjectToScene(new GameDialog("GIFTED_ALREADY", "Sorry but that's all I've had! Try coming back later... if you are still alive..."));
                    return d;
                }

                d.addObjectToScene(new GameDialog("TEXT_FROM_CHARACTER", "Hey adventurer! Take this " + _itemToGift.getName() + "!"));
                d.getPlayerData().addItem(_itemToGift.getItemType());

                _hasGifted = true;

                return d;
            }
            case TRADE:
            {
                if (_hasTraded)
                {
                    d.addObjectToScene(new GameDialog("TRADE_ALREADY_DONE", "Too late kid, I've already made up my mind about that! Go away now!"));

                    return d;
                }

                Random rng = new Random();

                if (rng.nextDouble() > 0.5 && d.getPlayerData().getInventory().values().stream().anyMatch(x -> x > 0))
                {
                    // Tries to buy something

                    GameItemType itemToBuy = GameItemType.NONE;

                    while (itemToBuy == GameItemType.NONE || d.getPlayerData().getInventory().getOrDefault(itemToBuy, 0) == 0)
                    {
                        List<GameItemType> items = d.getPlayerData().getInventory().keySet().stream().toList();

                        itemToBuy = items.get(rng.nextInt(items.size()));
                    }

                    GameItem gameItem = GameConstants.getItem(itemToBuy);
                    _itemToTrade = gameItem;
                    _tradeBuy = true;
                    d.addObjectToScene(new GameTradeDialog("TRADE_DIALOG", "Hey man, would you mind trading me that " + gameItem.getName() + " for " + gameItem.getValue() + " coins? (YES / NO)"));
                }
                else
                {
                    // Tries to sell something

                    GameItemType itemToSell = (new GameItemType[] {GameItemType.IRON_ARMOR, GameItemType.CRYSTAL_WAND, GameItemType.HEALTH_POTION, GameItemType.LUCK_AMULET, GameItemType.IRON_SWORD})[rng.nextInt(5)];

                    GameItem gameItem = GameConstants.getItem(itemToSell);
                    _itemToTrade = gameItem;
                    _tradeBuy = false;
                    d.addObjectToScene(new GameTradeDialog("TRADE_DIALOG", "Hey man, I have an incredible " + gameItem.getName() + " for you! At a friendly price of just " + gameItem.getValue() + " coins, do you accept? (YES / NO)"));
                }

                break;
            }
            case COMBAT:
            {
                if (!_isInCombat && _health > 0)
                {
                    _isInCombat = true;
                    d.setGameState(GameState.BATTLE);
                }
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
