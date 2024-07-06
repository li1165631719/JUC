package collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author 李志豪
 * @Date 2024/7/7 3:28
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        Interviewer r1 = new Interviewer(queue);
        Consumer r2 = new Consumer(queue);
        new Thread(r1).start();
        new Thread(r2).start();


    }
}
class Interviewer implements Runnable {
    BlockingQueue<String> queue;

    public Interviewer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("10个候选人都已经到了");

        for (int i = 1; i <= 10; i++) {
            String condidate ="Condidate"+i;
            try {
                queue.put(condidate);
                System.out.println("已经安排好了候选人"+condidate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class Consumer implements Runnable {
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue queue) {

        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String msg;
        try {
            while (! (msg=queue.take()).equals("stop")){
                System.out.println(msg+"到了");
            }
            System.out.println("所有候选人都结束了");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}