package com.github.MehrabRahman;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.github.MehrabRahman.http.Server;

public class App {
    public static void main(String[] args) throws SecurityException, IOException {
        Logger logger = Logger.getLogger(App.class.getName());
        FileHandler fileHandler = new FileHandler("server.log");
        logger.addHandler(fileHandler);
        
        int port = Integer.parseInt(System.getProperty("port", "8080"));
        logger.info("port is set to " + port);
        Server server = new Server(port);
        server.addController("/health", (request, response) -> {
            response.setStatus("200 OK");
            response.setBody("Server OK".getBytes());
        });
        server.listen();
    }
}