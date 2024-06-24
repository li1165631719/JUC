package immutable;

/**
 * 描述： 演示栈封闭的两种情况，基本变量和对象
 * 先演示线程争抢带来错误结果，然后把变量放在方法内，情况就变了
 * @Author 李志豪
 * @Date 2024/6/25 4:50
 */
public class StackConfinement implements Runnable{
    int index = 0;

    public void inThread(){
        int neverGoOut = 0;
        synchronized (this) {//完全没有必要，会被编译器优化掉，方法内部的局部变量是线程安全的
            for (int i = 0; i < 10000; i++) {
                neverGoOut++;
            }
        }
        System.out.println("栈内保护的数字是线程安全的："+neverGoOut);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index ++;
        }
        inThread();
    }

    public static void main(String[] args) throws InterruptedException {
        StackConfinement stackConfinement =new StackConfinement();
        Thread thread1 = new Thread(stackConfinement);
        Thread thread2 = new Thread(stackConfinement);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(stackConfinement.index);
    }
}
