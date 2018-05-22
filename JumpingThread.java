public class JumpingThread implements Runnable {
    Display dis;
    GravityThread gr;
    KeyboardThread kt;
    public JumpingThread(Display d, GravityThread g, KeyboardThread k) {
        dis=d;
        gr=g;
        kt=k;
    }
    public void run() {
        gr.jump();
        kt.jump();
        //System.out.println("before jump player height: " + dis.getPlayerY());
        for(int i=0;i<70;i++) {
            if (i<30) {
                dis.move('y',0.3);
                //dis.setPlayerY(dis.getPlayerY()+0.2);
            } else {
                dis.move('y',.2);
                //dis.setPlayerY(dis.getPlayerY()+0.1);
            }
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println("after jump player height: " + dis.getPlayerY());
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gr.unjump();
        //System.out.println("after jump waiting finished height: " + dis.getPlayerY());
        //System.out.println("finished jump");
        while(dis.getGround()>22) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println("after hit ground height: " + dis.getPlayerY());
        //System.out.println("finished jump wait");
        kt.unjump();
    }
}