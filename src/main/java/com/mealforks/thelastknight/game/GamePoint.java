package main.java.com.mealforks.thelastknight.game;

/**
 * Represents a point in the game world.
 */
public class GamePoint {

    /** Represents a null point. */
    public static final GamePoint NONE = new GamePoint(-1, -1);

    /** The x-coordinate of the point. */
    public int x;

    /** The y-coordinate of the point. */
    public int y;

    /**
     * Constructs a new GamePoint with the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public GamePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Computes the hash code of the point.
     *
     * @return The hash code of the point.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        return result;
    }

    /**
     * Checks if this point is equal to another object.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
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
