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
        for(int i=0;i<100;i++) {
            dis.move('y',.01);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        gr.unjump();
        kt.unjump();
    }
}