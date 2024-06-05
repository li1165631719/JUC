package threadpool;

/**
 * @author 李志豪
 * @create 2024/6/4
 */
public class EveryTaskOneThread {
    public static void main(String[] args) {
        Thread thread =new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
