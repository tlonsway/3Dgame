import java.awt.*;
import javax.swing.*;
public class SettingsMenu extends JComponent {
    boolean dis = false;
    JTextField speed = new JTextField();
    public SettingsMenu() {
        super();
        //speed.setVisible(true);
        //speed.setEditable(true);
        //this.add(speed);
    }
    public void redraw() {
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, 800, 800);
        g.setColor(Color.RED);
        Font f = new Font("Courier New", Font.BOLD, 80);
        g.setFont(f);
        g.drawString("SETTINGS", 50, 300);
        //speed.setBounds(150,400,80,30);
        Font f2 = new Font("Courier New", Font.BOLD,20);
        g.setFont(f2);
        //g.drawString("TOTAL BLOCKS",);
    }
    public void disable() {
        //speed.setEditable(false);
        //speed.setVisible(false);
        ////speed.setFocusable(false);
        //this.remove(speed);
        //this.removeAll();
    }
}