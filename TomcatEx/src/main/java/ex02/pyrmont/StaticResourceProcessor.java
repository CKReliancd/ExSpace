package ex02.pyrmont;

/**
 * 请求的是一个静态资源，则调用StaticResourceProcessor对象的process()方法，
 * 传入servletResponse对象和servletRequest对象
 */
public class StaticResourceProcessor {

    public void process(Request request,Response response){
        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
