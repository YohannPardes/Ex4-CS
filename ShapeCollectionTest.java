package Exe.Ex4;

import Exe.Ex4.geo.*;
import Exe.Ex4.gui.Ex4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {

    private static ShapeCollection SC;

    @BeforeEach
    void init() {

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

        SC = new ShapeCollection();
        SC.add((new GUIShape(poly, false, Color.BLUE, 1)));
        SC.add((new GUIShape(circ, true, Color.YELLOW, 3)));
        SC.add((new GUIShape(rect, false, Color.WHITE, 2)));

    }
    @Test
    void get() {
        assertEquals("GUIShape,-1,false,2,Rect2D,1.2,1.6,0.4,0.8,1.4,-0.2,2.2,0.6", SC.get(2).toString());
        assertNotEquals(SC.get(0), SC.get(1));
    }

    @Test
    void size() {
        assertEquals(3, SC.size());

    }

    @Test
    void removeElementAt() {
        SC.removeElementAt(0);
        assertEquals(2, SC.size());
        assertEquals("GUIShape,-1,false,2,Rect2D,1.2,1.6,0.4,0.8,1.4,-0.2,2.2,0.6", SC.get(1).toString());
    }

    @Test
    void addAt() {
        GeoShapeable seg = new Segment2D(new Point2D(0.1, 1.3), new Point2D(-0.8, 0.7));
        SC.addAt((new GUIShape(seg, false, Color.RED, 4)), 2);
        assertEquals("GUIShape,-65536,false,4,Segment2D,0.1,1.3,-0.8,0.7", SC.get(2).toString());
        assertEquals(4, SC.size());
    }

    @Test
    void add() {
        GeoShapeable seg = new Segment2D(new Point2D(0.1, 1.3), new Point2D(-0.8, 0.7));
        SC.add((new GUIShape(seg, false, Color.RED, 4)));
        assertEquals("GUIShape,-65536,false,4,Segment2D,0.1,1.3,-0.8,0.7", SC.get(3).toString());
        assertEquals(4, SC.size());
    }

    @Test
    void copy() {
        ShapeCollection tempSC = (ShapeCollection) SC.copy();
        assertEquals(SC.get(0).toString(), tempSC.get(0).toString());
        assertEquals(SC.get(1).toString(), tempSC.get(1).toString());
        assertEquals(SC.get(2).toString(), tempSC.get(2).toString());

        GeoShapeable seg = new Segment2D(new Point2D(0.1, 1.3), new Point2D(-0.8, 0.7));
        SC.addAt((new GUIShape(seg, false, Color.RED, 4)), 2);
        assertNotEquals(SC.get(2).toString(), tempSC.get(2).toString());

    }

    @Test
    void sort() {

        ShapeComp comp1 = new ShapeComp(Ex4_Const.Sort_By_toString);
        ShapeComp comp2 = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
        ShapeComp comp3 = new ShapeComp(Ex4_Const.Sort_By_Area);
        ShapeComp comp4 = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
        ShapeComp comp5 = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
        ShapeComp comp6 = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
        ShapeComp comp7 = new ShapeComp(Ex4_Const.Sort_By_Tag);
        ShapeComp comp8 = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);

        ShapeCollection referenceCollection = (ShapeCollection) SC.copy();

        // Sort_By_toString
        SC.sort(comp1);
        assertEquals(SC.get(0).toString(), referenceCollection.get(2).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(0).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(1).toString());

        // Sort_By_Anti_toString
        SC.sort(comp2);
        assertEquals(SC.get(0).toString(), referenceCollection.get(1).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(0).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(2).toString());

        // Sort_By_Area
        SC.sort(comp3);
        assertEquals(SC.get(0).toString(), referenceCollection.get(0).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(2).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(1).toString());

        // Sort_By_Anti_Area
        SC.sort(comp4);
        assertEquals(SC.get(0).toString(), referenceCollection.get(1).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(2).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(0).toString());

        // Sort_By_Perimeter
        SC.sort(comp5);
        assertEquals(SC.get(0).toString(), referenceCollection.get(2).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(0).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(1).toString());

        // Sort_By_Anti_Perimeter
        SC.sort(comp6);
        assertEquals(SC.get(0).toString(), referenceCollection.get(1).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(0).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(2).toString());

        // Sort_By_Tag
        SC.sort(comp7);
        assertEquals(SC.get(0).toString(), referenceCollection.get(0).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(2).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(1).toString());

        // Sort_By_Anti_Tag
        SC.sort(comp8);
        assertEquals(SC.get(0).toString(), referenceCollection.get(1).toString());
        assertEquals(SC.get(1).toString(), referenceCollection.get(2).toString());
        assertEquals(SC.get(2).toString(), referenceCollection.get(0).toString());

    }

    @Test
    void removeAll() {
        SC.removeAll();
        assertEquals(0, SC.size());
    }

    @Test
    void save() {
        ShapeCollection tempCollection = (ShapeCollection) SC.copy();
        tempCollection.removeAll();
        SC.save("mdr.txt");

        tempCollection.load("mdr.txt");

        assertEquals(SC.get(0).toString(), tempCollection.get(0).toString());
        assertEquals(SC.get(1).toString(), tempCollection.get(1).toString());
        assertEquals(SC.get(2).toString(), tempCollection.get(2).toString());
    }

    @Test
    void load() {
        ShapeCollection tempCollection = (ShapeCollection) SC.copy();
        tempCollection.removeAll();
        SC.save("mdr.txt");

        tempCollection.load("mdr.txt");

        assertEquals(SC.get(0).toString(), tempCollection.get(0).toString());
        assertEquals(SC.get(1).toString(), tempCollection.get(1).toString());
        assertEquals(SC.get(2).toString(), tempCollection.get(2).toString());
    }

    @Test
    void getBoundingBox() {
        Point2D[] pts = {
                new Point2D(5,5),
                new Point2D(-5,5),
                new Point2D(-5, -5),
                new Point2D(5,-5),
        };
        assertArrayEquals(pts, SC.getBoundingBox().getPoints());
    }

    @Test
    void testToString() {
        assertEquals("GUIShape,-16776961,false,1,Polygon2D,1.0,1.0,2.0,1.0,2.0,-1.0,0.0,-1.0" +
                "GUIShape,-256,true,3,Circle2D,0.0,0.0, 5.0" +
                "GUIShape,-1,false,2,Rect2D,1.2,1.6,0.4,0.8,1.4,-0.2,2.2,0.6",
                SC.toString());
    }
}