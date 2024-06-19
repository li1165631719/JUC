package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Atomic*Array数组类型原子类
 * 演示原子数组的使用方法
 *
 * @Author 李志豪
 * @Date 2024/6/19 21:48
 */
public class AtomicArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray =new AtomicIntegerArray(1000);

        Incrementer incrementer =new Incrementer(atomicIntegerArray);
        Decrementer decrementer =new Decrementer(atomicIntegerArray);

        Thread[] threadsIncrementer =new Thread[100];
        Thread[] threadsDecrementer =new Thread[100];
        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i]=new Thread(decrementer);
            threadsIncrementer[i]=new Thread(incrementer);
            threadsDecrementer[i].start();
            threadsIncrementer[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                threadsDecrementer[i].join();//join等待当前线程结束，准确的sleep，确保线程一定执行完成
                threadsIncrementer[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
//            if(atomicIntegerArray.get(i)!=0){
//                System.out.println("发现了错误"+i);
//            }
            System.out.println(atomicIntegerArray.get(i));
        }
        System.out.println("运行结束");
    }
}
class Decrementer implements Runnable{
    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable{
    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}

