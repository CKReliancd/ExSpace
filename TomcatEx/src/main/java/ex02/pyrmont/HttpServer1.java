package ex02.pyrmont;

import ex01.pyrmont.HttpServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdowmn = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await(){

        ServerSocket serverSocket  = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,
                    1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Loop waiting for a request
        while (!shutdowmn){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                //create Request Object and parse
                Request request = new Request(inputStream);
                request.parse();

                //create Response object
                Response response = new Response(outputStream);
                response.setRequest(request);

                //check if this is a request for a servlet or
                //a static resource
                //a request for a servlet begin with "/servlet/"
                if(request.getUri().startsWith("/servlet/")) {
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request,response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request,response);
                }
                //Close the socket
                socket.close();
                //check if the previous URI is a shutdown command
                shutdowmn = request.getUri().equals(SHUTDOWN_COMMAND);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        
        
        
        
    }

}
