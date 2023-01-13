package Exe.Ex4.geo;

/**
 * This class represents a 2D circle in the plane.
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 *
 * @author boaz.benmoshe
 */
public class Circle2D implements GeoShapeable {
    private Point2D _center;
    private double _radius;

    /**
     * center radius constructor
     * @param cen center
     * @param rad radius
     */
    public Circle2D(Point2D cen, double rad) {
        this._center = new Point2D(cen);
        this._radius = rad;
    }
    /**
     * copy constructor
     */
    public Circle2D(Circle2D circ) {
        this._center = new Point2D(circ._center);
        this._radius = circ._radius;
    }

    /**
     * getter for radius
     * @return the radius of the circle
     */
    public double getRadius() {
        return this._radius;
    }
    public Point2D getCenter() {
        return this._center;
    }


    /**
     * to string method for Circle2D
     * @return center, radius format
     */
    @Override
    public String toString() {
        return _center.toString() + ", " + _radius;
    }

    /**
     * given a point return true/false whether the point inside the circle or not
     * @param ot - a query 2D point
     * @return true/false
     */
    @Override
    public boolean contains(Point2D ot) {
        double dist = ot.distance(this._center);
        return dist <= this._radius;
    }

    /**
     * return the area of the circle
     * PI*R**2
     * @return the area
     */
    @Override
    public double area() {
        return Math.PI * Math.pow(this._radius, 2);
    }

    /**
     * return the permieter of the circle
     * 2*PI*R
     * @return the perimeter
     */
    @Override
    public double perimeter() {
        return Math.PI * 2 * this._radius;
    }

    /**
     * given a vector move the center coordinates by this vector
     * @param vec - a vector from the 0,0
     */
    @Override
    public void move(Point2D vec) {
        _center.move(vec);
    }

    /**
     * @return return a copy of the point without memory dependencies
     */
    @Override
    public GeoShapeable copy() {
        return new Circle2D(this);
    }

    /**
     * @return return the 4 cardinal point of the circle
     */
    @Override
    public Point2D[] getPoints() {
        Point2D[] ans = new Point2D[5];
        ans[0] = new Point2D(this._center);
        ans[1] = new Point2D(ans[0].x(), ans[0].y() + this._radius);
        ans[2] = new Point2D(ans[0].x(), ans[0].y() - this._radius);
        ans[3] = new Point2D(ans[0].x() + this._radius, ans[0].y() );
        ans[4] = new Point2D(ans[0].x() - this._radius, ans[0].y() );
        return ans;
    }

    /**
     * given a reference point and a ratio move the center by this ratio
     * enhance the radius by the same ratio
     * @param center - center point from which the rescaling is being done.
     * @param ratio - the ratio of rescaling.
     */
    @Override
    public void scale(Point2D center, double ratio) {
        //////////add your code below ///////////
        this._center.scale(center, ratio);
        this._radius *= ratio;
        //////////////////////////////////////////
    }

    /**
     * given a pivot point and a degree rotate the circle around the pivot point
     * @param center - pivot point from which the rotation is being done.
     * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
     */
    @Override
    public void rotate(Point2D center, double angleDegrees) {
        //////////add your code below ///////////
        this._center.rotate(center, angleDegrees);
        //////////////////////////////////////////
    }

}
