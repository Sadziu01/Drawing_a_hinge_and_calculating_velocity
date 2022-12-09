/**
 * @author Bartosz Sadowski
 *gradlew build
 *gradlew jar
 *java.exe -p Lab04_pop.jar -m Lab/pwr.sadowski.main.MainFrame
 */
package pwr.sadowski.main;

import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {


    private JTextField formD;
    private JTextField formH;
    private JPanel contentPane;
    private JLabel lblSpeedx;
    private JLabel lblSpeedy;
    private MyPanel mp;

    static MainFrame thisClass = new MainFrame();

    public int d, h;
    public double x, y, tempX, tempY;
    public static double speedX;
    public static double speedY;

    private static final DecimalFormat df = new DecimalFormat("0.00");


    public void animate() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int i = 360;
                while (i>0) {
                    mp.g.rotate();
                    if(i<360){
                        System.out.println("Next");
                        x = mp.g.getX();
                        y = mp.g.getY();
                    }

                    speedX = Math.abs((x-tempX)/0.01);
                    speedY = Math.abs((y-tempY)/0.01);

                    if(speedX > 0 && speedY > 0){
                        lblSpeedx.setText(String.valueOf("X: " + df.format(speedX)));
                        lblSpeedy.setText(String.valueOf("Y: " + df.format(speedY)));
                    }

                    tempX = x;
                    tempY = y;

                    System.out.println(df.format(speedX) + " Jx per sec");
                    System.out.println(df.format(speedY) + " Jy per sec");

                    mp.repaint();
                    i--;
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                }
                System.out.println("Animation stopped");
            }
        });
        t.start();

    }
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel fLabelD = new JLabel("Input d:");
        fLabelD.setFont(new Font("Tahoma", Font.PLAIN, 16));
        fLabelD.setBounds(10, 28, 303, 31);
        contentPane.add(fLabelD);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                h = Integer.parseInt(formH.getText());
                d = Integer.parseInt(formD.getText());
                System.out.println(h);
                System.out.println(d);
                formD.setText("");
                formH.setText("");

                mp = new MyPanel(d, h);
                mp.setBounds(260, 10, 236, 448);
                contentPane.add(mp);

                thisClass.animate();
            }
        });
        btnNewButton.setBounds(10, 320, 100, 31);
        contentPane.add(btnNewButton);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formD.setText("");
                formH.setText("");
            }
        });
        btnClear.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnClear.setBounds(120, 320, 100, 31);
        contentPane.add(btnClear);

        JLabel lblInputH = new JLabel("Input h:");
        lblInputH.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblInputH.setBounds(10, 122, 303, 31);
        contentPane.add(lblInputH);

        formD = new JTextField();
        formD.setBounds(10, 69, 157, 31);
        contentPane.add(formD);
        formD.setColumns(10);

        formH = new JTextField();
        formH.setColumns(10);
        formH.setBounds(10, 165, 157, 31);
        contentPane.add(formH);

        lblSpeedx = new JLabel("SpeedX");
        lblSpeedx.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSpeedx.setBounds(10, 415, 77, 31);
        lblSpeedx.setText(String.valueOf("X: " + speedX));
        contentPane.add(lblSpeedx);

        lblSpeedy = new JLabel("SpeedY");
        lblSpeedy.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSpeedy.setBounds(143, 415, 77, 31);
        lblSpeedy.setText(String.valueOf("Y: " + speedY));
        contentPane.add(lblSpeedy);

    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                thisClass.setTitle("Manipulator");
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });

    }
}

