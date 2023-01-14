package Exe.Ex4.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect2DTest {


    private static Rect2D rect;
    @BeforeEach
    void init (){
        rect = new Rect2D(new Point2D[]{
                new Point2D(1.2, 1.6),
                new Point2D(0.4, 0.8),
                new Point2D(1.4, -0.2),
                new Point2D(2.2, 0.6),
        });
    }
    @Test
    void getXcoord() {
        double[] xVal = {
                1.2,
                0.4,
                1.4,
                2.2,
        };

        assertArrayEquals(xVal, rect.getXcoord());
    }

    @Test
    void getYcoord() {
        double[] xVal = {
                1.6,
                0.8,
                -0.2,
                0.6,
        };

        assertArrayEquals(xVal, rect.getYcoord());
    }

    @Test
    void contains() {

        assertTrue(rect.contains(new Point2D(1.4, 0.2)));
        assertTrue(rect.contains(new Point2D(1.1, 1.1)));
        assertFalse(rect.contains(new Point2D(0.8, 0.2)));
    }

    @Test
    void area() {
        assertEquals(1.6, rect.area(), 0.001);
    }

    @Test
    void perimeter() {
        assertEquals(5.0909, rect.perimeter(), 0.001);

    }

    @Test
    void move() {
        rect.move(new Point2D(2, 1));
        Point2D[] pts = {
                new Point2D(3.2, 2.6),
                new Point2D(2.4, 1.8),
                new Point2D(3.4, 0.8),
                new Point2D(4.2, 1.6),
        };
        assertArrayEquals(pts, rect.getPoints());
    }

    @Test
    void copy() {
        Rect2D tempRect = (Rect2D) rect.copy();
        assertArrayEquals(tempRect.getPoints(), rect.getPoints());
    }

    @Test
    void scale() {
        rect.scale(new Point2D(1, 1), 1.1);
        Point2D[] pts = {
                new Point2D(1.22,1.66),
                new Point2D(0.339,0.78),
                new Point2D(1.44,-0.32),
                new Point2D(2.32,0.559),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = rect.getPoints()[i];
            assertTrue(p1.close2equals(p2, 0.001));
        }
    }

    @Test
    void rotate() {
        rect.rotate(new Point2D(1, 1), 29);
//        System.out.println(Arrays.toString(rect.getPoints()));
        Point2D[] pts = {
                new Point2D(0.884,1.621),
                new Point2D(0.572,0.534),
                new Point2D(1.931,0.144),
                new Point2D(2.243,1.2319),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = rect.getPoints()[i];
            System.out.println(p1+" "+p2);
            assertTrue(p1.close2equals(p2, 0.001));
        }
    }

    @Test
    void getPoints() {
        Point2D[] pts = {
                new Point2D(1.2, 1.6),
                new Point2D(0.4, 0.8),
                new Point2D(1.4, -0.2),
                new Point2D(2.2, 0.6),
        };

        assertArrayEquals(pts, rect.getPoints());

    }

    @Test
    void testToString() {
        assertEquals("1.2,1.6,0.4,0.8,1.4,-0.2,2.2,0.6", rect.toString());
    }
}