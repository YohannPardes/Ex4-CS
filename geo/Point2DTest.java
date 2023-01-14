package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2DTest {
    private static Point2D P;
    @BeforeEach
    void init(){
        P = new Point2D(2.5,1.2);
    }
//    @Test
    void x() {
        assertEquals(2.5, P.x());

    }

    @Test
    void y() {
        assertEquals(1.2, P.y());
    }

    @Test
    void ix() {
        assertEquals(2, P.ix());
    }

    @Test
    void iy() {
        assertEquals(1, P.iy());
    }

    @Test
    void add() {
        Point2D newP = P.add(new Point2D(2, 3));
        assertEquals(4.5, newP.x());
        assertEquals(4.2, newP.y());
    }

    @Test
    void testToString() {
        assertEquals("2.5,1.2", P.toString());
    }

    @Test
    void distance() {
        assertEquals(2.773, P.distance(), 0.001);
    }

    @Test
    void testDistance() {
        assertEquals(1.4142, P.distance(new Point2D(1.5, 0.2)), 0.001);
    }

    @Test
    void testEquals() {
        assertTrue(new Point2D(2.5, 1.2).equals(P));
        assertFalse(new Point2D(2.2, 0.2).equals(P));
    }

    @Test
    void close2equals() {
        assertTrue(new Point2D(2.48, 1.18).close2equals(P, 0.1));
        assertFalse(new Point2D(2.4, 1.15).close2equals(P, 0.0001));
    }

    @Test
    void testClose2equals() {
        assertTrue(new Point2D(2.5, 1.20000001).close2equals(P));
        assertFalse(new Point2D(2.4, 1.15).close2equals(P));
    }

    @Test
    void vector() {
        assertEquals(P, Point2D.ORIGIN.vector(P));
    }

    @Test
    void move() {
        P.move(new Point2D(0.5, 0.8));
        assertEquals(new Point2D(3, 2), Point2D.ORIGIN.vector(P));
    }

    @Test
    void scale() {
        P.scale(Point2D.ORIGIN, 1.5);
        assertTrue(new Point2D(3.75, 1.799).close2equals(P, 0.01));
    }

    @Test
    void rotate() {
        P.rotate(P, 120);
        assertEquals(P.x(), P.x(), Ex4_Const.EPS);
        assertEquals(P.y(), P.y(), Ex4_Const.EPS);
    }
}