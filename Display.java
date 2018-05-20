import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Display extends JComponent {
    ArrayList<ZObject> objects = new ArrayList<ZObject>();
    ArrayList<Star> stars = new ArrayList<Star>();
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
    double mov = 0.5;
    double totalYDist = 0;
    public Display(ArrayList<ZObject> in) {
        objects = in;
        for (int i=0;i<100;i++) {
            int x = (int)(Math.random()*WIDTH);
            int y = (int)(Math.random()*HEIGHT);
            stars.add(new Star(x,y));
        }
        playery=0;
        playerx=0;
        playerz=0;
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
        playery=((double)((int)(playery*1000)))/1000;
        if (playery<.03&&playery!=0) {
            //System.out.println("y: " + playery);
            this.move('y',-playery);
            //playery=0;
            //System.out.println("y:" + playery);
        }
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (Star s: stars) {
            g.drawRect(s.getX(),s.getY(),2,2);
        }
        for(ZObject z : objects) {
            if (z.getType().equals("Polygon")) {
                double[] oneproj = project.project2D(new double[]{z.getPolygon().getOne().getX(),z.getPolygon().getOne().getY(),z.getPolygon().getOne().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] twoproj = project.project2D(new double[]{z.getPolygon().getTwo().getX(),z.getPolygon().getTwo().getY(),z.getPolygon().getTwo().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] threeproj = project.project2D(new double[]{z.getPolygon().getThree().getX(),z.getPolygon().getThree().getY(),z.getPolygon().getThree().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);
                int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0])};
                int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1])};
                g.setColor(z.getPolygon().getColor());
                g.fillPolygon(xp,yp,3);
            } else if (z.getType().equals("Quad")) {
                double[] oneproj = project.project2D(new double[]{z.getQuad().getOne().getX(),z.getQuad().getOne().getY(),z.getQuad().getOne().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] twoproj = project.project2D(new double[]{z.getQuad().getTwo().getX(),z.getQuad().getTwo().getY(),z.getQuad().getTwo().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] threeproj = project.project2D(new double[]{z.getQuad().getThree().getX(),z.getQuad().getThree().getY(),z.getQuad().getThree().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);     
                double[] fourproj = project.project2D(new double[]{z.getQuad().getFour().getX(),z.getQuad().getFour().getY(),z.getQuad().getFour().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);
                int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0]),(int)(WIDTH*fourproj[0])};
                int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1]),(int)(HEIGHT*fourproj[1])};
                //g.setColor(z.getQuad().getColor());
                Graphics2D g2=(Graphics2D)(g);
                g2.setPaint(new GradientPaint(WIDTH/2,HEIGHT,Color.WHITE,WIDTH/2, 0,z.getQuad().getColor()));
                //java.awt.Polygon p = new java.awt.Polygon();
                //g.fillPolygon(xp,yp,4);
                g2.fill(new java.awt.Polygon(xp,yp,4));
                Color w2 = new Color(255,255,255,180);
                Color z2 = new Color(z.getQuad().getColor().getRed(),z.getQuad().getColor().getGreen(),z.getQuad().getColor().getBlue(),180);
                g2.setPaint(new GradientPaint(WIDTH/2,HEIGHT,w2,WIDTH/2, 0,z2));
                g2.draw(new java.awt.Polygon(xp,yp,4));
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
            dis=((double)((int)(dis*1000)))/1000;
            //System.out.println("YDist plus dis: " + (totalYDist+dis));
            //System.out.println("Before YDist: " + totalYDist);
            //System.out.println("Before dis: " + dis);
            ydist = dis;
            playery+=dis;
            totalYDist+=dis;
            //System.out.println("dis: " + dis);
            //System.out.println("YDist: " + totalYDist);
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
            } else if (zo.getType().equals("Quad")) {
                double[] oreturned = manipulate.translate(zo.getOneList(), xdist, ydist, zdist);            
                double[] twreturned = manipulate.translate(zo.getTwoList(), xdist, ydist, zdist);
                double[] trreturned = manipulate.translate(zo.getThreeList(), xdist, ydist, zdist);   
                double[] freturned = manipulate.translate(zo.getFourList(), xdist, ydist, zdist);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
                OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
                OtherPoint dpfour = new OtherPoint(freturned[0],freturned[1],freturned[2]);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,dpfour,zo.getColor()));
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