package zeromq;

/**
 * Two Qs will be created one for the client side one for the server side but there functionalities are very different;
 * 
 *
 */
import org.zeromq.*;

public class Client {
    public static void main(String[] args) throws InterruptedException {

        int counter = 0;
        try (ZContext context = new ZContext()) {
            ZMQ.Socket s = context.createSocket(SocketType.PUSH);
            // why ZeroMQ over sockets
            // you can send the data to multyiple connections
            // if first one was not active it will send it to the secon and vice versa
            // if both available it will send the data in a roound robin way
            // so if we have to send 10 nums to these two servers it will
            // send the odd numbers to one of them and the evens to the other

            //
            // key difference between bind and connect?
            // bind and connect have nothing to do with the data flow, i can have bind when
            // i am sending data
            // with connect when you call it in that node a Q will be created regardless if
            // the port is open or not
            // with bind if nobody coneected to anything no Q is created
            // if a machine shwoed up with the bind a Q will be created if it went ofline
            // then the Q will be terminated imediatelly
            s.connect("tcp://localhost:4000");
            s.connect("tcp://localhost:5000");
            // the code inside the while loop's condition means as long as you havent
            // pressed control z in the
            // termnial, run the code so it is like a true condition but with some controls
            // over it.
            while (!Thread.currentThread().isInterrupted()) {
                String msg = "hell0" + counter++;
                System.out.println(msg);
                // slowing down the server to see the messages
                s.send(msg.getBytes(ZMQ.CHARSET), 0);
                Thread.sleep(1);
            }
        }
    }
}
