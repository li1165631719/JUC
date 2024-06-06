package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 核心线程数为n，最大线程数为Integer.MAX_VALUE,线程保存时间为0，拥有延迟队列
 * @author 李志豪
 * @create 2024/6/6
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        threadPool.schedule(new FixedThreadPoolTest.Task(),5, TimeUnit.SECONDS);//延迟5s调用

    }
}
