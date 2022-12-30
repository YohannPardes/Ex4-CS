package Exe.Ex4.geo;

public class Utils {

    boolean isIn(GeoShapeable shape, Point2D checkedP) {

        Point2D[] pts = shape.getPoints();
        int nbVertices = pts.length;

        boolean c = false;
        int i, j;
        for (i = 0, j = nbVertices-1; i < nbVertices; j = i++) {
            if ( ((pts[i].y()> checkedP.y()) != (pts[j].y()> checkedP.y())) &&
                    (checkedP.x() < (pts[j].x()-pts[i].x()) * (checkedP.y()-pts[i].y()) / (pts[j].y()-pts[i].y()) + pts[i].x()) )
                c = !c;
        }
        return c;
    }
}
