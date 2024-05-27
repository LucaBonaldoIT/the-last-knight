package main.java.com.mealforks.thelastknight.game;

public class GameItem {
    private GameItemType _type;
    private int _weight;
    private int _value;

    public GameItem()
    {
        _type = GameItemType.NONE;
        _weight = 0;
        _value = 0;
    }

    public GameItemType getItemType()
    {
        return _type;
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
