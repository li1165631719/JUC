package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李志豪
 * @create 2024/6/17
 */
public class GetHoldCount {
    private static ReentrantLock lock =new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }

}
