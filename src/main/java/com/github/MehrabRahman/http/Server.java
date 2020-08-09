package com.github.MehrabRahman.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ExecutorService threadPool;
    public static Map<String, Controller> container = new ConcurrentHashMap<>();

    public Server(int port) {
        try {
            System.out.println("Starting server on port " + port);
            serverSocket = new ServerSocket(port);
            threadPool = Executors.newFixedThreadPool(10);
            Server.container.put("/", new FileController());
            Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown()));
        } catch (IOException e) {
            System.err.println("Could not run server on port: " + port);
        }
    }

    private void shutdown() {
        System.out.println("Shutting down the server...");
        try {
            serverSocket.close();
            threadPool.shutdown();
        } catch (Exception e) {
            System.err.println("Failed to shutdown server!");
        }
    }

    public void listen() {
        try {
            while ((clientSocket = serverSocket.accept()) != null) {
                clientSocket.setSoTimeout(10 * 1000);
                threadPool.execute(new Handler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Could not create client instance");
        }
    }

    public void addController(String path, Controller controller) {
        Server.container.put(path, controller);
    }
    
    private class Handler implements Runnable {
        private final Socket clientSocket;

        private Handler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                Request request = new Request(clientSocket.getInputStream());
                Response response = new Response(clientSocket.getOutputStream());
                Controller controller = Server.container.get(request.getPath());
                if (controller == null) {
                    controller = Server.container.get("/");
                }
                controller.service(request, response);
                response.send();
            } catch (IOException e) {
                System.err.println("Could not create a Request/Response object");
            }
        }
    }
}
