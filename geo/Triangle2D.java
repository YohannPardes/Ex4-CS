package Exe.Ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	private Point2D[] _pts;
	Triangle2D(Point2D[] ptSet){
		this._pts = ptSet;
	}
	@Override
	public boolean contains(Point2D ot) {
		return Utils.isIn(this._pts, ot);
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
		for (Point2D p : this._pts){
			p.move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(this._pts);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for (Point2D p :
				this._pts) {
			p.scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (Point2D p :
				this._pts) {
			p.scale(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		return this._pts;
	}
}
