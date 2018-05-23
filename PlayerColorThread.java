import java.awt.*;
public class PlayerColorThread implements Runnable {
    Display dis;
    public PlayerColorThread(Display d) {
        dis=d;
    }
    public void run() {
        int red=244;
        int green=66;
        int blue=66;
        int sleep=10;
        while(true) {
            for(int g=66;g<244;g++) {
                green=g;
                dis.changePlayerColor(new Color(red,green,blue));
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for(int r=244;r>66;r--) {
                red=r;
                dis.changePlayerColor(new Color(red,green,blue));
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for(int b=66;b<244;b++) {
                blue=b;
                dis.changePlayerColor(new Color(red,green,blue));
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for(int g=244;g>66;g--) {
                green=g;
                dis.changePlayerColor(new Color(red,green,blue));
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for(int r=66;r<244;r++) {
                red=r;
                dis.changePlayerColor(new Color(red,green,blue));
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for(int b=244;b>66;b--) {
                blue=b;
                dis.changePlayerColor(new Color(red,green,blue));
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}