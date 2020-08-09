package com.github.MehrabRahman.http;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileController implements Controller {

    public void service(Request request, Response response) {
        String path = request.getPath();
        if ("/".equals(path)) {
            path = "/index.html";
        }

        Path file = Paths.get(path.substring(1));
        try {
            String MIMEType = Files.probeContentType(file);
            response.setStatus("200 OK");
            response.setHeader("Content-Type", MIMEType);
            response.setBody(Files.readAllBytes(file));
        } catch (IOException e) {
            System.err.println("File not found");
            response.setStatus("404 File Not Found");
            response.setHeader("Content-Type", "text/html");
            response.setBody("<h1>File Not Found!</h1>".getBytes());
        }
    }
}