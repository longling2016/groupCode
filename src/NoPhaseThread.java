import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Chesley on 6/1/17.
 * This thread is going to listen to the ServerSocket for NoPhase class
 */
public class NoPhaseThread implements Runnable {
    ServerSocket ss;

    public NoPhaseThread(ServerSocket ss) {
        this.ss = ss;
    }

    public void run() {
        DataInputStream dIn = null;
        Socket socket;
        try {
            while (true && !NoPhase.terminate) {
                socket = ss.accept();
                dIn = new DataInputStream(socket.getInputStream());

                String message = dIn.readUTF();
//                System.out.println("no phase: " + message);
                NoPhase.noPhaseListen(message);
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            if (dIn != null) {
                try {
                    dIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

