package cas;

/**
 * 模拟CAS操作，等价代码
 *
 * @Author 李志豪
 * @Date 2024/6/23 10:22
 */
public class TwoThreadsCompetition implements Runnable {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;
        Thread t1 = new Thread(r,"Thread1");
        Thread t2 = new Thread(r,"Thread2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(r.value);
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }
}
