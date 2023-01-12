package Exe.Ex4.geo;


import Exe.Ex4.Ex4_Const;
import Exe.Ex4.gui.Ex4;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{

	private Point2D _p1;
	private Point2D _p2;
	/**
	 * given 2 points create a Segment2D
	 * @param p1
	 * @param p2
	 */
	public Segment2D(Point2D p1, Point2D p2){
		this._p1 = p1;
		this._p2 = p2;
	}
	/**
	 * copy constructor
	 * @param seg
	 */
	public Segment2D(Segment2D seg){
		this._p1 = new Point2D(seg._p1);
		this._p2 = new Point2D(seg._p2);
	}

	/**
	 * @return the first point of the segment
	 */
	public Point2D get_p1(){
		return this._p1;
	}
	/**
	 * @return the first point of the segment
	 */
	public Point2D get_p2(){
		return this._p2;
	}

	/**
	 * Return true of false if the point lay on the line function of the segment.
	 * @param ot - a query 2D point
	 * @return boolean - true if contain false if NOT contain.
	 */
	@Override
	public boolean contains(Point2D ot) {
		double minX;
		double maxX;

		if (this._p1.x()<this._p2.x()){
			minX = this._p1.x();
			maxX = this._p2.x();
		}
		else {
			minX = this._p2.x();
			maxX = this._p1.x();
		}

		// if the point is on the same x range
		if (ot.x()>= minX && ot.x()<=maxX) {

			//calculating the line function
			double dx = this._p2.x() - this._p1.x();
			double dy = this._p2.y() - this._p1.y();
			double m = dy / dx;
			double c = this._p1.y() - m * this._p1.x();

			//if the point lay on the line function
			if (Math.abs(ot.y() - (ot.x()*m + c)) <= 0.03){
				return true;
			}
		}
		return false;
	}

	/**
	 * The area of a segment is always 0.
	 * @return 0
	 */
	@Override
	public double area() {
		return 0;
	}

	/**
	 * Compute the euclidian distance between the two segment edges.
	 * @return distance as a double.
	 */
	@Override
	public double perimeter() {
		double dxSqrd = Math.pow(this._p2.x() - this._p1.x(), 2);
		double dySqrd = Math.pow(this._p2.y() - this._p1.y(), 2);

		return Math.sqrt(dxSqrd + dySqrd);
	}
	/**
	 * given a vector move the 4 vertex coordinates by this vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void move(Point2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
	}
	/**
	 * @return return a copy of the point without memory dependencies
	 */
	@Override
	public GeoShapeable copy() {

		return new Segment2D(new Point2D(this._p1), new Point2D(this._p2));
	}
	/**
	 * given a reference point and a ratio move the center by this ratio
	 * enhance the radius by the same ratio
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
	 * given a pivot point and a degree rotate the segment around the pivot point
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
	 * @return return the 2 vertex point of the segment
	 */
	@Override
	public Point2D[] getPoints() {
		return new Point2D[] {this.get_p1(), this.get_p2()};
	}
	/**
	 * to string method for Segment2D
	 * @return 2 Point2D as an array
	 */
	@Override
	public String toString(){
		String ans = "";

		for (Point2D p : this.getPoints()){
			ans += p.toString();
			ans += ",";
		}
		return ans.substring(0, ans.length()-1);
	}
}