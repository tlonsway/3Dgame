public class UpdateThread implements Runnable {
    Display dis;
    double ups = 60;
    //updates per second
    public UpdateThread(Display d) {
        dis=d;
    }
    public UpdateThread(Display d, int updatespersecond) {
        dis=d;
        ups=updatespersecond;
    }
    public void run() {
        while(true) {
            if (!dis.getPaused()) {
                dis.draw();
            }
            try {
                Thread.sleep((int)(1000/ups));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
}