package Exe.Ex4.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Polygon2DTest {

    private static Polygon2D poly;
    @BeforeEach
    void init(){
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
                new Point2D(0, -1),
        };
        poly = new Polygon2D(pts);
    }
    @Test
    void contains() {
        assertTrue(poly.contains(new Point2D(1, 0)));
        assertTrue(poly.contains(new Point2D(1.9, 0)));
        assertFalse(poly.contains(new Point2D(0, 0)));
    }

    @Test
    void area() {
        poly = new Polygon2D(new Point2D[] {
                new Point2D(0,4),
                new Point2D(3,-1),
                new Point2D(-2,-2),
                new Point2D(1,-1),
                });
        assertEquals(13, poly.area());
    }

    @Test
    void perimeter() {
        assertEquals(7.236, poly.perimeter(), 0.001);
    }

    @Test
    void move() {
        poly.move(new Point2D(1, 1));
        Point2D[] pts = {
                new Point2D(2, 2),
                new Point2D(3, 2),
                new Point2D(3, 0),
                new Point2D(1, 0),
        };
        assertArrayEquals(pts, poly.getPoints());
    }

    @Test
    void copy() {
        Polygon2D tempPoly = (Polygon2D) poly.copy();

        assertArrayEquals(tempPoly.getPoints(), poly.getPoints());

    }

    @Test
    void scale() {

        poly.scale(new Point2D(1, 1), 1.1);
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2.1, 1),
                new Point2D(2.1, -1.2),
                new Point2D(-0.1, -1.2),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = poly.getPoints()[i];
            assertTrue(p1.close2equals(p2));
        }
    }

    @Test
    void rotate() {
        poly.rotate(new Point2D(1, 1), 46);
//        System.out.println(Arrays.toString(poly.getPoints()));
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(1.694, 1.719),
                new Point2D(3.133, 0.33),
                new Point2D(1.744, -1.108),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = poly.getPoints()[i];
            assertTrue(p1.close2equals(p2, 0.001));
        }
    }

    @Test
    void getXcoord() {
        double[] xVal = {
                1,
                2,
                2,
                0,
        };

        assertArrayEquals(xVal, poly.getXcoord());
    }

    @Test
    void getYcoord() {
        double[] yVal = {
                1,
                1,
                -1,
                -1,
        };

        assertArrayEquals(yVal, poly.getYcoord());
    }

    @Test
    void getPoints() {
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
                new Point2D(0, -1),
        };

        assertArrayEquals(pts, poly.getPoints());

    }


    @Test
    void testToString() {
        assertEquals("1.0,1.0,2.0,1.0,2.0,-1.0,0.0,-1.0", poly.toString());
    }
}