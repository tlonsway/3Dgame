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
        for(int i=0;i<60;i++) {
            dis.move('y',.05);
            dis.setPlayerY(dis.getPlayerY()+.05);
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        gr.unjump();
        while(dis.getPlayerY()>0) {
            System.out.println(dis.getPlayerY());
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        kt.unjump();
    }
}