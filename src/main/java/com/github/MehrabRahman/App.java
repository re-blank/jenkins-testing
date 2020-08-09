package com.github.MehrabRahman;

public class App {
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getProperty("port", "8080"));

        Server server = new Server(port);
        server.listen();
    }
}