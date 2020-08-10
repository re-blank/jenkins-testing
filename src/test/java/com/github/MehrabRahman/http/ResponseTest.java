package com.github.MehrabRahman.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ResponseTest
{
    BufferedReader reader;
    Response response;
    @Before
    public void setup() throws IOException
    {
        PipedOutputStream output = new PipedOutputStream();
        PipedInputStream input = new PipedInputStream();
        output.connect(input);
        reader = new BufferedReader(new InputStreamReader(input));
        response = new Response(output);
    }

    @Test
    public void sendStatusLineTest() throws IOException
    {
        response.setStatus("999");
        response.send();
        String statusLine = reader.readLine();
        assertEquals("HTTP/1.1", statusLine.split(" ")[0]);
        assertEquals("999", statusLine.split(" ")[1]);
        while(reader.ready())
        {
            reader.readLine();
        }

        response.setStatus("5643");
        response.send();
        statusLine = reader.readLine();
        assertEquals("HTTP/1.1", statusLine.split(" ")[0]);
        assertEquals("5643", statusLine.split(" ")[1]);
    }

    @Test
    public void sendHeaderTest() throws IOException
    {
        response.setHeader("Case1", "Passed");
        response.send();
        reader.readLine();
        String header = reader.readLine();
        assertEquals("Case1", header.split(":")[0].trim());
        assertEquals("Passed", header.split(":")[1].trim());
        while(reader.ready())
        {
            reader.readLine();
        }

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Case1", "Passed");
        headerMap.put("Case2", "Passed!");

        response.setHeader("Case2", "Passed!");
        response.send();
        reader.readLine();

        header = reader.readLine();
        String headerKey = header.split(":")[0].trim();
        String headerVal = header.split(":")[1].trim();
        assertTrue(headerMap.containsKey(headerKey));
        assertEquals(headerVal ,headerMap.get(headerKey));

        header = reader.readLine();
        headerKey = header.split(":")[0].trim();
        headerVal = header.split(":")[1].trim();
        assertTrue(headerMap.containsKey(headerKey));
        assertEquals(headerVal ,headerMap.get(headerKey));
    }

    @Test
    public void sendBodyTest() throws IOException
    {
        String bodyString = "Désolé, quand tu mens";
        response.setBody(bodyString.getBytes());
        response.send();

        reader.readLine();
        
        String header = reader.readLine();
        assertEquals("Content-Length", header.split(":")[0].trim());
        assertEquals(String.valueOf(bodyString.getBytes().length), header.split(":")[1].trim());
        
    }
}