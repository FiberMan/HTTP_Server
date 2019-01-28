package com.filk.io;
import java.io.IOException;
import java.io.OutputStream;


// decorator
public class BufferedOutputStream extends OutputStream {
    private OutputStream outputStream;
    private byte[] buffer = new byte[5];
    private int index;

    public BufferedOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        if (index == buffer.length) {
            flush();
        }
        buffer[index] = (byte) b;
        index++;
    }

    // 8192
    // write byte[] 128
    @Override
    public void write(byte[] b) throws IOException {
        // TODO
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        // TODO
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(buffer, 0, index);
        index = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        outputStream.close();
    }
}