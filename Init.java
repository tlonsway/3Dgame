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
        //ZObject two = new ZObject(new OtherPoint(-15,50,5000), new OtherPoint(15,50,5000), new OtherPoint(15,50,1500), new OtherPoint(-15,50,1500));
        //ZObject three = new ZObject(new OtherPoint(-120,20,500),new OtherPoint(-60,20,500), new OtherPoint(-60,20,-100), new OtherPoint(-120,20,-100));
        //ZObject four = new ZObject(new OtherPoint(-30,20,1450), new OtherPoint(30,20,1450), new OtherPoint(30,20,550), new OtherPoint(-30,20,550));
        ArrayList<ZObject> samplein = new ArrayList<ZObject>();
        samplein.add(one);
        int startx = -100;
        int starty = 0;
        int startz = 20;
        int maxdist = 65;
        int mindist = 35;
        int maxy=80;
        int miny=10;
        int maxz=10;
        int minz=1;
        int maxwidth=50;
        int minwidth=15;
        int maxlength=100;
        int minlength=20;
        for(int i=0;i<200;i++) {
            int randx = (int)((Math.random()*((maxdist+1)-mindist))+mindist);
            int randy = (int)((Math.random()*((maxy+1)-miny))+miny);
            int randz = (int)((Math.random()*((maxz+1)-minz))+minz);
            int randwidth = (int)((Math.random()*((maxwidth+1)-minwidth)+minwidth));
            int randlength = (int)((Math.random()*((maxlength+1)-minlength)+minlength));
            int randys = (int)(Math.random()*2);
            int randzs = (int)(Math.random()*2);
            OtherPoint p1 = null;
            OtherPoint p2 = null;
            OtherPoint p3 = null;
            OtherPoint p4 = null;
            if (randys==0) {
                randy*=-1;
            } 
            if (randzs==0) {
                randz*=-1;
            }
            p1 = new OtherPoint(starty+randy,startz+randz,startx+randx+randlength);
            p2 = new OtherPoint(starty+randy+randwidth,startz+randz,startx+randx+randlength);
            p3 = new OtherPoint(starty+randy+randwidth,startz+randz,startx+randx);
            p4 = new OtherPoint(starty+randy,startz+randz,startx+randx);
            ZObject z = new ZObject(p1,p2,p3,p4);
            samplein.add(z);
            startx+=randx+randlength;
            starty+=randy;
            startz+=randz;
        }
        //samplein.add(one);
        //samplein.add(two);
        //samplein.add(three);
        //samplein.add(four);
        Display d = new Display(samplein);
        frame.add(d);
        d.setVisible(true);
        d.draw();
        GravityThread gt = new GravityThread(d);
        (new Thread(gt)).start();
        frame.addKeyListener(new KeyboardThread(d,gt));
        (new Thread(new FrameThread(d))).start();
        frame.getContentPane().setBackground(Color.BLACK);
        (new Thread(new GroundChecker(d))).start();
    }
}