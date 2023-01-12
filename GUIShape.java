package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */

import Exe.Ex4.geo.GeoShapeable;

import java.awt.*;


public class GUIShape implements GUI_Shapeable {
    private GeoShapeable _g = null;
    private boolean _fill;
    private Color _color;
    private int _tag;
    private boolean _isSelected;
    private static int count = 0;

    public GUIShape(GeoShapeable g, boolean fill, Color color, int tag) {
        _g = null;
        if (g != null) {
            _g = g.copy();
        }
        _fill = fill;
        _color = color;
        setTag(tag);
        _isSelected = false;

    }

    public GUIShape(GeoShapeable g, boolean fill, Color color) {
        _g = null;
        if (g != null) {
            _g = g.copy();
        }
        _fill = fill;
        _color = color;
        setTag(count);
        _isSelected = false;
        count ++;
    }

    public GUIShape(GUIShape ot) {
        this(ot._g, ot._fill, ot._color, ot._tag);
    }

    @Override
    public GeoShapeable getShape() {
        return _g;
    }

    @Override
    public boolean isFilled() {
        return _fill;
    }

    @Override
    public void setFilled(boolean filled) {
        _fill = filled;
    }

    @Override
    public Color getColor() {
        return _color;
    }

    @Override
    public void setColor(Color cl) {
        _color = cl;
    }

    @Override
    public int getTag() {
        return _tag;
    }

    @Override
    public void setTag(int tag) {
        _tag = tag;

    }
    @Override
    public GUI_Shapeable copy() {
        GUI_Shapeable cp = new GUIShape(this);
        return cp;
    }

    @Override
    public String toString() {
        String ans = "";
        if (this.getShape() != null) {
            ans += "GUIShape,";
            ans += this._color.getRGB();
            ans += "," + this.isFilled();
            ans += "," + this.getTag();
            ans += "," + this.getShape().getClass().getSimpleName();
            ans += "," + this.getShape().toString();
        }

        return ans;
    }

    private void init(String[] ww) {

    }
    @Override
    public boolean isSelected() {
        return this._isSelected;
    }
    @Override
    public void setSelected(boolean s) {
        this._isSelected = s;
    }
    @Override
    public void setShape(GeoShapeable g) {
        // TODO Auto-generated method stub

    }
}
