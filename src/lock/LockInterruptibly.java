package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李志豪
 * @create 2024/6/14
 */
public class LockInterruptibly implements Runnable {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly1 = new LockInterruptibly();
        Thread thread1 = new Thread(lockInterruptibly1);
        Thread thread2 = new Thread(lockInterruptibly1);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到锁");//锁住的操作
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断了");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + "获得锁期间被中断了");
        }
    }
}
