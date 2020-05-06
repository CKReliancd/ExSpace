package ex02.pyrmont;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 会从servletResponse对象中获取，并将字符串发送到客户浏览器
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = servletResponse.getWriter();
        out.println("Hello, Rose are red.");
        out.print("Violets are blue.");
    }

    public String getServletInfo() {
        return null;
    }

    /**
     * 在将servlet实例从服务中移除前，servlet容器会调用servlet实例的destroy()方法。
     * 一般当servlet容器关闭或servlet容器要释放内存的时候，才会将servlet实例移除，
     * 而且只有当servlet实例的service()方法中所有线程退出或执行超时后，才会调用destroy()。
     * 当servlet容器调用了某个servlet实例的destroy()方法后，他就不会再调用该servlet实例的
     * service()方法了。调用destroy()方法让servlet对象去清理自身持有的资源，如内存、文件句柄
     * 和线程等，确保所有的持久化状态与内存中该servlet对象的当前状态同步。
     */
    public void destroy() {
        System.out.println("destroy");
    }
}
