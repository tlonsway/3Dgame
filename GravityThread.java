public class GravityThread implements Runnable {
    Display dis;
    public GravityThread(Display d) {
        dis=d;
    }
    public void run() {
        while(true) {
            if (dis.getPlayerY()>0) {
                dis.move('y',-.01);
                dis.setPlayerZ(dis.getPlayerY()-.01);
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }    
    }
}