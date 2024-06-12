package threadlocal;

/**
 * @author 李志豪
 * @create 2024/6/12
 */
public class ThreadLocalNPE {
    private static ThreadLocal<Long> longThreadLocal =new ThreadLocal<>();

    public long get(){
        return longThreadLocal.get();
    }
    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE =new ThreadLocalNPE();
        //System.out.println("ThreadLocalNPE.get() = " + threadLocalNPE.get());
        threadLocalNPE.set();
        System.out.println("ThreadLocalNPE.get() = " + threadLocalNPE.get());
        Thread thread1 =new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println("ThreadLocalNPE.get() = " + threadLocalNPE.get());
            }
        });
        thread1.start();


    }
}
