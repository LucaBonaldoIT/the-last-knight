package main.java.com.mealforks.thelastknight.game;

public class GamePoint {

    public static final GamePoint NONE = new GamePoint(-1, -1);

    public GamePoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GamePoint other = (GamePoint) obj;
        if (this.x != other.x)
            return false;
        if (this.y != other.y)
            return false;
        return true;
    }

}
