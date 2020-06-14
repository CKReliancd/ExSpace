package ex02.pyrmont;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * 请求的是一个servlet，则载入相应的servlet类，调用其service()方法，传入servletRequest
 * 对象和servletResponse对象
 */
public class ServletProcessor2 {

    public void process(Request request, Response response) {

        //URI格式：servlet/servletName     servletName是请求资源的类名
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        //创建URLClassLoader载入Servlet类，指定Contants.WEB_ROOT指定的工作目录加载类
        URLClassLoader classLoader = null;
        try {
            URL[] urls = new URL[1];
            File classPath = new File(Constants.WEB_ROOT);

            //the forming of repository is taken from the createClassLoader method in org.apache.catalina.startup.ClassLoaderFactory
            //返回此抽象路径名的标准路径名字符串。
            URL file = new URL("file", null, classPath.getCanonicalPath() + File.separator);
            String respository = file.toString();

            //the code for forming the URL is taken from the addRepository method in
            //org.apache.catalina.loader.StandardClassLoader class.
            //有很多方法可以创建URL对象。本方法使用public URL(URL context,java.lang.String spec,URLStreamHandler hander)
            //但是还有一个方法public URL(java.lang.String protocol,java.lang.String host,java.lang.String file)
            //所以指明第三个参数URLStreamHandler用以区分

            URLStreamHandler streamHandler = null;
            urls[0] = new URL(null, respository, streamHandler);

            //urls是一个java.net.URL对象数组，每一个对象都指明了类加载起去哪里加载类。
            //若一个URL以"/"结尾，则表明它指向的是一个目录。否则URL默认指向一个JAR文件，
            //载入器会根据需要下载并打开这个JAR文件。
            classLoader = new URLClassLoader(urls);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = classLoader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFacade, responseFacade);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
