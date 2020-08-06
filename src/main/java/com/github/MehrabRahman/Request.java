package com.github.MehrabRahman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private BufferedReader reader;
    private String method;
    private String path;
    private Map<String, String> headers;

    public Request(InputStream input) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.headers = new HashMap<>();
        parse();
    }

    private void parse() {
        try {
            String requestLine = reader.readLine();
            String[] requestLineTokens = requestLine.split(" ");
            this.method = requestLineTokens[0];
            this.path = requestLineTokens[1];

            String header;
            while (reader.ready()) {
                header = reader.readLine();
                String[] headerTokens = header.split(": ");
                headers.put(headerTokens[0], headerTokens[1]);
            }
        } catch (IOException e) {
            System.err.println("Could not parse the request");
        }
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }
}