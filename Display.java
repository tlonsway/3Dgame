import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Display extends JComponent {
    ArrayList<ZObject> objects = new ArrayList<ZObject>();
    public static final int ASPECT = 1;
    public static final int FOV = 90;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    double playerx;
    double playery;
    double playerz;
    boolean a;
    boolean d;
    boolean w;
    boolean s;
    boolean shift;
    double mov = .5;
    public Display(ArrayList<ZObject> in) {
        objects = in;
    }
    public void draw() {
        if (shift) 
            mov = .9;
        if (!shift)
            mov = .5;
        if (a) {
            this.move('x',mov);
        }
        if (d) {
            this.move('x', -mov);
        }
        if (w) {
            this.move('z',-mov);
        }
        if (s) {
            this.move('z',mov);
        }
        if (playery<.025&&playery!=0) {
            playery=0;
            System.out.println(playery);
        }
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ZObject z : objects) {
            if (z.getType().equals("Polygon")) {
                double[] oneproj = project.project2D(new double[]{z.getPolygon().getOne().getX(),z.getPolygon().getOne().getY(),z.getPolygon().getOne().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] twoproj = project.project2D(new double[]{z.getPolygon().getTwo().getX(),z.getPolygon().getTwo().getY(),z.getPolygon().getTwo().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] threeproj = project.project2D(new double[]{z.getPolygon().getThree().getX(),z.getPolygon().getThree().getY(),z.getPolygon().getThree().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);
                int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0])};
                int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1])};
                g.setColor(z.getPolygon().getColor());
                g.fillPolygon(xp,yp,3);
            }        
        }
        //draw();
    }
    public void update(ArrayList<ZObject> in) {
        objects=in;
    }  
    public void move(char dir, double dis) {
        ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
        double xdist = 0;
        double ydist = 0;
        double zdist = 0;
        if (dir == 'x') {
            xdist = dis;
            playerx+=dis;
        }
        if (dir == 'y') {
            ydist = dis;
            playery+=dis;
        }
        if (dir == 'z') {
            zdist = dis;
            playerz+=dis;
        }
        for (ZObject zo : objects) {
            if (zo.getType().equals("Polygon")) {
                double[] oreturned = manipulate.translate(zo.getOneList(), xdist, ydist, zdist);            
                double[] twreturned = manipulate.translate(zo.getTwoList(), xdist, ydist, zdist);
                double[] trreturned = manipulate.translate(zo.getThreeList(), xdist, ydist, zdist);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
                OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,zo.getColor()));                
            }
        }
        objects = tempzobj;
        //draw();
    }   
    public double getPlayerX() {
        return playerx;
    }
    public double getPlayerY() {
        return playery;
    }
    public double getPlayerZ() {
        return playerz;
    }
    public void setPlayerX(double x) {
        playerx=x;
    }
    public void setPlayerY(double y) {
        playery=y;
    }
    public void setPlayerZ(double z) {
        playerz=z;
    }
    public void aPress() {
        a=true;
    }
    public void aRelease() {
        a=false;
    }
    public void dPress() {
        d=true;
    }
    public void dRelease() {
        d=false;
    }
    public void wPress() {
        w=true;
    }
    public void wRelease() {
        w=false;
    }
    public void sPress() {
        s=true;
    }
    public void sRelease() {
        s=false;
    }
    public void shiftPress() {
        shift=true;
    }
    public void shiftRelease() {
        shift=false;
    }
}