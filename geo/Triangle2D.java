package Exe.Ex4.geo;

import java.util.ArrayList;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	private final Point2D _p1;
	private final Point2D _p2;
	private final Point2D _p3;

	public Triangle2D(Triangle2D tri){
		this._p1 = new Point2D(tri.get_p1());
		this._p2 = new Point2D(tri.get_p2());
		this._p3 = new Point2D(tri.get_p3());
	}


	public Triangle2D(Point2D pt1, Point2D pt2, Point2D pt3){
		this._p1 = pt1;
		this._p2 = pt2;
		this._p3 = pt3;
	}

	public Point2D get_p1() {
		return new Point2D(_p1);
	}
	public Point2D get_p2() {
		return new Point2D(_p2);
	}
	public Point2D get_p3() {
		return new Point2D(_p3);
	}

	@Override
	public boolean contains(Point2D ot) {
		return Utils.isIn(new Point2D[] {this._p1, this._p2, this._p3}, ot);
	}

	@Override
	public double area() {
		return  Utils.calcArea(this.getPoints());
	}

	@Override
	public double perimeter() {
		return Utils.calcPerimeter(this.getPoints());
	}

	@Override
	public void move(Point2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
		this._p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(this);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
	}

	public double[] getXcoord(){
		double[] Xcoor = new double[3];

		Xcoor[0] = this._p1.x();
		Xcoor[1] = this._p2.x();
		Xcoor[2] = this._p3.x();

		return Xcoor;
	}

	public double[] getYcoord(){
		double[] Ycoor = new double[3];

		Ycoor[0] = this._p1.y();
		Ycoor[1] = this._p2.y();
		Ycoor[2] = this._p3.y();

		return Ycoor;
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[]{this._p1, this._p2, this._p3};
	}

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
