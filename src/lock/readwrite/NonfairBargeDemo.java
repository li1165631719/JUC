package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示非公平和公平的ReentrantReadWriteLock的策略
 *
 * @author 李志豪
 * @create 2024/6/18
 */
public class NonfairBargeDemo {
    private static ReentrantReadWriteLock reentrantReadWriteLock =new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock =reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    private static void read(){
        System.out.println(Thread.currentThread().getName()+"尝试获取读锁");
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"得到读锁，正在读取");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write(){
        System.out.println(Thread.currentThread().getName()+"尝试获取写锁");
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"得到写锁，正在读取");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(()->write(),"Thread0").start();
        new Thread(()->read(),"Thread1").start();
        new Thread(()->read(),"Thread2").start();
        new Thread(()->write(),"Thread3").start();
        new Thread(()->write(),"Thread4").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread[] threads =new Thread[1000];
                for (int i = 0; i < 1000; i++) {
                    threads[i]=new Thread(()->read());
                }
                for (int i = 0; i < 1000; i++) {
                    threads[i].start();
                }
            }
        }).start();
    }

}
