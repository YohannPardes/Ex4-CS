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
	public Segment2D(Point2D p1, Point2D p2){
		this._p1 = p1;
		this._p2 = p2;
	}
	public Point2D get_p1(){
		return this._p1;
	}
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
			double dy = this._p2.y() - this._p1.y();
			double dx = this._p2.x() - this._p1.x();
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
	@Override
	public void move(Point2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
	}
	@Override
	public GeoShapeable copy() {

		return new Segment2D(new Point2D(this._p1), new Point2D(this._p2));
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for (Point2D p : this.getPoints()){
			p.scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (Point2D p : this.getPoints()){
			p.rotate(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[] {this.get_p1(), this.get_p2()};
	}
	
}