public class GravityThread implements Runnable {
    Display dis;
    boolean jumping;
    public GravityThread(Display d) {
        dis=d;
        jumping=false;
    }
    public GravityThread(Display d, boolean jump) {
        dis=d;
        jumping=jump;
    }
    public void run() {
        boolean r = true;
        while(r) {
            try {
                //System.out.println("loop");
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while(!jumping && !dis.getPaused()) {
                    //System.out.println("Player Y:" + dis.getPlayerY());
                    //System.out.println("Current ground level: " + dis.getGround());
                    /*if (dis.getPlayerY()>dis.getGround()||dis.getPlayerY()<dis.getGround()) {
                        dis.move('y',-.025);
                        //dis.setPlayerY((dis.getPlayerY()-.025));
                        //System.out.println("moving");
                    }*/
                    if (dis.getGround()>20||dis.getGround()<10/*-.1*/) {
                        dis.move('y',-.2);
                    }
                    /*if (dis.getCollision()==false) {
                        dis.move('y',-.025);
                    }*/
                    try {
                        Thread.sleep(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }  
            } catch (Exception e) {
                try{
                    r=false;
                    (new Thread(new GravityThread(dis,jumping))).start();
                    System.out.println("Gravity thread crashed but was restarted due to error: " + e);
                }catch (Exception e2) {
                    System.out.println("Gravity threading dead - program will break");
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