import java.awt.*;
import javax.swing.*;
public class SettingsMenu extends JComponent {
    boolean dis = false;
    JTextField speed = new JTextField();
    boolean complete = false;
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
        Font f = new Font("Courier New", Font.BOLD, 100);
        g.setFont(f);
        g.drawString("SETTINGS", 30, 100);
        //speed.setBounds(150,400,80,30);
        Font f2 = new Font("Courier New", Font.BOLD,50);
        g.setFont(f2);
        //g.drawString("TOTAL BLOCKS",);
        g.drawString("SPEED",110,330);
        g.drawString("FPS",170,480);
        g.drawString("BLOCKS",80,630);
    }
    public void disable() {
        //speed.setEditable(false);
        //speed.setVisible(false);
        ////speed.setFocusable(false);
        //this.remove(speed);
        //this.removeAll();
    }
    public void setComplete() {
        complete=true;
    }
    public boolean getComplete() {
        return complete;
    }
}