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
    public Display(ArrayList<ZObject> in) {
        objects = in;
    }
    public void draw() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ZObject z : objects) {
            System.out.println(z.getType());
            if (z.getType().equals("Polygon")) {
                if (z.getPolygon().getOne().getZ() > 0 && z.getPolygon().getTwo().getZ() > 0 && z.getPolygon().getThree().getZ() > 0) {
                    double[] oneproj = project.project2D(new double[]{z.getPolygon().getOne().getX(),z.getPolygon().getOne().getY(),z.getPolygon().getOne().getZ(),1},FOV,ASPECT,5.0,100.0);
                    double[] twoproj = project.project2D(new double[]{z.getPolygon().getTwo().getX(),z.getPolygon().getTwo().getY(),z.getPolygon().getTwo().getZ(),1},FOV,ASPECT,5.0,100.0);
                    double[] threeproj = project.project2D(new double[]{z.getPolygon().getThree().getX(),z.getPolygon().getThree().getY(),z.getPolygon().getThree().getZ(),1},FOV,ASPECT,5.0,100.0);
                    int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0])};
                    int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1])};
                    g.setColor(z.getPolygon().getColor());
                    g.fillPolygon(xp,yp,3);
                    System.out.println("rendering");
                }
            }        
        }
    }
    public void update(ArrayList<ZObject> in) {
        objects=in;
    }   
}