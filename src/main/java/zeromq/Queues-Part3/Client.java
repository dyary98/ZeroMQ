import org.zeromq.*;

public class Client {
    public static void main(String[] args) throws InterruptedException {

        int counter = 0;
        try (ZContext context = new ZContext()) {
            ZMQ.Socket s = context.createSocket(SocketType.CLIENT);
            s.connect("tcp://localhost:4000");
            s.connect("tcp://localhost:5000");
            while (!Thread.currentThread().isInterrupted()) {
                String msg = "hell0" + counter++;
                System.out.println(msg);
                s.send(msg.getBytes(ZMQ.CHARSET), 0);
                Thread.sleep(1);
            }
        }
    }
}
