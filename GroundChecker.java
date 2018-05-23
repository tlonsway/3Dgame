public class GroundChecker implements Runnable {
    Display dis;
    public GroundChecker(Display d) {
        dis=d;
    }
    public void run() {
        while(true) {
            try {
                if (dis.getGround()>10&&dis.getGround()<19) {
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
            } catch (Exception e) {
                try {
                    (new Thread(new GroundChecker(dis))).start();
                    System.out.println("GroundChecker crashed but was successfully restarted");
                } catch (Exception e2) {
                    System.out.println("GroundChecker threading service has died - program will break");
                }
            }
        }
    }
}