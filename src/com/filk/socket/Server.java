package com.filk.socket;

import com.filk.threads.ThreadDispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerType serverType;
    private String resourcePath;
    private int maxThreadCount = 1;

    public Server() {}

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started");

            ThreadDispatcher threadDispatcher = new ThreadDispatcher(maxThreadCount);

            while (true) {
                Socket socket = serverSocket.accept();
                ConnectionHandler connection = new ConnectionHandler(socket, serverType, resourcePath, threadDispatcher);
                Thread thread = new Thread(connection);
                thread.start();
            }
        }
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

    public void setMaxThreadCount(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    enum ServerType {
        ECHO, WEB
    }
}

