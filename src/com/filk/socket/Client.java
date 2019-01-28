package com.filk.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 3000);
        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        while (true) {
            String s = scanner.nextLine();
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println(bufferedReader.readLine());
        }
    }
}