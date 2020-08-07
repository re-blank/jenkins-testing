package com.github.MehrabRahman;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ExecutorService threadPool;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown()));
            // Runtime.getRuntime().addShutdownHook(new Thread(() -> System.err.println("Shutting down from lambda!")));
            // Runtime.getRuntime().addShutdownHook(new Thread() {
            //     @Override
            //     public void run() {
            //         System.err.println("Shutting down from anonymous class!");
            //     }
            // });
            this.threadPool = Executors.newFixedThreadPool(10);
        } catch (IOException e) {
            System.err.println("Could not run server on port: " + port);
        }
    }

    private void shutdown() {
        System.out.println("Shutting down the server...");
        try {
            this.serverSocket.close();
        } catch (Exception e) {
            System.err.println("Failed to shutdown server!");
        }
    }

    public void listen() {
        try {
            while ((this.clientSocket = serverSocket.accept()) != null) {
                this.threadPool.execute(new Handler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Could not create client instance");
        }
    }
    
    private class Handler implements Runnable {
        private Socket clientSocket;

        private Handler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                Request request = new Request(clientSocket.getInputStream());
                Response response = new Response(clientSocket.getOutputStream());
                new FileController(request, response).service();
            } catch (IOException e) {
                System.err.println("Could not create a Request/Response object");
            }
        }
    }
}
