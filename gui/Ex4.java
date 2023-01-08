package Exe.Ex4.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.*;

/**
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * @author boaz.benmoshe
 */
public class Ex4 implements Ex4_GUI {
    private ShapeCollectionable _shapes = new ShapeCollection();
    private GUI_Shapeable _gs;
    private GUI_Shapeable tempGs;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private String prevMode = "";
    private Point2D _p1;
    private Point2D _p2;
    private ArrayList<Point2D> pts = new ArrayList<>();
    private static Ex4 _winEx4 = null;
    private Ex4() {
        init(null);
    }
    public void init(ShapeCollectionable s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s.copy();
        }
        GUI_Shapeable _gs = null;
        Polygon2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point2D _p1 = null;
    }
    public void show(double d) {
        StdDraw_Ex4.setScale(0, d);
        StdDraw_Ex4.show();
        drawShapes();
    }
    public static Ex4 getInstance() {
        if (_winEx4 == null) {
            _winEx4 = new Ex4();
        }
        return _winEx4;
    }
    public void drawShapes() {
        StdDraw_Ex4.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        if (tempGs != null){
            drawShape(tempGs);
        }
        StdDraw_Ex4.show();
    }
    private static void drawShape(GUI_Shapeable g) {
        StdDraw_Ex4.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex4.setPenColor(Color.gray);
        }
        GeoShapeable gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle2D) {
            Circle2D c = (Circle2D) gs;
            Point2D cen = c.getPoints()[0];
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
            }
        }

        if (gs instanceof Segment2D) {
            Segment2D seg = (Segment2D) gs;
            Point2D p1 = seg.get_p1();
            Point2D p2 = seg.get_p2();

            StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());
        }

        if (gs instanceof Rect2D) {
            Rect2D rect = (Rect2D) gs;
            if (isFill){
                StdDraw_Ex4.filledPolygon(rect.getXcoord(), rect.getYcoord());
            } else {
                StdDraw_Ex4.polygon(rect.getXcoord(), rect.getYcoord());
            }
        }

        if (gs instanceof Triangle2D) {
            Triangle2D tri = (Triangle2D) gs;
            if (isFill){
                StdDraw_Ex4.filledPolygon(tri.getXcoord(), tri.getYcoord());
            } else {
                StdDraw_Ex4.polygon(tri.getXcoord(), tri.getYcoord());
            }
        }

        if (gs instanceof Polygon2D) {
            Polygon2D poly = (Polygon2D) gs;
            if (isFill){
                StdDraw_Ex4.filledPolygon(poly.getXcoord(), poly.getYcoord());
            } else {
                StdDraw_Ex4.polygon(poly.getXcoord(), poly.getYcoord());
            }
        }
    }
    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }
    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }
    public void actionPerformed(String p) {
        _mode = p;
        if (prevMode != _mode){
            pts.clear();
            prevMode = _mode;
            _p1 = null;
            _p2 = null;
            _gs = null;
            tempGs = null;
        }

        // SETTING COLORS
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }

        // SETTING FILL/EMPTY
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }

        // CLEAR
        if (p.equals("Clear")) {
            _shapes.removeAll();
        }

        // SELECT
        if (p.equals("All")) {
            selectHandler(true);
        }
        else if (p.equals("None")) {
            selectHandler(false);
        }
        else if (p.equals("Anti")) {
            selectHandler(null);
        }


        drawShapes();
    }
    public void mouseClicked(Point2D p) {
        System.out.println("Mode: " + _mode + "  " + p);

        //SHAPES
        if (_mode.equals("Circle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }

        if (_mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }

        if (_mode.equals("Rect")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }

        if(_mode.equals("Triangle")) {
            if (_gs == null || this._p2 == null) {
                if (_p1 != null) {
                    _p2 = new Point2D(p);
                }
                if (_p1 == null) {
                    _p1 = new Point2D(p);
                }

            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
                _p2 = null;
            }
        }

        if (_mode.equals("Polygon")) {
            if (_gs == null) {
                this.pts.add(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                pts.clear();
            }
        }


        //FUNCTIONALITIES
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                move();
                _p1 = null;
            }
        }

        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } else {
                rotate(_p1, p);
                _p1 = null;
            }
        }

        if (_mode.equals("Scale_90%")){
            scale(p, 0.9);
        }

        if (_mode.equals("Scale_110%")){
            scale(p, 1.1);
        }


        // SELECT
        if (_mode.equals("Point")) {
            selectHandler(p);
        }

        drawShapes();
    }
    private void selectHandler(Object inp){
        if (inp instanceof Boolean){
            selectAll((Boolean)inp);
        }
        else if (inp instanceof Point2D){
            select((Point2D)inp);
        }
        else {
            selectAnti();
        }
    }
    private void select(Point2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }
    private void selectAll(boolean on) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (on){
                s.setSelected(true);
            }
            else {
                s.setSelected(false);
            }
        }
    }
    private void selectAnti() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            s.setSelected(!s.isSelected());
        }
    }
    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (s.isSelected() && g != null) {
                g.move(_p1);
            }
        }
    }
    private void scale(Point2D p, double ratio) {
        for (int i=0; i<this._shapes.size();i+=1){
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.getShape().scale(p, ratio);
            }
        }
    }
    private void rotate(Point2D p1, Point2D p2) {
        for (int i=0; i<this._shapes.size();i+=1){
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                double dx = p2.x() - p1.x();
                double dy = p2.y() - p1.y();

                double wantedRotation = Math.atan(dy/dx);
                if (dx<0){
                    wantedRotation += Math.PI;
                }
                s.getShape().rotate(p1, Math.toDegrees(wantedRotation));
            }
        }
    }
    public void mouseRightClicked(Point2D p) {
        this.pts.add(p);
    }
    public void mouseMoved(MouseEvent e) {
        if (_p1 != null || this.pts.size()>0) {
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShapeable gs = null;
            GeoShapeable tmp = null;
//          System.out.println("M: "+x1+","+y1);
            Point2D p = new Point2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle2D(_p1, r);
            }

            if (_mode.equals("Segment")) {
                Point2D tempP = new Point2D(StdDraw_Ex4.mouseX(), StdDraw_Ex4.mouseY());
                gs = new Segment2D(_p1, tempP);
            }

            if (_mode.equals("Rect")) {
                Point2D tempP = new Point2D(StdDraw_Ex4.mouseX(), StdDraw_Ex4.mouseY());
                Point2D p2 = new Point2D(_p1.x(), y1);
                Point2D p3 = new Point2D(x1, y1);
                Point2D p4 = new Point2D(x1, _p1.y());
                Point2D[] tempPoints = {_p1, p2, p3, p4};
                gs = new Rect2D(tempPoints);
            }

            if (_mode.equals("Triangle")){
                if (_p1 != null && _p2 == null){
                    Point2D tempP = new Point2D(StdDraw_Ex4.mouseX(), StdDraw_Ex4.mouseY());
                    tmp = new Segment2D(_p1, tempP);
                }

                if (_p2 != null){
                    Point2D tempP = new Point2D(StdDraw_Ex4.mouseX(), StdDraw_Ex4.mouseY());
                    gs = new Triangle2D(_p1, _p2, tempP);
                }
            }

            if (_mode.equals("Polygon")){
                gs = new Polygon2D(this.pts, new Point2D(StdDraw_Ex4.mouseX(), StdDraw_Ex4.mouseY()));
            }

            tempGs = new GUIShape(tmp, false, Color.pink, 0);
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }
    @Override
    public ShapeCollectionable getShape_Collection() {
        // TODO Auto-generated method stub
        return this._shapes;
    }
    @Override
    public void show() {
        show(Ex4_Const.DIM_SIZE);
    }
    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            ans += s.toString() + "\n";
        }
        return ans;
    }
}
