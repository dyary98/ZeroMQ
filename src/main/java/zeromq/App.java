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

                    // by default is 1000 you can change it
                    s.setBacklog(1000);
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
        // when the Q fulls, it will stop the current thread, here we have another
        // thread
        // so it will stop that one and we can continue our works in the main thread

        int c = 0;
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
