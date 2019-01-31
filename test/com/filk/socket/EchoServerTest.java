package com.filk.socket;

import java.io.*;

public class EchoServerTest {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.setServerType(Server.ServerType.ECHO);
        server.setMaxThreadCount(2);
        server.start();
    }
}

