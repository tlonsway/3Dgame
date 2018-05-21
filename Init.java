import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.BufferedImage;
public class Init {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Window");
        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ZObject one = new ZObject(new OtherPoint(-30,4,5),new OtherPoint(-30,4,80), new OtherPoint(30,4,5));
        //ZObject two = new ZObject(new OtherPoint(-30,4,80), new OtherPoint(30,4,5), new OtherPoint(30,4,80));
        //ZObject one = new ZObject(new OtherPoint(-3,2,5),new OtherPoint(-3,2,10), new OtherPoint(3,2,5), new OtherPoint(3,2,10));
        ZObject one = new ZObject(new OtherPoint(-30,20,500),new OtherPoint(30,20,500),new OtherPoint(30,20,-100) , new OtherPoint(-30,20,-100));
        ZObject two = new ZObject(new OtherPoint(-15,20,1500), new OtherPoint(15,20,1500), new OtherPoint(15,20,5000), new OtherPoint(-15,20,5000));
        ZObject three = new ZObject(new OtherPoint(-120,20,500),new OtherPoint(-60,20,500), new OtherPoint(-60,20,-100), new OtherPoint(-120,20,-100));
        ArrayList<ZObject> samplein = new ArrayList<ZObject>();
        samplein.add(one);
        samplein.add(two);
        samplein.add(three);
        Display d = new Display(samplein);
        frame.add(d);
        d.setVisible(true);
        d.draw();
        GravityThread gt = new GravityThread(d);
        (new Thread(gt)).start();
        frame.addKeyListener(new KeyboardThread(d,gt));
        (new Thread(new FrameThread(d))).start();
        frame.getContentPane().setBackground(Color.BLACK);
    }
}