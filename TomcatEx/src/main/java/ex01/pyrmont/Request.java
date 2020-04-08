package ex01.pyrmont;

import java.io.InputStream;

public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input){
        this.input = input;
    }

    public void parse(){
       StringBuffer request = new StringBuffer(2048);
       int i;
       byte[] buffer = new byte[2048];










    }









}
