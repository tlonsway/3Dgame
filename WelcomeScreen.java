import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
public class WelcomeScreen extends JPanel {
    float opacity;
    public WelcomeScreen() {
        super();
    }
    public void draw(float op) {
        repaint();
        opacity=op;
    }
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            BufferedImage image = ImageIO.read(new File("PlaneGlider.png"));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2.drawImage(image,0,0,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
}