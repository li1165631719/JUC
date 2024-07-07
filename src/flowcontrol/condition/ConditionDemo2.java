package flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：演示用Condition实现生产者消费者模式
 * @Author 李志豪
 * @Date 2024/7/7 22:03
 */
public class ConditionDemo2 {
    private int queueSize =10;
    private PriorityQueue<Integer> queue =new PriorityQueue<Integer>(queueSize);

    private Lock lock = new ReentrantLock();
    private Condition notFull =lock.newCondition();
    private Condition noEmpty =lock.newCondition();

}
