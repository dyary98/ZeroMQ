package zeromq;

/**
 * Hello world!
 *
 */
import org.zeromq.*;

public class App {
    public static void main(String[] args) {

        Thread t = new Thread() {
            int counter = 0;

            @Override
            public void run() {
                try (ZContext context = new ZContext()) {
                    ZMQ.Socket s = context.createSocket(SocketType.PUSH);
                    s.setBacklog(500);
                    s.connect("tcp://localhost:4000");
                    while (true) {
                        String msg = "hell0" + counter++;
                        System.out.println(msg);
                        s.send(msg.getBytes(ZMQ.CHARSET), 0);
                    }
                }
            };
        };
        t.start();
        System.out.println("Hello World!");
        int c = 0;
        for (int i = 0; i < 1000; i++) {
            System.out.println("slaw");
        }

        try {
            Thread.sleep(5000);
            while (c < 1010) {
                c++;
                System.out.println("hi");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
