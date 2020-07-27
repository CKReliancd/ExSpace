package ex03.pyrmont.connector.http;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {
    boolean stopped;
    private String scheme = "http";

    public String getScheme() { return scheme; }

    /**
     * 等待HTTP请求；
     * 为每个请求创建一个HttpProcessor实例
     * 调用HttpProcessor对象的process()方法
     */
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped) {
            //Accept the next incoming connection from the server socket
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (Exception e) {
                continue;
            }
            //Hand this socket off to an HttpProcessor
            HttpProcessor httpProcessor = new HttpProcessor(this);
            httpProcessor.process(socket);
        }
    }
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
