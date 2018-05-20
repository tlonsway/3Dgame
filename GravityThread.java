public class GravityThread implements Runnable {
    Display dis;
    boolean jumping;
    public GravityThread(Display d) {
        dis=d;
        jumping=false;
    }
    public void run() {
        while(true) {
            //System.out.println("loop");
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while(!jumping) {
                if (dis.getPlayerY()>.025) {
                    dis.move('y',-.025);
                    //dis.setPlayerY((dis.getPlayerY()-.025));
                    //System.out.println("moving");
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