package Exe.Ex4;

import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GUIShapeTest {

    public static Polygon2D poly = new Polygon2D(new Point2D[]{
            new Point2D(1, 1),
            new Point2D(2, 1),
            new Point2D(2, -1),
            new Point2D(0, -1)
    });

    public static Rect2D rect = new Rect2D(new Point2D[]{
            new Point2D(1.2, 1.6),
            new Point2D(0.4, 0.8),
            new Point2D(1.4, -0.2),
            new Point2D(2.2, 0.6),
    });

    public static Circle2D circ = new Circle2D(new Point2D(0,0), 5);

    @Test
    void testGetShape() {// The segment that was inserted was what we got.
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        assertEquals(shape.getShape().toString(), rect.toString());
    }

    @Test
    void isFilled() {
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        assertNotEquals(true, shape.isFilled());
        GUIShape shape2 = new GUIShape(circ, true, Color.BLACK, 1);
        assertEquals(true, shape2.isFilled());
    }

    @Test
    void setFilled() {
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        assertNotEquals(true, shape.isFilled());
        shape.setFilled(true);
        assertEquals(true, shape.isFilled());
    }

    @Test
    void getColor() {
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        assertEquals(Color.BLACK, shape.getColor());
    }

    @Test
    void setColor() {
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        assertEquals(Color.BLACK, shape.getColor());
        shape.setColor(Color.YELLOW);
        assertEquals(Color.YELLOW, shape.getColor());
        assertNotEquals(Color.BLACK, shape.getColor());

    }

    @Test
    void getTag() {
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        assertEquals(1, shape.getTag());
    }

    @Test
    void setTag() {
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        assertNotEquals(4, shape.getTag());
        shape.setTag(4);
        assertEquals(4, shape.getTag());
    }

    @Test
    void copy() {
        GUIShape shape = new GUIShape(rect, false, Color.BLACK, 1);
        GUIShape shape2 = (GUIShape) shape.copy();

        assertEquals(shape.toString(), shape2.toString());

        shape2.setTag(6);
        assertNotEquals(shape.toString(), shape2.toString());
    }

    @Test
    void testToString() {
        GUIShape shape = new GUIShape(circ, false, Color.BLACK, 1);
        assertEquals("GUIShape,-16777216,false,1,Circle2D,0.0,0.0, 5.0", shape.toString());

    }

    @Test
    void isSelected() {
        GUIShape shape = new GUIShape(circ, false, Color.BLACK, 1);
        assertFalse(shape.isSelected());
    }

    @Test
    void setSelected() {
        GUIShape shape = new GUIShape(poly, false, Color.BLACK, 1);
        assertFalse(shape.isSelected());
        shape.setSelected(true);
        assertTrue(shape.isSelected());
        shape.setSelected(false);
        assertFalse(shape.isSelected());
    }

    @Test
    void setShape() {
        GUIShape shape = new GUIShape(poly, false, Color.BLACK, 1);
        shape.setShape((GeoShapeable) circ);

        assertEquals("GUIShape,-16777216,false,1,Circle2D,0.0,0.0, 5.0", shape.toString());
    }
}