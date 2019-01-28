package com.filk.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderWriter {

    public static List<String> readLines(InputStream inputStream) throws IOException {
        return readLines(new BufferedReader(new InputStreamReader(inputStream)));
    }

    public static List<String> readLines(BufferedReader bufferedReader) throws IOException {
        List<String> result = new ArrayList<>();

        String line;
        while (bufferedReader.ready() && (line = bufferedReader.readLine()) != null) {
            result.add(line);
        }
        //bufferedReader.close();
        return result;
    }


    public static void writeLines(OutputStream outputStream, List<String> stringList) throws IOException {
        writeLines(new BufferedWriter(new OutputStreamWriter(outputStream)), stringList);
    }

    public static void writeLines(BufferedWriter bufferedWriter, List<String> stringList) throws IOException {
        for (String s : stringList) {
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        //bufferedWriter.close();
    }
}
