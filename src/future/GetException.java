package future;

import java.util.concurrent.*;

/**
 * 演示get方法过程中抛出异常,for循环为了演示抛出Exception的时机；并不是说一产生就抛出，直到我们get执行时，才会抛出
 * @Author 李志豪
 * @Date 2024/7/13 6:59
 */
public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            System.out.println(future.isDone());//只告诉任务是否完成了，而不在乎而任务的执行的好坏
            future.get();//异常其实已经发生了，但是在执行future.get方法才能感知到
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");
        }
    }
    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
