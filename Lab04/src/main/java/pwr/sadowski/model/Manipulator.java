package pwr.sadowski.model;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Manipulator extends GObject {

    public double alpha, beta;
    public int d, h;

    public Manipulator(int d, int h) {
        this.d = d;
        this.h = h;
    }


    @Override
    public void drawMe(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        int dx0 = g2d.getClipBounds().width / 2;
        int dy0 = g2d.getClipBounds().height / 2;
        AffineTransform saveAT = g2d.getTransform();

        t = new AffineTransform();

        t.translate(dx0, dy0);
        t.scale(1, -1);

        g2d.setTransform(t);
        g2d.fillOval(d, h, 8, 8);

        t.rotate(alpha);
        g2d.setTransform(t);
        g2d.drawLine(0, 0, 50, 0);

        t.translate(50, 0);
        t.rotate(-beta);
        g2d.setTransform(t);
        g2d.drawLine(0, 0, 150, 0);

        t.translate(150, 0);
        g2d.setTransform(t);

        double[] value = new double[6];
        g2d.getTransform().getMatrix(value);

        x = value[4];
        y = value[5];

        g2d.setTransform(saveAT);

    }

    @Override
    public void rotate() {
        alpha += Math.PI / 180.0; // co 1 stopień
        beta = alpha-(Math.atan((h-(Math.sin(alpha)*50))/(d-(Math.cos(alpha)*50))));
    }

}
