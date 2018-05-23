import java.util.*;
import java.awt.*;
import javafx.geometry.*;
public class ZObject implements Comparable<ZObject> {
    String type;
    OtherPoint p1;
    OtherPoint p2;
    OtherPoint p3;
    OtherPoint p4;
    Color c;
    boolean touched = false;
    public ZObject() {
    }    
    public ZObject(OtherPoint pone, OtherPoint ptwo) {
        type = "Vector";
        p1 = pone;
        p2 = ptwo;
    }
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, Color co) {
        type = "Polygon";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        c = co;
    }
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree) {
        type = "Polygon";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256),150);
        //c = new Color(0,(int)(Math.random()*256),0);
    }
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, OtherPoint pfour) {
        type="Quad";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        p4 = pfour;
        c = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256),150);
    }
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, OtherPoint pfour, Color co) {
        type="Quad";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        p4 = pfour;
        c = co;
        touched=false;
    }       
    public ZObject(OtherPoint pone, OtherPoint ptwo, OtherPoint pthree, OtherPoint pfour, Color co, boolean t) {
        type="Quad";
        p1 = pone;
        p2 = ptwo;
        p3 = pthree;
        p4 = pfour;
        c = co;
        touched=t;
    }    
    public double getZ() {
        //FIND POINT WITH CLOSEST Z
        /*OtherPoint closestpoint = p1;
        if (p2.getZ()<closestpoint.getZ()) {
            closestpoint=p2;
        }
        if (type.equals("Polygon") && p3.getZ()>closestpoint.getZ()) {
            closestpoint=p3;
        }*/
        //return closestpoint.getZ();
        if (type.equals("Polygon")) {
            return (p1.getZ()+p2.getZ()+p3.getZ())/3;
        } else if (type.equals("Quad")) {
            return (p1.getZ()+p2.getZ()+p3.getZ()+p4.getZ())/4;
        } else {
            return (p1.getZ()+p2.getZ())/2;
        }
    }
    public String getType() {
        return type;
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
    public Polygon getPolygon() {
        return new Polygon(p1,p2,p3,c);
    }
    public Vector getVector() {
        return new Vector(p1,p2);
    }
    public Quad getQuad() {
        return new Quad(p1,p2,p3,p4,c);
    }
    public Color getColor() {
        return c;
    }
    public int compareTo(ZObject other) {
        double otherZ = other.getZ();
        double thisZ = this.getZ();
        int ret;
        if (thisZ > otherZ)
            return -1;
        else if (thisZ < otherZ)
            return 1;
        else
            return 0;
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
    public double getTop() {
        if (p1.getY()>p2.getY()&&p1.getY()>p3.getY()&&p1.getY()>p4.getY()) {
            return p1.getY();
        } else if (p2.getY()>p1.getY()&&p2.getY()>p3.getY()&&p2.getY()>p4.getY()) {
            return p2.getY();
        } else if (p3.getY()>p1.getY()&&p3.getY()>p1.getY()&&p3.getY()>p4.getY()) {
            return p3.getY();
        } else {
            return p4.getY();
        }
    }
    public BoundingBox getBounds2D() {
        BoundingBox b = new BoundingBox(p4.getX(),p4.getZ(),p2.getX()-p4.getX(),p2.getZ()-p4.getZ());
        //System.out.println(b.toString());
        return b;
    }
    public BoundingBox getBounds3D() {
        return new BoundingBox(p1.getX(),p1.getZ(),-p1.getY(),p3.getX(),p3.getZ(),-p3.getY()+1);
    }
    public boolean isTouched() {
        return touched;
    }
    public void touch() {
        touched=true;
    }
    public void untouch() {
        touched=false;
    }
    public void setColor(Color co) {
        c=co;
    }
}