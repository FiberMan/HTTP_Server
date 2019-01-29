package com.filk.http;

import com.filk.exceptions.ErrorType;
import com.filk.exceptions.ServerException;
import com.filk.io.ReaderWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {
    private static final String DEFAULT_PAGE = "/index.html";

    public static Request parseRequest(BufferedReader bufferedReader, String resourcePath) {
        try {
            Request request = new Request();
            List<String> requestStrings = ReaderWriter.readLines(bufferedReader);
            injectRequestLine(request, requestStrings.get(0));
            injectHeaders(request, requestStrings);
            injectInputStream(request, resourcePath);

            return request;
        } catch (ServerException e) {
            throw e;
        } catch (Exception e) {
            throw new ServerException(ErrorType.BAD_REQUEST);
        }
    }

    private static void injectRequestLine(Request request, String requestLine) {
        System.out.println("Original request: " + requestLine);
        String[] strings = requestLine.split(" ");
        request.setHttpMethod(HttpMethod.getByName(strings[0]));
        request.setHttpUri(strings[1].equals("/") ? DEFAULT_PAGE : strings[1]);
        request.setHttpVersion(strings[2]);
    }

    private static void injectHeaders(Request request, List<String> requestStrings) {
        Map<String, String> headers = new HashMap<>();
        String line;
        int i = 1;
        while (i++ < requestStrings.size() && !(line = requestStrings.get(i)).equals("")) {
            String[] header = line.split(": ");
            if (header.length == 2) {
                headers.put(header[0], header[1]);
            }
        }
        request.setHeaders(headers);
    }

    private static void injectInputStream(Request request, String resourcePath) {
        if (request.getHttpMethod() == HttpMethod.GET) {
            try {
                String filePath = resourcePath + File.separator + request.getHttpUri().replace('/', File.separatorChar);
                File file = new File(filePath);
                request.setBodyContentInputStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                throw new ServerException(ErrorType.NOT_FOUND);
            }
        }
    }

}
