package com.iron59;

import java.io.DataOutputStream;
import java.net.Socket;

public class SocketClient {

    public static void transmit(String server, int port) {
        System.out.println("Transmitting to " + server + ":" + port);
        
        Socket socket = null;
        DataOutputStream dataOutputStream = null;

        try {
            socket = new Socket(server, port);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int i = 0;
            while (true) {
                dataOutputStream.writeUTF("Hello from " + socket.getLocalSocketAddress() + " " + (i+1));    
                Thread.sleep(1000);
                i += 1;
            }
        } catch (Exception e) {
            try {
                // I know, nested try-catch, but I'm lazy
                dataOutputStream.flush();
                dataOutputStream.close();
                socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
