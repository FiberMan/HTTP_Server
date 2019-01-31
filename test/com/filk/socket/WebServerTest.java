package com.filk.socket;

import java.io.IOException;

public class WebServerTest {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.setServerType(Server.ServerType.WEB);
        server.setMaxThreadCount(2);
        server.setResourcePath("resources\\webapp");
        server.start();
    }
}