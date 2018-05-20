public class GravityThread implements Runnable {
    Display dis;
    boolean jumping;
    public GravityThread(Display d) {
        dis=d;
        jumping=false;
    }
    public void run() {
        while(true) {
            if (!jumping) {
                if (dis.getPlayerY()>0) {
                    dis.move('y',-.01);
                    dis.setPlayerY(dis.getPlayerY()-.01);
                }
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    
    }
    public void jump() {
        jumping=true;
    }
    public void unjump() {
        jumping=false;
    }
}