package com.github.MehrabRahman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Request is a class whose job is to parse a incoming socket's InputStream.
 * The method, path, and headers are stored to fields, which will then be passed
 * to a Response object.
 * 
 * @author MehrabRahman
 * @version 1.0.0
 */
public class Request {
    private BufferedReader reader;
    private String method;
    private String path;
    private Map<String, String> headers;

    /**
     * Request constructor requires an InputStream from a Socket. It will construct a HashMap and then call a parse function.
     */
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
                if (header.contains(":")) {
                    String[] headerTokens = header.split(": ");
                    headers.put(headerTokens[0], headerTokens[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not parse the request");
        }
    }

    /**
     * Returns the HTTP Verb of the request.
     * 
     * @return HTTP method
     */
    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String toString() {
        return "Request [headers=" + headers + ", method=" + method + ", path=" + path + ", reader=" + reader + "]";
    }    
}