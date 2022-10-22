package com.iron59;

import java.io.DataInputStream;
import java.io.EOFException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void startServer(int port) {
        ServerSocket serverSocket = null;
        Socket s = null;

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                s = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                while (true) {
                    try {
                        System.out.println(dataInputStream.readUTF());
                    } catch (EOFException e) {
                        break;
                    }
                }
                dataInputStream.close();
                s.close();
            }
        } catch (EOFException _) {
            // gracefully close the connection when the client is done
            try {
                s.close();
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
