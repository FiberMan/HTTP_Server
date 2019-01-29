package com.filk.socket;

import com.filk.exceptions.ErrorType;
import com.filk.exceptions.ServerException;
import com.filk.http.*;
import com.filk.io.ReaderWriter;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

class ConnectionHandler implements Runnable {
    private Socket socket;
    private Server.ServerType serverType;
    private String resourcePath;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    ConnectionHandler(Socket socket, Server.ServerType serverType, String resourcePath) {
        try {
            this.socket = socket;
            this.serverType = serverType;
            this.resourcePath = resourcePath;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Connected:    " + socket.toString());
            if (serverType == Server.ServerType.ECHO) {
                processEcho();
            } else {
                processWeb();
            }
        } catch (SocketException e) {
            System.out.println("Disconnected: " + socket.toString());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

    private void processWeb() throws IOException {
        //List<String> responseBody = new ArrayList<>();
        //InputStream inputStream;
        try {
            // request parser
            // content reader
            Request request = RequestParser.parseRequest(bufferedReader, resourcePath);


            // response writer
            //Response response = new Response();
            Response.writeSuccessResponse(bufferedWriter, socket.getOutputStream(), request.getBodyContentInputStream());

        } catch(ServerException e) {
            Response.writeErrorResponse(bufferedWriter, e.getErrorType());
        } catch (Exception e) {
            Response.writeErrorResponse(bufferedWriter, ErrorType.INTERNAL_SERVER_ERROR);
        }


//        Response response = new Response(responseCode, responseBody);
//        ReaderWriter.writeLines(socket.getOutputStream(), response.getResponseStrings());

        bufferedReader.close();
        bufferedWriter.close();
    }


}
