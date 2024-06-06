package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 最大线程数和核心线程数都为1，且拥有无界队列
 * @author 李志豪
 * @create 2024/6/6
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new FixedThreadPoolTest.Task());
        }
    }
}
