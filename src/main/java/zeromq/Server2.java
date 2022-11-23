package zeromq;

/**
 * Hello world!
 *
 */
import org.zeromq.*;

public class Server2 {
    public static void main(String[] args) throws InterruptedException {

        int counter = 0;
        try (ZContext context = new ZContext()) {
            ZMQ.Socket s = context.createSocket(SocketType.PULL);
            s.bind("tcp://localhost:5000");

            while (!Thread.currentThread().isInterrupted()) {
                // recv methos returns back the message in binary for you
                byte[] data = s.recv(0);
                // then we have to convert the message(which is an array of binary data)
                // from binary into String
                String msg = new String(data, ZMQ.CHARSET);
                System.out.println(msg);
                // slowing down the server to see the messages
                Thread.sleep(200);
            }
        }
    }
}
