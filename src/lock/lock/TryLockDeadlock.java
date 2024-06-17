package lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：用tryLock来避免死锁
 *
 * @author 李志豪
 * @create 2024/6/14
 */
public class TryLockDeadlock implements Runnable {
    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadlock tryLockDeadlock1=new TryLockDeadlock();
        TryLockDeadlock tryLockDeadlock2=new TryLockDeadlock();
        tryLockDeadlock1.flag = 1;
        tryLockDeadlock2.flag = 0;
        new Thread(tryLockDeadlock1).start();
        new Thread(tryLockDeadlock2).start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + "获取到了锁2");
                                    System.out.println(Thread.currentThread().getName() + "获取到了2把锁");
                                } finally {
                                    lock2.unlock();
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + "获取锁2失败");
                            }
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁1失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (flag == 0) {
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到了锁2");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println(Thread.currentThread().getName() + "获取到了锁1");
                                }finally {
                                    lock1.unlock();
                                }
                            }else {
                                System.out.println(Thread.currentThread().getName() + "未获取到锁1");
                            }
                        } finally {
                            lock2.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "未获取到锁2，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
