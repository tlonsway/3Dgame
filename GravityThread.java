public class GravityThread implements Runnable {
    Display dis;
    boolean jumping;
    public GravityThread(Display d) {
        dis=d;
        jumping=false;
    }
    public void run() {
        try {
            while(true) {
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
                    if (dis.getGround()>20||dis.getGround()<-.1) {
                        dis.move('y',-.07);
                    }
                    /*if (dis.getCollision()==false) {
                        dis.move('y',-.025);
                    }*/
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }  
            }
        } catch (Exception e) {
            try{
                (new Thread(new GravityThread(dis))).start();
                System.out.println("Gravity thread crashed but was restarted due to error: " + e);
            }catch (Exception e2) {
                System.out.println("Gravity threading dead - program will break");
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