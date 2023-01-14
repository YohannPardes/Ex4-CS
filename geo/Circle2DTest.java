package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class Circle2DTest {

    private static Circle2D circ;
    @BeforeEach
    void init(){
        circ = new Circle2D(new Point2D(0,0), 5);
    }

    @org.junit.jupiter.api.Test
    void getRadius() {
        assertEquals(5, circ.getRadius());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("0.0,0.0, 5.0", circ.toString());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        assertTrue(circ.contains(new Point2D(0, 0)));
        assertTrue(circ.contains(new Point2D(4, 1)));
        assertFalse(circ.contains(new Point2D(-1, 6)));
    }

    @org.junit.jupiter.api.Test
    void area() {
        assertEquals(Math.PI * Math.pow(circ.getRadius(), 2), circ.area());
    }

    @org.junit.jupiter.api.Test
    void perimeter() {
        assertEquals(2 * Math.PI * circ.getRadius(), circ.perimeter());
    }

    @org.junit.jupiter.api.Test
    void move() {
        circ.move(new Point2D(2, 3));
        assertEquals(new Point2D(2, 3), circ.getCenter());
    }

    @org.junit.jupiter.api.Test
    void copy() {
        Circle2D newCirc = (Circle2D)circ.copy();
        assertEquals(circ.getCenter(), newCirc.getCenter());
        assertEquals(circ.getRadius(), newCirc.getRadius());
        newCirc.move(new Point2D(2, 3));
        assertNotEquals(circ.getCenter(), newCirc.getCenter());
        assertEquals(circ.getRadius(), newCirc.getRadius());
    }

    @org.junit.jupiter.api.Test
    void getPoints() {
                Point2D[] expectedPts = {
                        new Point2D(0, 0),
                        new Point2D(0, 5),
                        new Point2D(0, -5),
                        new Point2D(5,0),
                        new Point2D(-5, 0)
                };
        assertArrayEquals(expectedPts ,circ.getPoints());
    }

    @org.junit.jupiter.api.Test
    void scale() {
        circ.scale(new Point2D(1, 1), 0.9);

        assertEquals(4.5, circ.getRadius());
        assertEquals(0.1, circ.getCenter().x(), Ex4_Const.EPS);
        assertEquals(0.1, circ.getCenter().y(), Ex4_Const.EPS);
    }

    @org.junit.jupiter.api.Test
    void rotate() {
        circ.rotate(new Point2D(1, 1), 180);

        assertEquals(2, circ.getCenter().x(), Ex4_Const.EPS);
        assertEquals(2, circ.getCenter().y(), Ex4_Const.EPS);
    }
}