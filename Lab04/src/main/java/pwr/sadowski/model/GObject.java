package pwr.sadowski.model;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

abstract public class GObject {
    public AffineTransform t;
    double x = 0, y = 0;

    abstract public void drawMe(Graphics g);
    abstract public void rotate();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

