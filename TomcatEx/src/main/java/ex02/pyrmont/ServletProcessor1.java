package ex02.pyrmont;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * 请求的是一个servlet，则载入相应的servlet类，调用其service()方法，传入servletRequest
 * 对象和servletResponse对象
 */
public class ServletProcessor1 {

    public void process(Request request, Response response) {

        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/" + 1));
        URLClassLoader classLoader = null;

        try {
            //create a URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            //the forming of repository is taken from the createClassLoader method in org.apache.catalina.startup.ClassLoaderFactory
            //返回此抽象路径名的标准路径名字符串。
            String respository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();

            //the code for forming the URL is taken from the addRepository method in
            //org.apache.catalina.loader.StandardClassLoader class.
            urls[0]= new URL(null,respository,streamHandler);
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
        Servlet servlet  = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest)request,(ServletResponse)response);
        } catch (Exception e) {
            e.printStackTrace();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

    }
}
