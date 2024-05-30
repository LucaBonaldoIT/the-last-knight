package main.java.com.mealforks.thelastknight.game;

public class GameItem {
    private GameItemType _type;
    private int _weight;
    private int _value;

    private String _name;

    public GameItem()
    {
        _type = GameItemType.NONE;
        _weight = 0;
        _value = 0;
        _name = "item_name";
    }

    public GameItem(GameItemType type, int weight, int value, String name)
    {
        _type = type;
        _weight = weight;
        _value = value;
        _name = name;
    }

    public GameItemType getItemType()
    {
        return _type;
    }

    public String getName()
    {
        return _name;
    }

    public int getWeight()
    {
        return _weight;
    }

    public int getValue()
    {
        return _value;
    }
}
