package com.github.MehrabRahman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileController {
    private Request request;
    private Response response;

    public FileController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void service() {
        Path path = Paths.get(request.getPath().substring(1));
        try {
            String MIMEType = Files.probeContentType(path);
            response.setStatus("200 OK");
            response.setHeader("Content-Type", MIMEType);
            response.setBody(Files.readAllBytes(path));
        } catch (IOException e) {
            System.err.println("File not found");
            response.setStatus("404 File Not Found");
            response.setHeader("Content-Type", "text/html");
            response.setBody("<h1>File Not Found!</h1>".getBytes());
        }
    }
}