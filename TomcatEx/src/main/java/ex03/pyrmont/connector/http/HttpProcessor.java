package ex03.pyrmont.connector.http;

import jdk.management.resource.internal.inst.SocketInputStreamRMHooks;
import org.apache.catalina.util.StringManager;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Administrator
 * @Description Http处理器，主要用与调用HttpServer
 * @Date 2020/6/19 11:25
 */
public class HttpProcessor {


    /**
     * 与此处理器相关联的Http连接器(HttpConnector)
     */
    private HttpConnector connector = null;
    private HttpRequest request;
    private HttpRequestLine requestLine = new HttpRequestLine();
    private HttpResponse response;

    protected String method = null;
    protected String queryString = null;

    /**
     * The string manager for this package.
     */
    protected StringManager sm = StringManager.getManager("ex03.pyrmont.connector.http");

    public void process(Socket socket) {
        SocketInputStream socketInputStream = null;
        OutputStream outputStream = null;
        try {
            socketInputStream = new SocketInputStream(socket.getInputStream(), 2048);
            outputStream = socket.getOutputStream();

            //create HttpRequest object and parse
            request = new HttpRequest(socketInputStream);

            //create HttpResponse object
            response = new HttpResponse(outputStream);
            response.setRequest(request);

            response.setHeader("Server", "Pyrmont Servlet Container");

            parseRequest(socketInputStream, outputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 解析inputStream流中的Request
     *
     * @param socketInputStream
     * @param outputStream
     */
    private void parseRequest(SocketInputStream socketInputStream, OutputStream outputStream)
    throws IOException, ServletException {































    }


}
