package com.github.MehrabRahman;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private OutputStream output;
    private String status;
    private Map<String, String> headers;
    private byte[] body;

    public Response(OutputStream output) {
        this.output = output;
        this.headers = new HashMap<String, String>();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public void setBody(byte[] body) {
        this.body = body;
        headers.put("Content-Length", String.valueOf(body.length));
    }

    public void send() {
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("HTTP/1.1 " + status);
        headers.forEach((key, value) -> writer.println(key + ": " + value));
        writer.println();
        if (body != null) {
            try {
                output.write(body);
                writer.println();
            } catch (IOException e) {
                System.err.println("Error writing response body");
            }
        }
        writer.flush();
    }

    @Override
    public String toString() {
        return "Response [headers=" + headers + ", output=" + output + ", status="
                + status + "]";
    }
}