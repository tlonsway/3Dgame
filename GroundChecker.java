public class GroundChecker implements Runnable {
    Display dis;
    public GroundChecker(Display d) {
        dis=d;
    }
    public void run() {
        while(true) {
            if (dis.getGround()>0&&dis.getGround()<18) {
                //dis.move('y', 20-dis.getGround());
                double dist = (20-dis.getGround())/100;
                for(int i=0;i<100;i++) {
                    dis.move('y', dist);
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}