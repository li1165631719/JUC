package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示一个Future的使用方法
 * @Author 李志豪
 * @Date 2024/7/13 6:27
 */
public class OneFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    static class CallableTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
