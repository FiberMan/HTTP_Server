package com.filk.http;

import com.filk.exceptions.ErrorType;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Response {
    private static final String HTTP_VERSION = "HTTP/1.0";
//    private List<String> responseStrings;
//    private ResponseCode responseCode;

//    public Response(ResponseCode responseCode, List<String> responseBody) {
//        this.responseCode = responseCode;
//        responseStrings = new ArrayList<>();
//        responseStrings.add(HTTP_VERSION + " " + responseCode.getCode() + " " + responseCode.getMessage());
//        responseStrings.add("");
//        responseStrings.addAll(responseBody);
//    }
//
//    public List<String> getResponseStrings() {
//        return responseStrings;
//    }

//    public ResponseCode getResponseCode() {
//        return responseCode;
//    }

    public static void writeSuccessResponse(BufferedWriter bufferedWriter, OutputStream outputStream, InputStream inputStream) {
        try {
            bufferedWriter.write(HTTP_VERSION + " 200 OK");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.flush();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(Arrays.copyOf(buffer, bytesRead));
            }
            bufferedOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeErrorResponse(BufferedWriter bufferedWriter, ErrorType errorType) {
        try {
            bufferedWriter.write(HTTP_VERSION + " " + errorType.getStatusLine());
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
