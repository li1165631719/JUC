package lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 李志豪
 * @create 2024/6/14
 */
public class PessimismOptimismLock {
    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger =new AtomicInteger();
        atomicInteger.getAndIncrement();
    }
    public synchronized void testMethod() {
        a++;
    }
}
