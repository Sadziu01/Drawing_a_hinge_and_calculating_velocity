package pwr.sadowski.main;

import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import pwr.sadowski.model.GObject;
import pwr.sadowski.model.Manipulator;

public class MyPanel extends JPanel {

    public GObject g;


    public MyPanel(int a, int b) {
        this.setSize(300, 300);
        this.setLayout(new GridBagLayout());
        g = new Manipulator(a, b);}

    @Override
    protected void paintComponent(Graphics arg0) {
        super.paintComponent(arg0);

        if (g != null) {
            g.drawMe(arg0);
        }
    }

}
