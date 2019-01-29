package com.filk.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerType serverType;
    private String resourcePath;
    private final int MAX_THREAD_COUNT = 2;

    public Server() {
    }

    public Server(int port, ServerType serverType) {
        this.port = port;
        this.serverType = serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started");

            while (true) {
                    Socket socket = serverSocket.accept();          // почему сокет закрывается, если его взять в скобки: try (Socket....)   ?
                    ConnectionHandler connection = new ConnectionHandler(socket, serverType, resourcePath);
                    Thread thread = new Thread(connection);
                    thread.start();
            }
        }
    }

    enum ServerType {
        ECHO, WEB
    }
}

