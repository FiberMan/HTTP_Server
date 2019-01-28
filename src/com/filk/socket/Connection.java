package com.filk.socket;

import com.filk.http.HttpMethod;
import com.filk.http.Request;
import com.filk.http.Response;
import com.filk.http.ResponseCode;
import com.filk.io.ReaderWriter;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

class Connection implements Runnable {
    private Socket socket;
    private Server.ServerType serverType;
    private String resourcePath;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    Connection(Socket socket, Server.ServerType serverType, String resourcePath) {
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
        ResponseCode responseCode;
        List<String> responseBody = new ArrayList<>();
        try {
            Request request = new Request(bufferedReader);
            if (request.isGoodRequest() && request.getHttpMethod() == HttpMethod.GET) {
                String filePath =  resourcePath + File.separator + request.getHttpUri().replace('/', File.separatorChar);
                File file = new File(filePath);
                if (file.exists()) {
                    responseCode = ResponseCode.OK;
                    responseBody = ReaderWriter.readLines(new FileInputStream(file));
                } else {
                    responseCode = ResponseCode.NOT_FOUND;
                }
            } else {
                responseCode = ResponseCode.BAD_REQUEST;
            }
        } catch (IOException e) {
            responseCode = ResponseCode.SERVER_ERROR;
        }

        Response response = new Response(responseCode, responseBody);
        ReaderWriter.writeLines(socket.getOutputStream(), response.getResponseStrings());

        bufferedReader.close();
        bufferedWriter.close();
    }
}
