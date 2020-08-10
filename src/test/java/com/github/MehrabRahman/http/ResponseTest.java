package com.github.MehrabRahman.http;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import jdk.jfr.Timestamp;

public class ResponseTest {

    private final String[] _body = {"<h1>Hello World!</h1>", "Good Bye!", "File not found"};
    private final String _Content_Type ="Content-Type";
    private final String[] _content_types = {"text/html" , "text/plain"};
    private final String[] _status = {"200 OK", "404 File not found"};
    private String ok = "HTTP/1.1 200 OK\r\n";
    private String notFound = "HTTP/1.1 " + _status[1] + "\r\n";
    private String content_length = "Content-Length: ";
    private ByteArrayOutputStream _out;
    private Response _response;

    @Before
    public void emptyOutputStream(){
        _out = new ByteArrayOutputStream();
        _response = new Response(_out);

    }

    @After
    public void close_out(){
        try {
            _out.close();
        } catch (Exception e) {
            System.err.println("Failed to close output stream");    
        }
    }

    @Test
    public  void Html200Test(){
        _response.setStatus(_status[0]);
        _response.setHeader(_Content_Type, _content_types[0]);
        _response.setBody(_body[0].getBytes());

        _response.send();
        assertEquals(ok + content_length + _body[0].length() + "\r\n" + _Content_Type + ": " +_content_types[0] +"\r\n\r\n" + _body[0] +"\r\n", new String (_out.toByteArray()));
    }

    @Test
    public void PlainText200Test(){
    
         _response.setStatus(_status[0]);
         _response.setHeader(_Content_Type, _content_types[1]);
         _response.setBody(_body[1].getBytes());

         _response.send();
         assertEquals(ok + content_length + _body[1].length() + "\r\n" + _Content_Type + ": " +_content_types[1] +"\r\n\r\n" + _body[1] +"\r\n", new String (_out.toByteArray()));
    }

    @Test
    public void Html404Test(){
        _response.setStatus(_status[1]);
        _response.setHeader(_Content_Type, _content_types[0]);
        _response.setBody(_body[2].getBytes());

        _response.send();
        assertEquals(notFound + content_length + _body[2].length() + "\r\n" + _Content_Type + ": " +_content_types[0] +"\r\n\r\n" + _body[2] +"\r\n", new String (_out.toByteArray()));
    }


}