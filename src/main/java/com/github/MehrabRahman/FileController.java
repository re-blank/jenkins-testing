package com.github.MehrabRahman;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileController {
    private Request request;
    private Response response;

    public FileController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void service() {
        String path = request.getPath();
        InputStream resource = getClass().getResourceAsStream(path);
        try {
            String MIMEType = Files.probeContentType(Paths.get(path));
            // BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource));
            // String fileContent = fileReader.lines().collect(Collectors.joining("\n"));
            
    
            List<String> lines = Files.readAllLines(Paths.get("index.html"));
            String fileContent = "";
            for (String line : lines) {
                fileContent += line;
            }

            response.setStatus("200", "OK");
            response.setHeader("Content-Type", MIMEType);
            response.setBody(fileContent);
            response.send();
        } catch (IOException e) {
            System.err.println("Could not parse file type!");
        }
    }
}