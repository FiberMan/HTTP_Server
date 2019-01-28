package com.filk.io;
import java.io.*;

public class IOTest {
    public static void main(String[] args) throws IOException {
        String content = "Hello world";
//        FileOutputStream fileOutputStream = new FileOutputStream("test.log");
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//        try {
//            write(bufferedOutputStream, content);
//        } finally {
//            bufferedOutputStream.close();
//        }

        //BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("test.log"));
        //read(bufferedInputStream);

    }

    static void write(OutputStream outputStream, String content) throws IOException {
        for (byte b : content.getBytes()) {
            outputStream.write(b);
        }

    }

    static void read(InputStream inputStream) throws IOException {
        int value;
        while ((value = inputStream.read()) != -1) {
            System.out.print((char) value);
        }
    }
}
