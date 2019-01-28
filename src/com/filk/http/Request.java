package com.filk.http;

import com.filk.io.ReaderWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private HttpMethod httpMethod;
    private String httpUri;
    private String httpVersion;
    private Map<String, String> headers = new HashMap<>();
    private boolean isGoodRequest = true;

    public Request(BufferedReader bufferedReader) {
        try {
            List<String> requestStrings = ReaderWriter.readLines(bufferedReader);
            if (!requestStrings.isEmpty()) {
                String[] strings = requestStrings.get(0).split(" ");
                httpMethod = HttpMethod.getByName(strings[0]);
                httpUri = strings[1];
                httpVersion = strings[2];

                String line;
                int i = 1;
                while (i++ < requestStrings.size() && !(line = requestStrings.get(i)).equals("")) {
                    String[] header = line.split(": ");
                    if (header.length == 2) {
                        headers.put(header[0], header[1]);
                    }
                }
            } else {
                isGoodRequest = false;
            }
        } catch (IOException | IllegalArgumentException e) {
            isGoodRequest = false;
        }
        System.out.println(isGoodRequest ? "Request: " + httpMethod + " " + httpUri : "Empty request");
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getHttpUri() {
        return httpUri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public boolean isGoodRequest() {
        return isGoodRequest;
    }
}