package com.github.MehrabRahman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not run server on port: " + port);
        }
    }

    public void listen() {
        try {
            this.clientSocket = serverSocket.accept();
            handle();
        } catch (IOException e) {
            System.err.println("Could not create client instance");
        }
    }

    private void handle() {
        request();
        response();
    }

    private void request() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            System.err.println("Something went wrong reading the request");
        }
    }

    private void response() {
        try {
            String body = "<h1>Hello, World</h1>";
            PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
            printStream.println("HTTP/1.1 200 OK");
            printStream.println("Connection: Close");
            printStream.println("Content-Type: text/html");
            printStream.println("Content-Length: " + body.length());
            printStream.println();
            printStream.println(body);
            printStream.println();
            printStream.flush();
        } catch (IOException e) {
            System.err.println("Something went wrong writing the response");
        }
    }
}
