package com.github.MehrabRahman;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {
    String testString = "GET / HTTP/1.1\r\nHost: revature.com\r\nUser-Agent: curl/7.71.1\r\nAccept: */*";
    String testString2 = "PUT /about HTTP/1.1\r\nHost: revature.com\r\nUser-Agent: curl/7.71.1\r\nAccept: */*";
    Request getRequest;
    Request putRequest;
        
    @Before
    public void setup() {
        InputStream input = new ByteArrayInputStream(testString.getBytes());
        getRequest = new Request(input);
        input = new ByteArrayInputStream(testString2.getBytes());
        putRequest = new Request(input);
    }

    @Test
    public void parseMethodTest() {
        String expectedMethod = "GET";
        String actualMethod = getRequest.getMethod();
        assertEquals(expectedMethod, actualMethod);

        expectedMethod = "PUT";
        actualMethod = putRequest.getMethod();
        assertEquals(expectedMethod, actualMethod);
    }

    @Test
    public void parseHeadersTest() {
        Map<String, String> headers = getRequest.getHeaders();
        String expectedHost = "revature.com";
        String actualHost = headers.get("Host");
        assertEquals(expectedHost, actualHost);
    }

    @Test
    public void parsePathTest() {
        String expected = "/";
        String actual = getRequest.getPath();
        assertEquals(expected, actual);

        expected = "/about";
        actual = putRequest.getPath();
        assertEquals(expected, actual);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void parseRequestExceptionTest() {
        new Request(null);
    }
}