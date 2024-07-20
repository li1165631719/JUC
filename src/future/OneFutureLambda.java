package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示一个Future的使用方法,lambda形式
 * @Author 李志豪
 * @Date 2024/7/13 6:35
 */
public class OneFutureLambda {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Callable callable =()->{
            Thread.sleep(3000);
            return new Random().nextInt();
        };
        Future<Integer> future = service.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
