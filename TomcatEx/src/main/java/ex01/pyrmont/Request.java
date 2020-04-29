package ex01.pyrmont;

import java.io.IOException;
import java.io.InputStream;

public class Request {

    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUri() {
        return uri;
    }

    /**
     * 解析HTTP请求中的原始数据。parse()方法会调用私有方法parseUri()来解析HTTP请求的URI
     * 从传入到Request对象中的套接字的InputStream对象中读取整个字节流，将字节数组储存在缓冲区中，
     * 然后使用缓冲区字节数组中的数组填充StringBuffer对象request，并将StringBuffer的String表示传递给parseUri()
     */
    public void parse() {
        //从socket中读取
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    /**
     * 从请求行中获取URI。在请求中搜索第一个和第二个空格，从中找出URI
     *
     * @param requestString
     * @return
     */
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            return requestString.substring(index1 + 1, index2);
        }
        return null;
    }


}
