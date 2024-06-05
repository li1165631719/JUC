package threadpool;

import java.util.concurrent.Executors;

/**
 *
 * 为什么要使用线程池？
 * 1.反复创建线程开销大
 * 2.过多的线程会占用太多的内存
 *
 * 解决思路：
 * 1.用少量的线程--避免内存占用过大
 * 2.让这部分线程都保持工作，且可以反复执行任务--避免声明周期的损耗
 *
 * 好处：
 * 1.加快响应速度
 * 2.合理利用cpu和内存
 * 3.统一管理资源
 *
 * 适用场景：
 * 服务器接受大量的请求时，使用线程池技术是非常合适的，它可以大大减少线程的创建和销毁次数，提供服务的工作效率
 *
 * 实际上，在开发中，使用超过5个线程就可以用线程池进行管理
 *
 * @author 李志豪
 * @create 2024/6/4
 */
public class Forloop {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread thread =new Thread(new Task());
            thread.start();
        }
        Executors.newFixedThreadPool(1);

    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
