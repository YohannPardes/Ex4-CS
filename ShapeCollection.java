package Exe.Ex4;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import Exe.Ex4.geo.*;


/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<>();
	}
	public ShapeCollection(ArrayList<GUI_Shapeable> GUI_List) {
		_shapes = GUI_List;
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		//////////add your code below ///////////
		return this._shapes.remove(i);
		//////////////////////////////////////////
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		//////////add your code below ///////////
		this._shapes.add(i, s);
		//////////////////////////////////////////
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() {
		//////////add your code below ///////////
		ArrayList<GUI_Shapeable> temp = new ArrayList<>();
		for (GUI_Shapeable s : this._shapes){
			temp.add(s.copy());
		}
		ShapeCollection newCollection = new ShapeCollection(temp);
		return newCollection;
		//////////////////////////////////////////
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		//////////add your code below ///////////
		_shapes.sort(comp);
		//////////////////////////////////////////
	}

	@Override
	public void removeAll() {
		//////////add your code below ///////////
		this._shapes.removeAll(this._shapes);
	}

	@Override
	public void save(String path) {

			File file = new File(path);
			FileWriter fw = null;
			try {
				fw = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter pw = new PrintWriter(fw);
//			 add to the file each object.
			for (int i = 0; i < _shapes.size(); i+=1) {
				GUI_Shapeable s = _shapes.get(i);
				pw.println(s);
			}
			pw.close();

		}

	@Override
	public void load(String file) {
		//resetting the collection
		removeAll();

		// initialazing the variables
		GeoShapeable g = null;
		boolean fill;
		Color color;
		int tag;

		//iterating through all the lines in the save folder
		File myFile = new File(file);
		Scanner myScan = null;
		try {
			myScan = new Scanner(myFile);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		while (myScan.hasNextLine()){
			String currLine = myScan.nextLine();
			String[] stringData = currLine.split(",");

			color = Color.decode(stringData[1]);
			fill = Boolean.parseBoolean(stringData[2]);
			tag = Integer.parseInt(stringData[3]);

			String type = stringData[4];
			if (type.equals("Circle2D")){
				double r = Double.parseDouble(stringData[7]);
				Point2D cen = new Point2D(Double.parseDouble(stringData[5]), Double.parseDouble(stringData[6]));
				g = (new Circle2D(cen, r));
			}

			if (type.equals("Triangle2D")){
				Point2D p1 = new Point2D(Double.parseDouble(stringData[5]), Double.parseDouble(stringData[6]));
				Point2D p2 = new Point2D(Double.parseDouble(stringData[7]), Double.parseDouble(stringData[8]));
				Point2D p3 = new Point2D(Double.parseDouble(stringData[9]), Double.parseDouble(stringData[10]));
				g = (new Triangle2D(p1, p2, p3));
			}

			if (type.equals("Segment2D")){
				Point2D p1 = new Point2D(Double.parseDouble(stringData[5]), Double.parseDouble(stringData[6]));
				Point2D p2 = new Point2D(Double.parseDouble(stringData[7]), Double.parseDouble(stringData[8]));

				g = (new Segment2D(p1, p2));
			}

			if (type.equals("Rect2D")){
				Point2D p1 = new Point2D(Double.parseDouble(stringData[5]), Double.parseDouble(stringData[6]));
				Point2D p2 = new Point2D(Double.parseDouble(stringData[7]), Double.parseDouble(stringData[8]));
				Point2D p3 = new Point2D(Double.parseDouble(stringData[9]), Double.parseDouble(stringData[10]));
				Point2D p4 = new Point2D(Double.parseDouble(stringData[11]), Double.parseDouble(stringData[12]));
				g = new Rect2D(new Point2D[] {p1, p2, p3, p4});
			}

			if (type.equals("Polygon2D")){
				ArrayList<Point2D> pts = new ArrayList<>();
				for (int i = 5; i<stringData.length; i+=2){
					pts.add(new Point2D(Double.parseDouble(stringData[i]), Double.parseDouble(stringData[i+1])));
				}
				g = new Polygon2D(pts);
			}

			this._shapes.add(new GUIShape(g, fill, color, tag));

		}
		myScan.close();

		//////////////////////////////////////////
	}
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		//////////add your code below ///////////
		double maxX = 0;
		double maxY = 0;
		double minX = 10000;
		double minY = 10000;
		for (GUI_Shapeable s : this._shapes){
			Point2D[] currPts = s.getShape().getPoints();
			for (Point2D p : currPts){
				if (p.x()>maxX){maxX = p.x();}
				if (p.y()>maxY){maxY = p.y();}
				if (p.x()<minX){minX = p.x();}
				if (p.y()<minY){minY = p.y();}
			}
		}

		Point2D p1 = new Point2D(maxX, maxY);
		Point2D p2 = new Point2D(minX, minY);

		ans = new Rect2D(p1, p2);
		//////////////////////////////////////////
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
}
