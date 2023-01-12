package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	private Point2D[] _pts;

	/**
	 * given a set of 4 points create a Rect2D
	 * @param pts
	 */
	public Rect2D(Point2D [] pts){
		if (pts.length != 4){
			throw new NumberFormatException("Rect is expecting 4 points but"+ pts.length+"were given");
		}
		this._pts = pts;
	}

	/**
	 * copy constructor
	 * @param rect
	 */
	public Rect2D(Rect2D rect){
		this._pts = new Point2D[4];
		for (int i = 0; i <rect._pts.length; i++) {
			this._pts[i] = new Point2D(rect._pts[i]);
		}
	}

	/**
	 * given 2 points create a rect that is (parallel to the axis)
	 * @param p1
	 * @param p2
	 */
	public Rect2D(Point2D p1, Point2D p2){
		this._pts = new Point2D[4];

		this._pts[0] = p1;
		this._pts[1] = new Point2D(p2.x(), p1.y());
		this._pts[2] = new Point2D(p2.x(), p2.y());
		this._pts[3] = new Point2D(p1.x(), p2.y());
	}

	/**
	 * @return - the x coordinates of the 4 points as an array
	 */
	public double[] getXcoord(){
		double[] Xcoor = new double[4];

		for (int i = 0; i < this._pts.length; i++) {
			Xcoor[i] = this._pts[i].x();
		}
		return Xcoor;
	}
	/**
	 * @return - the y coordinates of the 4 points as an array
	 */
	public double[] getYcoord(){
		double[] Ycoor = new double[4];

		for (int i = 0; i < this._pts.length; i++) {
			Ycoor[i] = this._pts[i].y();
		}

		return Ycoor;
	}
	/**
	 * given a point return true/false whether the point inside the shape or not
	 * @param ot - a query 2D point
	 * @return true/false
	 */
	@Override
	public boolean contains(Point2D ot) {
		return Utils.isIn(this.getPoints(), ot);
	}
	/**
	 * return the area of the rectangle
	 * @return the area
	 */
	@Override
	public double area() {
		return Utils.calcArea(this.getPoints());
	}
	/**
	 * return the perimeter of the rectangle
	 * @return the perimeter
	 */
	@Override
	public double perimeter() {
		return Utils.calcPerimeter(this.getPoints());
	}
	/**
	 * given a vector move the 4 vertex coordinates by this vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void move(Point2D vec) {
		for (Point2D p : this.getPoints()){
			p.move(vec);
		}
	}
	/**
	 * @return return a copy of the point without memory dependencies
	 */
	@Override
	public GeoShapeable copy() {
		return new Rect2D(this);
	}
	/**
	 * given a reference point and a ratio move the 4 vertex by this ratio
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point2D center, double ratio) {
		for (Point2D p : this.getPoints()){
			p.scale(center, ratio);
		}
	}
	/**
	 * given a pivot point and a degree rotate the rectange around the pivot point
	 * @param center - pivot point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (Point2D p : this.getPoints()){
			p.rotate(center, angleDegrees);
		}
	}
	/**
	 * @return return the 4 vertex point of the rectangle
	 */
	@Override
	public Point2D[] getPoints() {
		return this._pts;
	}
	/**
	 * to string method for Rect2D
	 * @return 4 points as an array
	 */
	@Override
	public String toString(){
		String ans = "";

		for (Point2D p : this._pts){
			ans += p.toString();
			ans += ",";
		}
		return ans.substring(0, ans.length()-1);
	}
}