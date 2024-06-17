package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李志豪
 * @create 2024/6/17
 */
public class RecursionDemo {
    private static ReentrantLock lock= new ReentrantLock();

    public static void accessResource(){
        lock.lock();
        try{
            System.out.println("已经对资源进行了处理");
            if(lock.getHoldCount()<5){
                System.out.println(lock.getHoldCount());
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        accessResource();
    }
}
