package com.iron59;

public class ServerInfo {
    String server; // IP or hostname
    int port; // port number
    String mode; // server or client

    public ServerInfo(String server, int port, String mode) {
        this.server = server;
        this.port = port;
        this.mode = mode;
    }
}
