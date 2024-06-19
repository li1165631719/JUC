package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author 李志豪
 * @Date 2024/6/17 21:52
 */
public class CinemaReadWriteQueue {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock =reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock =reentrantReadWriteLock.writeLock();

    private static void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"得到了读锁，正在读取");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }
    private static void write(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"得到了写锁，正在写入");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
            new Thread(()->write(),"Thread1").start();
            new Thread(()->read(),"Thread2").start();
            new Thread(()->read(),"Thread3").start();
            new Thread(()->write(),"Thread4").start();
            new Thread(()->read(),"Thread5").start();//此读锁也不会抢占前面得写锁，除非在某一读锁唤醒之前那一点点空隙时间实现读锁抢占，正好有线程以是正在执行得状态获取读锁
    }
}
