package test;

import main.java.com.mealforks.thelastknight.game.GamePoint;
import org.junit.Test;
import static org.junit.Assert.*;

public class GamePointTest {

    @Test
    public void testHashCode() {
        GamePoint point1 = new GamePoint(10, 20);
        GamePoint point2 = new GamePoint(10, 20);
        assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    public void testEquals() {
        GamePoint point1 = new GamePoint(10, 20);
        GamePoint point2 = new GamePoint(10, 20);
        GamePoint point3 = new GamePoint(15, 25);

        assertTrue(point1.equals(point2));
        assertFalse(point1.equals(point3));
    }
}
