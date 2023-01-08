package Exe.Ex4.geo;

import Exe.Ex4.gui.StdDraw_Ex4;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class represents a 2D polygon, as in <a href="https://en.wikipedia.org/wiki/Polygon">...</a>
 * This polygon can be assumed to be simple in terms of area and contains.
 *
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{

	private Point2D[] _pts;

	public Polygon2D(Point2D[] points){
		this._pts = points.clone();
	}

	public Polygon2D(Polygon2D poly){
		Point2D[] prevPts = poly.getPoints();
		this._pts = new Point2D[prevPts.length];

		for (int i = 0; i <prevPts.length; i++) {
			this._pts[i] = new Point2D(prevPts[i]);
		}
	}
	public Polygon2D(ArrayList<Point2D> arrPts){
		this._pts = arrPts.toArray(new Point2D[arrPts.size()]);
	}

	public Polygon2D(ArrayList<Point2D> arrPts,Point2D point){
		this._pts = arrPts.toArray(new Point2D[arrPts.size()+1]).clone();
		this._pts[this._pts.length-1] = point;
	}
	@Override
	public boolean contains(Point2D ot) {
		return Utils.isIn(this._pts, ot);
	}

	@Override
	public double area() {
		return Utils.calcArea(this._pts);
	}

	@Override
	public double perimeter() {
		return Utils.calcPerimeter(this._pts);
	}
	@Override
	public void move(Point2D vec) {
		for (Point2D p : this.getPoints()){
			p.move(vec);
		}
	}
	@Override
	public GeoShapeable copy() {
		return new Polygon2D(this);
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

	public double[] getXcoord(){
		double[] Xcoor = new double[this._pts.length];

		for (int i = 0; i < this._pts.length; i++) {
			Xcoor[i] = this._pts[i].x();
		}
		return Xcoor;
	}

	public double[] getYcoord(){
		double[] Ycoor = new double[this._pts.length];

		for (int i = 0; i < this._pts.length; i++) {
			Ycoor[i] = this._pts[i].y();
		}

		return Ycoor;
	}
	@Override
	public Point2D[] getPoints() {
		return this._pts;
	}

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
