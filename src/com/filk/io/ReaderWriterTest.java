package com.filk.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReaderWriterTest {
    public static void main(String[] args) throws IOException {
        ReaderWriter readerWriter = new ReaderWriter();
        List<String> stringLines = readerWriter.readLines(new FileInputStream("test.log"));

        readerWriter.writeLines(new FileOutputStream("test1.log"), stringLines);

    }
}
