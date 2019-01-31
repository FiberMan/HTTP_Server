package com.filk.socket;

import com.filk.exceptions.ErrorType;
import com.filk.exceptions.ServerException;
import com.filk.http.*;
import com.filk.threads.ThreadDispatcher;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

class ConnectionHandler implements Runnable {
    private Socket socket;
    private Server.ServerType serverType;
    private String resourcePath;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ThreadDispatcher threadDispatcher;

    ConnectionHandler(Socket socket, Server.ServerType serverType, String resourcePath, ThreadDispatcher threadDispatcher) {
        try {
            this.socket = socket;
            this.serverType = serverType;
            this.resourcePath = resourcePath;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.threadDispatcher = threadDispatcher;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            threadDispatcher.checkNewThread();

            System.out.println("Connected:    " + socket.toString());
            if (serverType == Server.ServerType.ECHO) {
                processEcho();
            } else {
                processWeb();
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (SocketException e) {
            System.out.println("Disconnected: " + socket.toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception: " + socket.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Interrupted:  " + socket.toString());
            e.printStackTrace();
        } finally {
            threadDispatcher.releaseThread();
        }
    }

    private void processEcho() throws IOException {
        while (true) {
            String outString = "Echo: " + bufferedReader.readLine();
            bufferedWriter.write(outString);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    private void processWeb() {
        try {
            Request request = RequestParser.parseRequest(bufferedReader, resourcePath);
            Response.writeSuccessResponse(bufferedWriter, socket.getOutputStream(), request.getBodyContentInputStream());
        } catch(ServerException e) {
            Response.writeErrorResponse(bufferedWriter, e.getErrorType());
        } catch (Exception e) {
            Response.writeErrorResponse(bufferedWriter, ErrorType.INTERNAL_SERVER_ERROR);
        }
    }


}
