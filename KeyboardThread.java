import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class KeyboardThread extends KeyAdapter { 
    Display dis;
    GravityThread gt;
    boolean jumping;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println("GOT KEY PRESS:" + key);
        if (key == KeyEvent.VK_A) {
            dis.move('x', .5);
        }
        if (key == KeyEvent.VK_D) {
            dis.move('x',-.5);
        }
        if (key == KeyEvent.VK_W) {
            dis.move('z', -.5);
        }
        if (key == KeyEvent.VK_S) {
            dis.move('z', .5);
        }
        if (key == KeyEvent.VK_SPACE) {
            if (!jumping) {
                (new Thread(new JumpingThread(dis,gt,this))).start();
            }
        }
        /*
        if (key == KeyEvent.VK_CONTROL) {
            dis.move('y', -.5);
        }/*
        /*if (key == KeyEvent.VK_Q) {
            dis.look('z', .1);
        }
        if (key == KeyEvent.VK_E) {
            dis.look('z', -.1);
        }*/
    }
    public void setDrawingPlane(Display d) {
        dis=d;
    }
    public KeyboardThread(Display d, GravityThread g) {
        dis=d;
        gt=g;
        jumping=false;
    }
    public void jump() {
        jumping=true;
    }
    public void unjump() {
        jumping=false;
    }  
}       