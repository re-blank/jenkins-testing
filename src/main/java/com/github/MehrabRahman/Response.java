package com.github.MehrabRahman;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private OutputStream output;
    private String statusCode;
    private String statusMessage;
    private Map<String, String> headers;
    private String body;

    public Response(OutputStream output) {
        this.output = output;
        this.headers = new HashMap<String, String>();
    }

    public void setStatus(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public void setBody(String body) {
        this.body = body;
        headers.put("Content-Length", new Integer(body.length()).toString());
    }

    public void send() {
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("HTTP/1.1 " + statusCode + " " + statusMessage);
        headers.put("Connection", "Close");
        headers.forEach((key, value) -> writer.println(key + ": " + value));
        writer.println();
        if (body != null) {
            writer.println(body);
            try {
                output.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}