import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Description
 * @Date 2021/4/12 14:29
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
