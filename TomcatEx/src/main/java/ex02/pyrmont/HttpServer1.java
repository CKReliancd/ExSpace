package ex02.pyrmont;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdowmn = false;

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

                //create Request



            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        
        
        
        
    }

}
