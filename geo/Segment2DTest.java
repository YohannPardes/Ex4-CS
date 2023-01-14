package Exe.Ex4.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment2DTest {
    private static Segment2D seg;
    @BeforeEach
    void init(){
        seg = new Segment2D(new Point2D(0.1, 1.3), new Point2D(-0.8, 0.7));
    }
    @Test
    void get_p1() {
        assertEquals(new Point2D(0.1, 1.3), seg.get_p1());
    }

    @Test
    void get_p2() {
        assertEquals(new Point2D(-0.8, 0.7), seg.get_p2());
    }

    @Test
    void contains() {
        assertTrue(seg.contains(new Point2D(-0.5, 0.9)));
        assertFalse(seg.contains(new Point2D(0, 0)));
    }

    @Test
    void area() {
        assertEquals(0, seg.area());
    }

    @Test
    void perimeter() {
        assertEquals(1.0816, seg.perimeter(), 0.001);
    }

    @Test
    void move() {
        seg.move(new Point2D(2.3, -0.6));
        Point2D[] pts = new Point2D[] {
                            new Point2D(2.4,0.70),
                            new Point2D(1.5,0.1)};

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = seg.getPoints()[i];
            assertTrue(p1.close2equals(p2));
        }
    }

    @Test
    void copy() {
        Segment2D tempSeg = (Segment2D) seg.copy();
        assertArrayEquals(tempSeg.getPoints(), seg.getPoints());
    }

    @Test
    void scale() {

        seg.scale(new Point2D(1, 1), 1.1);
        Point2D[] pts = {
                new Point2D(0.01,1.33),
                new Point2D(-0.98,0.67),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = seg.getPoints()[i];
            assertTrue(p1.close2equals(p2));
        }
    }

    @Test
    void rotate() {
        seg.rotate(new Point2D(1, 1), 23.5);
        Point2D[] pts = {
                new Point2D(0.055,0.916),
                new Point2D(-0.531,0.007),
        };

        for (int i =0; i<pts.length; i+=1){

            Point2D p1 = pts[i];
            Point2D p2 = seg.getPoints()[i];
            System.out.println(p1+" "+p2);
            assertTrue(p1.close2equals(p2, 0.001));
        }
    }

    @Test
    void getPoints() {
        Point2D[] pts = {
                new Point2D(0.1, 1.3),
                new Point2D(-0.8, 0.7)
        };

        assertArrayEquals(pts, seg.getPoints());

    }

    @Test
    void testToString() {
        assertEquals("0.1,1.3,-0.8,0.7", seg.toString());
    }
}