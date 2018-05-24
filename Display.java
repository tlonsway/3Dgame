import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javafx.geometry.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.*;
import java.awt.MultipleGradientPaint.CycleMethod;
public class Display extends JComponent {
    ArrayList<ZObject> objects = new ArrayList<ZObject>();
    ArrayList<ZObject> original = new ArrayList<ZObject>();
    ArrayList<ZObject> playerbox = new ArrayList<ZObject>();
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
    double mov = 1;
    double totalYDist = 0;
    int score = 0;
    double zt = 0;
    boolean paused;
    double lastGround = 0;
    double dropy=0;
    public Display(ArrayList<ZObject> in) {
        objects = in;
        for (int i=0;i<100;i++) {
            int x = (int)(Math.random()*WIDTH);
            int y = (int)(Math.random()*HEIGHT);
            stars.add(new Star(x,y));
        }
        for(ZObject z : in) {
            original.add(z);
        }
        playery=0;
        playerx=0;
        playerz=0;
        ZObject zone = new ZObject(new OtherPoint(-7,20,90), new OtherPoint(7,20,90), new OtherPoint(7,20,80), new OtherPoint(-7,20,80),Color.RED);
        ZObject ztwo = new ZObject(new OtherPoint(-7,20,80), new OtherPoint(-7,10,80), new OtherPoint(7,10,80), new OtherPoint(7,20,80),Color.RED);
        ZObject zthree = new ZObject(new OtherPoint(-7,10,80), new OtherPoint(-7,10,90), new OtherPoint(7,10,90), new OtherPoint(7,10,80),Color.RED);
        ZObject zfour = new ZObject(new OtherPoint(-7,10,90), new OtherPoint(-7,20,90), new OtherPoint(7,20,90), new OtherPoint(7,10,90),Color.RED);
        ZObject zfive = new ZObject(new OtherPoint(-7,20,80), new OtherPoint(-7,20,90), new OtherPoint(-7,10,90), new OtherPoint(-7,10,80),Color.RED);
        ZObject zsix = new ZObject(new OtherPoint(7,20,80), new OtherPoint(7,20,90), new OtherPoint(7,10,90), new OtherPoint(7,10,80),Color.RED);
        playerbox.add(zone);
        playerbox.add(ztwo);
        playerbox.add(zthree);
        playerbox.add(zfour);
        playerbox.add(zfive);
        playerbox.add(zsix);
    }
    public void draw() {
        //System.out.println(dropy);
        if (dropy<-50) {
            dropy=0;
            reset();
        }
        if (shift) 
            mov = 3;
        if (!shift)
            mov = 2;
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
        /*if (playery<.03&&playery!=0) {
            //System.out.println("y: " + playery);
            this.move('y',-playery);
            //playery=0;
            //System.out.println("y:" + playery);
        }*/

        //System.out.println(playery);
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255,255,255,180));
        for (int i=stars.size()-1;i>-1;i--) {
            Star s = stars.get(i);
            if (s.getX()<0||s.getY()<0||s.getX()>WIDTH||s.getY()>HEIGHT) {
                stars.remove(i);
                int x = (int)(Math.random()*WIDTH);
                int y = (int)(Math.random()*HEIGHT);
                stars.add(new Star(x,y));
                continue;
            } 
            if (s.getX()<WIDTH/2) {
                s.setX(s.getX()-1);
            }
            if (s.getX()>WIDTH/2) {
                s.setX(s.getX()+1);
            }
            if (s.getY()<HEIGHT/2) {
                s.setY(s.getY()-1);
            }
            if (s.getY()>HEIGHT/2) {
                s.setY(s.getY()+1);
            } 
            /*java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(s.getX(), s.getX());
            float radius = 10;
            java.awt.geom.Point2D focus = new java.awt.geom.Point2D.Float(s.getX()-10, s.getY()-10);
            float[] dist = {0.0f, 0.5f, 1.0f};
            Color[] colors = {new Color(255,255,255,255), new Color(255,255,255,180), new Color(255,255,255,100)};
            RadialGradientPaint p =
            new RadialGradientPaint(center, radius, focus,
                                 dist, colors,
                                 CycleMethod.NO_CYCLE);
            Graphics2D g2D = (Graphics2D)g; 
            g2D.setPaint(p);
            g2D.fillRect(s.getX(),s.getY(),5,5);*/
            g.fillRect(s.getX(),s.getY(),2,2);
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
                if (z.getZ()<1000) {
                    Graphics2D g2=(Graphics2D)(g);
                    g2.setPaint(new GradientPaint(WIDTH/2,HEIGHT,new Color(255,255,255,200),WIDTH/2, 0,z.getQuad().getColor()));
                    //java.awt.Polygon p = new java.awt.Polygon();
                    //g.fillPolygon(xp,yp,4);
                    g2.fill(new java.awt.Polygon(xp,yp,4));
                    Color w2 = new Color(255,255,255,20);
                    Color z2 = new Color(z.getQuad().getColor().getRed(),z.getQuad().getColor().getGreen(),z.getQuad().getColor().getBlue(),20);
                    g2.setPaint(new GradientPaint(WIDTH/2,HEIGHT,w2,WIDTH/2, 0,z2));
                    g2.draw(new java.awt.Polygon(xp,yp,4));
                }
            }
        }
        for(ZObject z : playerbox) {
            if (z.getType().equals("Quad")) {
                double[] oneproj = project.project2D(new double[]{z.getQuad().getOne().getX(),z.getQuad().getOne().getY(),z.getQuad().getOne().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] twoproj = project.project2D(new double[]{z.getQuad().getTwo().getX(),z.getQuad().getTwo().getY(),z.getQuad().getTwo().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] threeproj = project.project2D(new double[]{z.getQuad().getThree().getX(),z.getQuad().getThree().getY(),z.getQuad().getThree().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);     
                double[] fourproj = project.project2D(new double[]{z.getQuad().getFour().getX(),z.getQuad().getFour().getY(),z.getQuad().getFour().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);
                int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0]),(int)(WIDTH*fourproj[0])};
                int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1]),(int)(HEIGHT*fourproj[1])};
                g.setColor(new Color(z.getColor().getRed(),z.getColor().getGreen(),z.getColor().getBlue(),150));
                g.fillPolygon(xp,yp,4);
                g.setColor(new Color(z.getColor().getRed(),z.getColor().getGreen(),z.getColor().getBlue(),255));
                g.drawPolygon(xp,yp,4);
            }
        }        
        g.setColor(new Color(255,0,0,200));
        Font f = new Font("Courier New", Font.BOLD, 40);
        g.setFont(f);
        g.drawString("SCORE: " + score, 30, 50);
        /*double[] oneproj = project.project2D(new double[]{-7,20,90,1},FOV,ASPECT,0.0,100.0);
        double[] twoproj = project.project2D(new double[]{7,20,90,1},FOV,ASPECT,0.0,100.0);
        double[] threeproj = project.project2D(new double[]{7,20,80,1},FOV,ASPECT,5.0,100.0);     
        double[] fourproj = project.project2D(new double[]{-7,20,80,1},FOV,ASPECT,5.0,100.0);
        int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0]),(int)(WIDTH*fourproj[0])};
        int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1]),(int)(HEIGHT*fourproj[1])};
        g.drawPolygon(xp,yp,4);*/
        //draw();
    }
    public void update(ArrayList<ZObject> in) {
        objects=in;
    }  
    public void move(char dir, double dis) {
        if (!paused) {
            ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
            double xdist = 0;
            double ydist = 0;
            double zdist = 0;
            if (dir == 'x') {
                xdist = dis;
                playerx+=dis;
            }
            if (dir == 'y') {
                //dis=((double)((int)(dis*1000)))/1000;
                //System.out.println("YDist plus dis: " + (totalYDist+dis));
                //System.out.println("Before YDist: " + totalYDist);
                //System.out.println("Before dis: " + dis);
                ydist = dis;
                playery+=dis;
                totalYDist+=dis;
                dropy+=dis;
                //System.out.println("dis: " + dis);
                //System.out.println("YDist: " + totalYDist);
            }
            if (dir == 'z') {
                zdist = dis;
                playerz+=dis;
                zt+=dis;
            }
            for (ZObject zo : objects) {
                if (zo.getType().equals("Quad")) {
                    double[] oreturned = manipulate.translate(zo.getOneList(), xdist, ydist, zdist);            
                    double[] twreturned = manipulate.translate(zo.getTwoList(), xdist, ydist, zdist);
                    double[] trreturned = manipulate.translate(zo.getThreeList(), xdist, ydist, zdist);   
                    double[] freturned = manipulate.translate(zo.getFourList(), xdist, ydist, zdist);
                    OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                    OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
                    OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
                    OtherPoint dpfour = new OtherPoint(freturned[0],freturned[1],freturned[2]);
                    tempzobj.add(new ZObject(dpone,dptwo,dpthree,dpfour,zo.getColor(),zo.isTouched()));
                }
            }
            
            objects = tempzobj;
            //draw();
        }
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
    //public void setPlayerZ(double z) {
    //    playerz=z;
    //}
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
    public double getGround() {
        double highest=10000;
        BoundingBox player = new BoundingBox(-7,80,14,10);
        for (ZObject z : objects) {
            //System.out.println(z.getBounds2D().toString());
            if (z.getBounds2D().intersects(player)) {
                //System.out.println("found intersection");
                if (z.getTop()>0) {
                    dropy=0;
                }
                if (!z.isTouched()) {
                    score++;
                    z.touch();
                    lastGround=z.getTop();
                }
                if (z.getTop()<24) {
                    z.setColor(Color.GREEN);
                }
                if (z.getTop()<highest) {
                    highest=z.getTop();
                    //System.out.println("new highest");
                }
            }
        }
        return highest;
    }
    public void addObject(ZObject z) {
        objects.add(z);
    }
    public void reset() {
        if (!paused) {
            pause();
            score=0;
            objects=new ArrayList<ZObject>();
            for(ZObject z : original) {
                objects.add(z);
            }
            unpause();
        }
    }
    public void pause() {
        paused=true;
    }
    public void unpause() {
        paused=false;
    }
    public boolean getPaused() {
        return paused;
    }
    public void spinPlayer(double deg) {
        double ydist=deg;
        double xdist=0;
        double zdist=0;
        ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
        for(ZObject zo : playerbox) {
            if (zo.getType().equals("Quad")) {
                double[] oreturned = manipulate.rotate(new double[]{zo.getOne().getX(),zo.getOne().getY()-15,zo.getOne().getZ()-85,1}, 'x', deg);            
                double[] twreturned = manipulate.rotate(new double[]{zo.getTwo().getX(),zo.getTwo().getY()-15,zo.getTwo().getZ()-85,1}, 'x', deg);
                double[] trreturned = manipulate.rotate(new double[]{zo.getThree().getX(),zo.getThree().getY()-15,zo.getThree().getZ()-85,1}, 'x', deg);   
                double[] freturned = manipulate.rotate(new double[]{zo.getFour().getX(),zo.getFour().getY()-15,zo.getFour().getZ()-85,1}, 'x', deg);
                //double[] oreturned = manipulate.rotateAxis(zo.getOneList(), 
                
                
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1]+15,oreturned[2]+85);
                OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1]+15,twreturned[2]+85);
                OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1]+15,trreturned[2]+85);
                OtherPoint dpfour = new OtherPoint(freturned[0],freturned[1]+15,freturned[2]+85);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,dpfour,zo.getColor(),zo.isTouched()));
            }
        }
        playerbox=tempzobj;
    }      
    public void changePlayerColor(Color c) {
        for(ZObject z : playerbox) {
            z.setColor(c);
        }
    }
}