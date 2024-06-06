package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 最大线程数和核心线程数相同，且拥有无界队列
 * @author 李志豪
 * @create 2024/6/6
 */
public class FixedThreadPool00M {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //executorService.execute(new SubThread());
        }
    }
}
