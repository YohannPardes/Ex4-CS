package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	private Point2D[] _pts;
	public Rect2D(Point2D [] pts){
		if (pts.length != 4){
			throw new NumberFormatException("Rect is expecting 4 points but"+ pts.length+"were given");
		}
		this._pts = pts;
	}

	public double[] getXcoord(){
		double[] Xcoor = new double[4];

		for (int i = 0; i < this._pts.length; i++) {
			Xcoor[i] = this._pts[i].x();
		}
		return Xcoor;
	}

	public double[] getYcoord(){
		double[] Ycoor = new double[4];

		for (int i = 0; i < this._pts.length; i++) {
			Ycoor[i] = this._pts[i].y();
		}

		return Ycoor;
	}

	@Override
	public boolean contains(Point2D ot) {
		return Utils.isIn(this.getPoints(), ot);
	}

	@Override
	public double area() {
		return Utils.calcArea(this.getPoints());
	}

	@Override
	public double perimeter() {
		return Utils.calcPerimeter(this.getPoints());
	}

	@Override
	public void move(Point2D vec) {
		for (Point2D p : this.getPoints()){
			p.move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		return new Rect2D(this._pts.clone());
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
		return this._pts;
	}

}
