package chapter04;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Piped {

    public static void main(String[] args) throws IOException {

        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        //将输入输出流进行连接，否则会在使用的时候抛出IOException
        reader.connect(writer);
        Thread printThread = new Thread(new Print(reader), "PrintThread");
        printThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                writer.write(receive);
            }
        } finally {
            writer.close();
        }
    }

    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            while (true) {
                try {
                    receive = in.read();
                    if (receive == -1) break;
                } catch (IOException e) {
                }
                System.out.print((char) receive);
            }
        }
    }
}
