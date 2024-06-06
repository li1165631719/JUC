package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 核心线程数为0，最大线程数为Integer.MAX_VALUE，没有队列帮忙存储，无限创造线程去调用，线程存货时间60L
 * @author 李志豪
 * @create 2024/6/6
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
