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
        for(int i=0;i<70;i++) {
            if (i<30) {
                dis.move('y',.2);
                dis.setPlayerY(dis.getPlayerY()+.2);
            } else {
                dis.move('y',.1);
                dis.setPlayerY(dis.getPlayerY()+.1);
            }
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gr.unjump();
        System.out.println("finished jump");
        while(dis.getPlayerY()>0) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("finished jump wait");
        kt.unjump();
    }
}