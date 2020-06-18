package ex03.connector.http;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.catalina.util.ParameterMap;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.util.*;

public class HttpRequest implements HttpServletRequest {

    private String contentType;
    private int contentLength;
    private InetAddress inetAddress;
    private InputStream inputStream;
    private String method;
    private String protocol;
    private String queryString;
    private String requestURI;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private boolean requestedSessionCookie;
    private String requestedSessionId;
    private boolean requestedSessionURL;

    /**
     * request attributes 请求中的属性
     */
    protected HashMap attributes = new HashMap();

    /**
     * 与request一起发送过来的授权凭证
     */
    protected String authorization = null;

    /**
     * request的上下文
     */
    protected String contextPath = "";

    /**
     * 请求里的请求头，key是名，value是数组
     */
    protected Map headers = Maps.newHashMap();

    /**
     * 请求里经过解析的参数，参数可以通过调用重载方法getParameter()获取,
     * key是参数名，值是字符串数组
     * 注：一旦特定的请求被解析和储存在这里，他们就不能被修改，因此没必要在程序访问里synchronized参数
     */
    protected ParameterMap parameterMap = null;

    /**
     * 标注请求是否已经被解析
     */
    protected boolean parsed = false;
    protected String pathInfo = null;

    /**
     * 需要的话返回reader
     */
    protected BufferedReader reader = null;

    /**
     * 返回ServletInputStream
     */
    protected ServletInputStream stream = null;

    public HttpRequest(InputStream inputStream){this.inputStream = inputStream;}

    public void addHeader(String name,String value){
        name = name.toLowerCase();
        synchronized (headers){
            ArrayList values = (ArrayList)headers.get(name);
            if (values == null){
                values = Lists.newArrayList();
                headers.put(name,values);
            }
            values.add(value);
        }
    }

    protected void parseParameters(){
        if (parsed){
            return;
        }
        ParameterMap results = this.parameterMap;
        if (results == null){
            results = new ParameterMap();
        }
        results.setLocked(false);
        String encoding = getCharacterEncoding();
        if (encoding == null){
            encoding = "ISO-8859-1";
        }

        //解析查询字符串里指定的参数
        String queryString = getQueryString();
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }









    @Override
    public String getAuthType() {
        return null;
    }

    @Override
    public Cookie[] getCookies() {
        return new Cookie[0];
    }

    @Override
    public long getDateHeader(String s) {
        return 0;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getHeaders(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return null;
    }

    @Override
    public int getIntHeader(String s) {
        return 0;
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public String getPathInfo() {
        return null;
    }

    @Override
    public String getPathTranslated() {
        return null;
    }

    @Override
    public String getContextPath() {
        return null;
    }

    @Override
    public String getQueryString() {
        return null;
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return null;
    }

    @Override
    public String getRequestURI() {
        return null;
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public HttpSession getSession(boolean b) {
        return null;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public String changeSessionId() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    @Override
    public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return false;
    }

    @Override
    public void login(String s, String s1) throws ServletException {

    }

    @Override
    public void logout() throws ServletException {

    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    @Override
    public Part getPart(String s) throws IOException, ServletException {
        return null;
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public long getContentLengthLong() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
