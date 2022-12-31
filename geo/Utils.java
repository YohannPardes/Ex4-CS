package Exe.Ex4.geo;

import java.util.Comparator;

public class Utils {

    /**
     * https://wrfranklin.org/Research/Short_Notes/pnpoly.html
     * @param pts - the pts forming the polygon
     * @param checkedP - the point that is being checked
     * @return true or false accordingly to if the point is inside the shape or not
     */
    public static boolean isIn(Point2D[] pts, Point2D checkedP) {
        int nbVertices = pts.length;

        boolean c = false;
        int i, j;
        for (i = 0, j = nbVertices-1; i < nbVertices; j = i++) {
            if ( ((pts[i].y()> checkedP.y()) != (pts[j].y()> checkedP.y())) &&
            (checkedP.x() < (pts[j].x()-pts[i].x()) * (checkedP.y()-pts[i].y()) / (pts[j].y()-pts[i].y()) + pts[i].x()))
                c = !c;
        }
        return c;
    }

    /**
     * https://www.themathdoctors.org/polygon-coordinates-and-areas/#:~:text=If%20the%20points%20are%20(x1,right%20and%20divide%20by%202.)
     * @param pts
     * @return
     */
    public static double calcArea(Point2D[] pts){
        //sorting to clockwise order
        pts = clockwiseOrder(pts);

       double area = 0;
        for (int i = 0; i < pts.length; i++) {
            try {
                area += pts[i].x() * pts[i + 1].y() - pts[i + 1].x() * pts[i].y();
            }
            catch (IndexOutOfBoundsException e) {
                area += pts[i].x() * pts[0].y() - pts[0].x() * pts[i].y();
            }

        }
        area = 0.5*Math.abs(area);

        return area;
    }

    /**
     * Given a set of points return the set sorted clockwise by the angle (from vector (1, 0))
     * @param pointSet the given set of points
     * @return sortedSet the sorted set
     */
    public static Point2D[] clockwiseOrder(Point2D[] pointSet){

        Point2D center = pointSet[0];
        int n = pointSet.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (calcAngle(center, pointSet[j]) > calcAngle(center, pointSet[j + 1])) {
                    Point2D temp = new Point2D(pointSet[j]);
                    pointSet[j] = pointSet[j + 1];
                    pointSet[j + 1] = temp;
                }
        return pointSet;
    }

    /**
     * This function calculate the angle from the origin of a point
     * @param p
     * @return
     */
    public static double calcAngle(Point2D center, Point2D p){

        double dx = p.x() - center.x();
        double dy = p.y() - center.y();

        // the distance between the clicked point and the actual point
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double angle = Math.atan(dy/dx);
        if (p.x()<0){
            angle += Math.PI;
        }
        if (angle < 0){
            angle += 2*Math.PI;
        }

        return angle;
}

    public static double calcPerimeter(Point2D[] pointSet){

        double perim = 0;
        for (int i = 0; i < pointSet.length; i++) {
            try {
                perim += Math.sqrt(Math.pow(pointSet[i].x()-pointSet[i + 1].x(), 2) + Math.pow(pointSet[i].x()-pointSet[i + 1].x(), 2));
            }
            catch (IndexOutOfBoundsException e) {
                perim += Math.sqrt(Math.pow(pointSet[i].x()-pointSet[0].x(), 2) + Math.pow(pointSet[i].x()-pointSet[0].x(), 2));            }

        }
        return perim;
    }
}
