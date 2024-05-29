package main.java.com.mealforks.thelastknight.game;

import java.awt.*;

public interface GameObject {
    public String getId();
    public GamePoint getCoordinates();
    public int getIndex();
    public void render(Graphics g);
    public GameData update(GameData d);
    public boolean toDelete();
}
