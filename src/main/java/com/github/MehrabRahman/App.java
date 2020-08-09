package com.github.MehrabRahman;

import com.github.MehrabRahman.http.Server;

public class App {
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getProperty("port", "8080"));

        Server server = new Server(port);
        server.addController("/health", (request, response) -> {
            response.setStatus("200 OK");
            response.setBody("Server OK".getBytes());
        });
        server.listen();
    }
}