import java.awt.*;
import javax.swing.*;
public class SettingsMenu extends JPanel {
    public SettingsMenu() {
        super();
        
        
    }
    public void redraw() {
        
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,800);
        int TESTMIN=0;
        int TESTMAX=100;
        int TESTINIT=50;
        JSlider test = new JSlider(JSlider.HORIZONTAL,TESTMIN,TESTMAX,TESTINIT);
        test.setMajorTickSpacing(10);
        test.setPaintTicks(true);
        test.setPaintLabels(true);
    }
}