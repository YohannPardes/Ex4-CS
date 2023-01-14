package Exe.Ex4.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void isInTest() {
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
                new Point2D(0, -1),
        };

        Polygon2D poly = new Polygon2D(pts);

        assertTrue(Utils.isIn(poly.getPoints(), new Point2D(1, 0)));
        assertTrue(Utils.isIn(poly.getPoints(), new Point2D(1.9, 0)));
        assertFalse(Utils.isIn(poly.getPoints(), new Point2D(0, 0)));

    }

    @Test
    void calcAreaTest() {

        Polygon2D poly = new Polygon2D(new Point2D[] {
                new Point2D(0,4),
                new Point2D(3,-1),
                new Point2D(-2,-2),
                new Point2D(1,-1),
        });
        assertEquals(13, Utils.calcArea(poly.getPoints()));
    }

    @Test
    void clockwiseOrder() {

        Polygon2D poly = new Polygon2D(new Point2D[] {
                new Point2D(1,-1),
                new Point2D(3,-1),
                new Point2D(-2,-2),
                new Point2D(0,4),
        });

        Point2D[] pts = new Point2D[] {
                new Point2D(1,-1),
                new Point2D(0,4),
                new Point2D(-2,-2),
                new Point2D(3,-1),
        };

        assertArrayEquals(poly.getPoints(), Utils.clockwiseOrder(pts));
    }

    @Test
    void calcAngle() {
        assertEquals(4.933 ,Utils.calcAngle(new Point2D(2.3, -1.3), new Point2D(1.2, 3.6)), 0.001);
        assertEquals(2.067 ,Utils.calcAngle(new Point2D(0.5, -1.3), new Point2D(-0.8, 1.1)), 0.001);
    }

    @Test
    void calcPerimeter() {
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
                new Point2D(0, -1),
        };

        Polygon2D poly = new Polygon2D(pts);
        assertEquals(7.236, Utils.calcPerimeter(poly.getPoints()), 0.001);

    }
}