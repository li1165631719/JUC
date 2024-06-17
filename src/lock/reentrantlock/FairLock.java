package lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 李志豪
 * @Date 2024/6/17 20:15
 */
public class FairLock {
    public static void main(String[] args) {
        PrintQueue printQueue =new PrintQueue();
        Thread thread[] =new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
class Job implements Runnable{
    PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始打印");
        printQueue.printJob(new Object());
        System.out.println(Thread.currentThread().getName()+"打印完毕");
    }
}
class PrintQueue{
    private Lock queueLock =new ReentrantLock(false);

    public void printJob(Object document){
        queueLock.lock();
        try{
            int duration = new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+"正在打印，需要"+duration+"秒");
            Thread.sleep(duration*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
        //在非公平锁，第一个线程执行完还处于唤醒状态，所以继续执行。而在公平锁中，则让线程0等在线程9后面。
        queueLock.lock();
        try{
            int duration = new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+"正在打印，需要"+duration+"秒");
            Thread.sleep(duration*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
