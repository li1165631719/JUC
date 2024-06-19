package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述： 演示AtomicInteger的基本用法，对比非原子类的线程
 * 安全问题，使用原子类之后，不需要加锁，也可以保证线程安全
 *
 * 常用方法
 * public final int get()//获取当前值
 *
 * public final int getAndSet(int newValue)//获取当前的值，并设置新的值
 *
 * public final int getAndIncrement()//获取当前的值，并自增
 * 用a++,会先读取，然后加，再写回去，所以这种情况就会就会被打断，而上述操作就是把这些操作变成原子操作不可以被打断
 *
 * public final int getAndDecrenment()//获取当前的值，并自减
 *
 * public final int getAndA(int delta)//获取当前的值，并加上预期值
 *
 * boolean compareAndSet(int expect,int update)//如果当前的数值等于预期值，则以原子的方式将该值设置为输入值（update）
 *
 *
 * @Author 李志豪
 * @Date 2024/6/19 21:30
 */
public class AtomicIntegerDemo1 implements Runnable{
   private static final AtomicInteger atomicInteger = new AtomicInteger();

   public void incrementAtomic(){
       atomicInteger.getAndAdd(-90);
   }

   private static volatile int basicCount = 0;
   public synchronized void incrementBasic(){//随着功能的扩充，sychronized就不单单作用在这一个变量上了，而原子类仅仅一个操作就能保证线程安全
       basicCount++;
   }


    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 atomicIntegerDemo1 =new AtomicIntegerDemo1();
        Thread t1 = new Thread(atomicIntegerDemo1);
        Thread t2 = new Thread(atomicIntegerDemo1);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("原子类的结果"+atomicInteger.get());
        System.out.println("普通类的结果"+basicCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }
}
