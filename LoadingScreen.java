import java.awt.*;
import javax.swing.*;
public class LoadingScreen extends JComponent {
    int x;
    public LoadingScreen() {
        super();
    }
    public void redraw(int xx) {
        x=xx;
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,800);
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(new GradientPaint(150,100,new Color(255,255,255,200),150,500,Color.RED));
        g2.fillRect(100, 300, x, 100);
        g.setColor(Color.WHITE);
        g.drawRect(100,300,600,100);
    }
}