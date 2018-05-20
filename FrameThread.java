public class FrameThread implements Runnable {
    Display dis;
    double fps = 45;
    public FrameThread(Display d) {
        dis=d;
    }
    public void run() {
        while(true) {
            dis.draw();
            try {
                Thread.sleep((int)(1000/fps));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
}