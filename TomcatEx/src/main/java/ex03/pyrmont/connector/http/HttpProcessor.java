package ex03.pyrmont.connector.http;

import ex03.pyrmont.ServletProcessor;
import ex03.pyrmont.StaticResourceProcessor;
import org.apache.catalina.util.RequestUtil;
import org.apache.catalina.util.StringManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Administrator
 * @Description Http处理器，主要用与调用HttpServer
 * @Date 2020/6/19 11:25
 */
public class HttpProcessor {

    public HttpProcessor(HttpConnector httpConnector) {
        this.connector = httpConnector;
    }

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

    /**
     * process()接受来自HTTP请求的socket。
     * 对于每一个传入的HTTP请求，完成4个操作
     * 1、创建一个HttpRequest对象；
     * 2、创建一个HttpResponse对象；
     * 3、解析HTTP请求的第一行内容和请求头信息，填充HttpRequest对象；
     * 4、将HttpRequest对象和HttpResponse对象传递给servletProcessor或
     * staticResourceProcessor的process()方法。servletProcessor类对调用请求的
     * servlet实例的service()方法，StaticResourceProcessor类会将请求的静态资源发送给客户端
     *
     * @param socket
     */
    public void process(Socket socket) {
        SocketInputStream socketInputStream = null;
        OutputStream outputStream = null;
        try {
            /**
             * 1、读取socket的输入流。
             * 之所以使用SocketInputStream类，
             * 就是为了调用其readRequestLine()方法和readHeader()方法
             */
            socketInputStream = new SocketInputStream(socket.getInputStream(), 2048);
            outputStream = socket.getOutputStream();

            //create HttpRequest object and parse
            request = new HttpRequest(socketInputStream);

            //create HttpResponse object
            response = new HttpResponse(outputStream);
            response.setRequest(request);

            response.setHeader("Server", "Pyrmont Servlet Container");

            /**
             * 2、解析请求行，
             * 例如：GET /myApp/Modernservlet?userName=tarzan$passwork=pwd HTTP/1,1
             * 这里包含了两个名/值对，userName/tarzen和passwork/pad。
             * 在servlet中，参数名jssesionid用于携带一个会话标识。会话标识符通常都是作为
             * cookie嵌入的，但是当浏览器禁用了Cookie时，也可以将会话标识符嵌入到查询字符串中。
             */
            //
            parseRequest(socketInputStream, outputStream);
            parseHeaders(socketInputStream);

            //验证这是一个对servlet的请求还是静态资源的请求
            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor servletProcessor = new ServletProcessor();
                servletProcessor.process(request, response);
            } else {
                StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                staticResourceProcessor.process(request, response);
            }
            // Close the socket
            socket.close();
            // no shutdown for this application

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法是org.apache.catalina.connector.http.HttpProcessor.方法的简化版
     * 只能解析一些简单的请求头信息，例如"cookie","content-length",and "content-type",
     *
     * @param socketInputStream
     * @throws IOException
     * @throws ServletException
     */
    private void parseHeaders(SocketInputStream socketInputStream) throws IOException, ServletException {
        while (true) {
            HttpHeader header = new HttpHeader();
            socketInputStream.readHeader(header);
            if (header.nameEnd == 0) {
                if (header.valueEnd == 0) {
                    return;
                } else {
                    throw new ServletException(sm.getString("httpProcessor.parseHeaders.colon"));
                }
            }
            String name = new String(header.name, 0, header.nameEnd);
            String value = new String(header.value, 0, header.valueEnd);
            request.addHeader(name, value);
            if (name.equals("cookie")) {
                Cookie[] cookies = RequestUtil.parseCookieHeader(value);
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equalsIgnoreCase("jsessionid")) {
                        //重写URL请求中的任何内容
                        if (!request.isRequestedSessionIdFromCookie()) {
                            //只接受cookie中的第一个session id
                            request.setRequestedSessionId(cookies[i].getValue());
                            request.setRequestedSessionCookie(true);
                            request.setRequestedSessionURL(false);
                        }
                    }
                    request.addCookie(cookies[i]);
                }
            } else if (name.equalsIgnoreCase("content-lengh")) {
                int n = -1;
                try {
                    n = Integer.parseInt(value);
                } catch (Exception e) {
                    throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
                }
                request.setContentLength(n);
            } else if (name.equalsIgnoreCase("content-type")) {
                request.setContentType(value);
            }
        }
    }

    /**
     * 解析inputStream流中的Request
     * 当调用此方法，request变量会指向一个HttpRequest实例，
     * parseRequest()方法时解析请求行，从而获取一些值，并将其赋给HttpRequest对象
     *
     * @param socketInputStream
     * @param outputStream
     */
    private void parseRequest(SocketInputStream socketInputStream, OutputStream outputStream)
            throws IOException, ServletException {

        //Parse the incoming request line

        //使用SocketInputStream对象中的信息填充HttpRequestLine实例。
        socketInputStream.readRequestLine(requestLine);

        //从requestLine中获取请求方法、URI和请求协议的版本信息
        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null;
        String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);

        //验证传入的requestline
        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }

        /*
        URI后面可能会有一个查询字符串。若有，则查询字符串与URI使用一个问好分隔的。
        因此，parseRequest方法会首先调用HttpRequest类的setQueryString()方法来获取查询字符串，
        并填充HttpRequest对象：
         */
        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1,
                    requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }
        //用HTTP协议检查URI的绝对路径
        //GET /myApp/Modernservlet?userName=tarzan$passwork=pwd HTTP/1,1
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            //解析出协议和主机名
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }

        /**
         * 有的浏览器关闭了cookie，查询字符串里可能会带上jsessionid。
         * 因此要检查是否存在jsessionid，
         * 存在的时候用HttpRequest类的setRequestedSessionId()方法填充实例:
         */
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            //存在参数jessionid，则表明会话标识符在查询字符串中，而不在cookie中。
            //因此需要调用该请求的setRequestSessionURL()方法并传入true值。
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        } else {
            //不存在参数jeesionid,传入false，并setRequestedSessionURL为null。
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }
        /**
         * 暂时使用字符串的操作使URI正常化，例如"\"会被替换成"/"。
         * 若URI本身时正常的，或不正常的地方可以修正，则normalize()方法会返回相同的URI
         * 或者被修正过的URI。若URI无法修正，则会认为他是无效的，返回null，而且parseRequest()
         * 会在末尾抛出异常。
         */
        String normalizedUri = normalize(uri);
        request.setMethod(method);
        request.setProtocol(protocol);
        if (normalizedUri != null) {
            request.setRequestURI(normalizedUri);
        } else {
            request.setRequestURI(uri);
        }

        if (normalizedUri == null) {
            throw new ServletException("Invalid URI:" + uri + "'");
        }

    }

    /**
     * 暂时使用字符串的操作使URI正常化，例如"\"会被替换成"/"。
     * 若URI本身时正常的，或不正常的地方可以修正，则normalize()方法会返回相同的URI
     * 或者被修正过的URI。若URI无法修正，则会认为他是无效的，返回null，而且parseRequest()
     * 会在末尾抛出异常。
     *
     * @param path Path to be normalized
     */
    protected String normalize(String path) {
        if (path == null)
            return null;
        // Create a place for the normalized path
        String normalized = path;

        // Normalize "/%7E" and "/%7e" at the beginning to "/~"
        if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e"))
            normalized = "/~" + normalized.substring(4);

        // Prevent encoding '%', '/', '.' and '\', which are special reserved
        // characters
        if ((normalized.indexOf("%25") >= 0)
                || (normalized.indexOf("%2F") >= 0)
                || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0)
                || (normalized.indexOf("%2f") >= 0)
                || (normalized.indexOf("%2e") >= 0)
                || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }

        if (normalized.equals("/."))
            return "/";

        // Normalize the slashes and add leading slash if necessary
        if (normalized.indexOf('\\') >= 0)
            normalized = normalized.replace('\\', '/');
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid
        // (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0)
            return (null);

        // Return the normalized path that we have completed
        return (normalized);

    }


}
