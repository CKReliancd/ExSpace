package chapter05;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @Description
 * @Date 2021/5/12 16:42
 */
public class LockUseCase {
    public void lock() {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
