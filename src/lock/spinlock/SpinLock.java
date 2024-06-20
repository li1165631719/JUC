package lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * 自旋锁
 *
 * @author 李志豪
 * @create 2024/6/20
 */
public class SpinLock {
    private AtomicReference<Thread> sign =new AtomicReference<>();

    public void lock(){
        Thread current = Thread.currentThread();
        while(!sign.compareAndSet(null,current)){
            System.out.println(current.getName()+"自旋获取失败，再次尝试");
        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current,null);
    }

    public static void main(String[] args) {
        SpinLock spinLock =new SpinLock();
        Runnable runnable =new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始尝试获取自旋锁");
                spinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"获取到了自旋锁");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName()+"释放了自旋锁");
                    spinLock.unlock();
                }
            }
        };
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        thread1.start();
        thread2.start();
    }

}
