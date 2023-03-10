
package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D point in the plane.
 * Do NOT change this class! It would be used as is for testing.
 * Ex4: you should edit and update this class!
 * @author boaz.benmoshe
 */


public class Point2D{
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point2D ORIGIN = new Point2D(0,0);
    private double _x,_y;
    public Point2D(double x,double y) {
    	_x=x; _y=y;
    }
    public Point2D(Point2D p) {
       this(p.x(), p.y());
    }
    public Point2D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
            throw(e);
        }
    }
    public double x() {return _x;}
    public double y() {return _y;}
 
    public int ix() {return (int)_x;}
    public int iy() {return (int)_y;}
  
    public Point2D add(Point2D p) {
    	return new Point2D(p.x()+x(),p.y()+y());
    }
    public String toString()
    {
        return _x+","+_y;
    }

    public double distance()
    {
        return this.distance(ORIGIN);
    }
    public double distance(Point2D p2) {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double t = (dx*dx+dy*dy);
        return Math.sqrt(t);
    }
    @Override
    public boolean equals(Object p)
    {
        if(!(p instanceof Point2D)) {return false;}
        Point2D p2 = (Point2D)p;
        return ( (_x==p2._x) && (_y==p2._y));
    }
    public boolean close2equals(Point2D p2, double eps)
    {
        return ( this.distance(p2) < eps );
    }
    public boolean close2equals(Point2D p2)
    {
        return close2equals(p2, Ex4_Const.EPS);
    }
    /**
     * This method returns the vector between this point and the target point. The vector is represented as a Point2D.
     * @param target
     * @return
     */
    public Point2D vector(Point2D target) {
    	double dx = target.x() - this.x();
    	double dy = target.y() - this.y();
    	return new Point2D(dx,dy);
    }
    /**
     * This function move the point to a new location modifying the actual point
     * @param vec The vector that has been add.
     */
	public void move(Point2D vec) {
		this._x += vec.x();
		this._y += vec.y();
	}
	
	/////////////////////// You should implement the methods below ///////////////////////////
	public void scale(Point2D cen, double ratio) {
		//////////add your code below ///////////
		double dx = this.x() - cen.x();
        double dy = this.y() - cen.y();

        double newX = cen.x() + dx * ratio;
        double newY = cen.y() + dy * ratio;

        this._x = newX;
        this._y = newY;
		////////////////////////////////////////
	}
	public void rotate(Point2D cen, double angleDegrees) {
		//////////add your code below ///////////
        double dx = this.x() - cen.x();
        double dy = this.y() - cen.y();

        // the distance between the clicked point and the actual point
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        // adding pi (rad) if the angle in the 3rd or 4th quarter
        double currentAngle = Math.atan(dy/dx);
        if (dx == 0){
            currentAngle = 0;
        }
        if (dx<0){
            currentAngle += Math.PI;
        }
        double newAngle = currentAngle + Math.toRadians(angleDegrees);

        // calculating the new pos
        double newX = cen.x() + r*Math.cos(newAngle);
        double newY = cen.y() + r*Math.sin(newAngle);

        this._x = newX;
        this._y = newY;
		/////////////////////////////////////////
	}
}
