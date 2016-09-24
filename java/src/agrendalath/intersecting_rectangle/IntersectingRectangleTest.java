package agrendalath.intersecting_rectangle;

import org.junit.Test;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class IntersectingRectangleTest {
    @Test
    public void testGetIntersectingRectangle() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getIntersectingRectangle = IntersectingRectangle.class.getDeclaredMethod("getIntersectingRectangle", Rectangle.class, Rectangle.class);
        getIntersectingRectangle.setAccessible(true);
        assertEquals(6L, getIntersectingRectangle.invoke(IntersectingRectangle.class,
                new Rectangle(new Point(0, 0), new Dimension(5, 4)),
                new Rectangle(new Point(2, 2), new Dimension(14, 5))
        ));
    }

    @Test
    public void testGetIntersectingRectangleNoIntersection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getIntersectingRectangle = IntersectingRectangle.class.getDeclaredMethod("getIntersectingRectangle", Rectangle.class, Rectangle.class);
        getIntersectingRectangle.setAccessible(true);
        assertEquals(0L, getIntersectingRectangle.invoke(IntersectingRectangle.class,
                new Rectangle(new Point(0, 0), new Dimension(1, 1)),
                new Rectangle(new Point(20, 20), new Dimension(1, 1))
        ));
    }
}
