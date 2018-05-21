import java.awt.*;
import javafx.geometry.*;
public class Quad {
    OtherPoint p1;
    OtherPoint p2;
    OtherPoint p3;
    OtherPoint p4;
    Color c;
    public Quad(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, OtherPoint pfour) {
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        p4 = pfour;
        c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
    }
    public Quad(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, OtherPoint pfour, Color co) {
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        p4 = pfour;
        c = co;
    }    
    public OtherPoint getOne() {
        return p1;
    }
    public OtherPoint getTwo() {
        return p2;
    }
    public OtherPoint getThree() {
        return p3;
    }
    public OtherPoint getFour() {
        return p4;
    }
    public Color getColor() {
        return c;
    }
    public double[] getOneList() {
        return new double[]{p1.getX(),p1.getY(),p1.getZ(),1};
    }
    public double[] getTwoList() {
        return new double[]{p2.getX(),p2.getY(),p2.getZ(),1};
    }
    public double[] getThreeList() {
        return new double[]{p3.getX(),p3.getY(),p3.getZ(),1};
    }
    public double[] getFourList() {
        return new double[]{p4.getX(),p4.getY(),p4.getZ(),1};
    }
    public BoundingBox getBounds() {
        BoundingBox b = new BoundingBox(p1.getX(),p1.getY(),p1.getZ(),p3.getX(),p3.getY(),p3.getZ());
        System.out.println(b.toString());
        return b;
    }
    
}