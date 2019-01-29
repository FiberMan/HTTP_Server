package com.filk.http;

import com.filk.exceptions.ErrorType;

import java.io.*;
import java.util.Arrays;

public class Response {
    private static final String HTTP_VERSION = "HTTP/1.0";

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
