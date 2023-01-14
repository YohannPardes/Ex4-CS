package Exe.Ex4.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Triangle2DTest {
    private static Triangle2D tri;
    @BeforeEach
    void init(){

         Point2D p1 = new Point2D(1, 1);
         Point2D p2 = new Point2D(2, 1);
         Point2D p3 = new Point2D(2, -1);

        tri = new Triangle2D(p1, p2, p3);
    }
    @Test
    void get_p1() {
        assertEquals(new Point2D(1, 1), tri.get_p1());
    }

    @Test
    void get_p2() {
        assertEquals(new Point2D(2, 1), tri.get_p2());
    }

    @Test
    void get_p3() {
        assertEquals(new Point2D(2, -1), tri.get_p3());
    }

    @Test
    void contains() {
        assertFalse(tri.contains(new Point2D(1, 0)));
        assertTrue(tri.contains(new Point2D(1.9, 0)));
        assertFalse(tri.contains(new Point2D(1.49, 0)));
    }

    @Test
    void area() {
        assertEquals(1, tri.area());
    }

    @Test
    void perimeter() {
        assertEquals(5.236, tri.perimeter(), 0.001);
    }

    @Test
    void move() {
        tri.move(new Point2D(-1.1, 0.2));

        Point2D[] pts = {
                 new Point2D(-0.1, 1.2),
                 new Point2D(0.9, 1.2),
                 new Point2D(0.9, -0.8),
        };
        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = tri.getPoints()[i];
            assertTrue(p1.close2equals(p2));
        }
    }

    @Test
    void copy() {
        Triangle2D tempTriangle = (Triangle2D) tri.copy();
        assertArrayEquals(tempTriangle.getPoints(), tri.getPoints());

    }

    @Test
    void scale() {
        tri.scale(new Point2D(1, 1), 1.1);
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2.1, 1),
                new Point2D(2.1, -1.2),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = tri.getPoints()[i];
            assertTrue(p1.close2equals(p2));
        }
    }

    @Test
    void rotate() {
        tri.rotate(new Point2D(1, 1), 46);
//        System.out.println(Arrays.toString(tri.getPoints()));
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(1.694, 1.719),
                new Point2D(3.133, 0.33),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = tri.getPoints()[i];
            assertTrue(p1.close2equals(p2, 0.001));
        }
    }

    @Test
    void getXcoord() {
        double[] xVal = {
                1,
                2,
                2,
        };

        assertArrayEquals(xVal, tri.getXcoord());
    }

    @Test
    void getYcoord() {
        double[] yVal = {
                1,
                1,
                -1,
        };
        assertArrayEquals(yVal, tri.getYcoord());
    }


    @Test
    void getPoints() {
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
        };

        assertArrayEquals(pts, tri.getPoints());

    }

    @Test
    void testToString() {
        assertEquals("1.0,1.0,2.0,1.0,2.0,-1.0", tri.toString());
    }

}