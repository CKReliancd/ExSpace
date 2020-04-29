package ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    /**
     * WEB_ROOT是HTML和其他文件所在的目录
     * 对于这个包，WEB_ROOT是工作目录下的“webroot”目录。
     * 工作目录是文件系统中调用java命令的位置。
     */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "src/main/webroot";

    //shutdown command
    private static final String SHUTDOWM_COMMAND = "/SHUTDOWN";

    // the shutdown command received
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    /**
     * 在指定端口上等待HTTP请求，对其进行处理，然后发送响应信息回客户端。在接收到关闭命令之前，会一直保持等待状态。
     * backlog:在拒绝接受传入请求之前，传入的连接请求的最大队列长度。
     */
    public void await() {
        //await方法会先创建一个ServerSocket实例，然后进入while循环
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        //Loop waiting for a request
        while (!shutdown){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                //接收到请求之后，从accept方法返回的Socket实例中获取inputStream和outputStream对象
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                //然后，创建ex01.pyrmont.Request对象，并调用其parse方法来解析HTTP请求的原始数据
                Request request = new Request(inputStream);
                request.parse();

                //然后，创建一个Response对象，并分别调用其setRequest()方法和sendStaticResponse方法：
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();

                //最后，await方法关闭套接字，调用Request类的方法来测试HTTP请求的URI是否关闭命令。
                socket.close();
                shutdown = request.getUri().equalsIgnoreCase(SHUTDOWM_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }




        }











    }


}
