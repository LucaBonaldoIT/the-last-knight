package main.java.com.mealforks.thelastknight.game;

/**
 * Represents an item in the game with a type, weight, value, and name.
 */
public class GameItem {
    private GameItemType _type;
    private int _weight;
    private int _value;
    private String _name;

    /**
     * Constructs a default GameItem object with no type, zero weight, zero value, and a default name.
     */
    public GameItem() {
        _type = GameItemType.NONE;
        _weight = 0;
        _value = 0;
        _name = "item_name";
    }

    /**
     * Constructs a GameItem object with specified type, weight, value, and name.
     *
     * @param type the type of the game item.
     * @param weight the weight of the game item.
     * @param value the value of the game item.
     * @param name the name of the game item.
     */
    public GameItem(GameItemType type, int weight, int value, String name) {
        _type = type;
        _weight = weight;
        _value = value;
        _name = name;
    }

    /**
     * Returns the type of the game item.
     *
     * @return the type of the game item.
     */
    public GameItemType getItemType() {
        return _type;
    }

    /**
     * Returns the name of the game item.
     *
     * @return the name of the game item.
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns the weight of the game item.
     *
     * @return the weight of the game item.
     */
    public int getWeight() {
        return _weight;
    }

    /**
     * Returns the value of the game item.
     *
     * @return the value of the game item.
     */
    public int getValue() {
        return _value;
    }
}
