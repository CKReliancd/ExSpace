package chapter04;

/**
 * @author Administrator
 * @Description </p>
 * <p>
 * //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 * public void await() throws InterruptedException { };
 * //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
 * //将count值减1
 * public void countDown() { };
 * @Date 2021/5/8 14:19
 */
public class CountDownLatchTest {

}
