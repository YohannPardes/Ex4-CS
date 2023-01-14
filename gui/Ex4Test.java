package Exe.Ex4.gui;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Ex4Test {
    private static Ex4 myEx4 = Ex4.getInstance();

    @Test
    void initTest() {
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
                new Point2D(0, -1),
        };
        GeoShapeable poly = new Polygon2D(pts);

        GeoShapeable rect = new Rect2D(new Point2D[]{
                new Point2D(1.2, 1.6),
                new Point2D(0.4, 0.8),
                new Point2D(1.4, -0.2),
                new Point2D(2.2, 0.6),
        });

        GeoShapeable circ = new Circle2D(new Point2D(0,0), 5);

        ShapeCollection sc = new ShapeCollection();
        sc.add((new GUIShape(poly, false, Color.BLUE, 1)));
        sc.add((new GUIShape(circ, true, Color.YELLOW, 3)));
        sc.add((new GUIShape(rect, false, Color.WHITE, 2)));

        myEx4.init(sc);

        assertEquals(3, myEx4.getShape_Collection().size());

        assertEquals(sc.get(0).toString(), myEx4.getShape_Collection().get(0).toString());
        assertEquals(sc.get(1).toString(), myEx4.getShape_Collection().get(1).toString());
        assertEquals(sc.get(2).toString(), myEx4.getShape_Collection().get(2).toString());


    }

    @Test
    void getShape_Collection() {
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
                new Point2D(0, -1),
        };
        GeoShapeable poly = new Polygon2D(pts);

        GeoShapeable rect = new Rect2D(new Point2D[]{
                new Point2D(1.2, 1.6),
                new Point2D(0.4, 0.8),
                new Point2D(1.4, -0.2),
                new Point2D(2.2, 0.6),
        });

        GeoShapeable circ = new Circle2D(new Point2D(0,0), 5);

        ShapeCollection sc = new ShapeCollection();
        sc.add((new GUIShape(poly, false, Color.BLUE, 1)));
        sc.add((new GUIShape(circ, true, Color.YELLOW, 3)));
        sc.add((new GUIShape(rect, false, Color.WHITE, 2)));

        myEx4.init(sc);

        assertEquals(3, myEx4.getShape_Collection().size());

        assertEquals(sc.get(0).toString(), myEx4.getShape_Collection().get(0).toString());
        assertEquals(sc.get(1).toString(), myEx4.getShape_Collection().get(1).toString());
        assertEquals(sc.get(2).toString(), myEx4.getShape_Collection().get(2).toString());
    }

    @Test
    void getInfo() {
        Point2D[] pts = {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(2, -1),
                new Point2D(0, -1),
        };
        GeoShapeable poly = new Polygon2D(pts);

        GeoShapeable rect = new Rect2D(new Point2D[]{
                new Point2D(1.2, 1.6),
                new Point2D(0.4, 0.8),
                new Point2D(1.4, -0.2),
                new Point2D(2.2, 0.6),
        });

        GeoShapeable circ = new Circle2D(new Point2D(0,0), 5);

        ShapeCollection sc = new ShapeCollection();
        sc.add((new GUIShape(poly, false, Color.BLUE, 1)));
        sc.add((new GUIShape(circ, true, Color.YELLOW, 3)));
        sc.add((new GUIShape(rect, false, Color.WHITE, 2)));

        myEx4.init(sc);
        String ans = "";

        for (int i = 0; i<myEx4.getShape_Collection().size();i+=1) {
            if (i!=0){
                ans += "\n";
            }
            ans += myEx4.getShape_Collection().get(i);
        }
        assertEquals("GUIShape,-16776961,false,1,Polygon2D,1.0,1.0,2.0,1.0,2.0,-1.0,0.0,-1.0\n" +
                              "GUIShape,-256,true,3,Circle2D,0.0,0.0, 5.0\n" +
                            "GUIShape,-1,false,2,Rect2D,1.2,1.6,0.4,0.8,1.4,-0.2,2.2,0.6"
                , ans);
    }
}