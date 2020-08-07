package com.github.MehrabRahman;

public class App {
    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        System.out.println("Starting server on port " + port);
        server.listen();
    }
}