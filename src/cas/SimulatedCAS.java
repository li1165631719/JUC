package cas;

/**
 * 模拟CAS操作，等价代码
 * @Author 李志豪
 * @Date 2024/6/23 10:22
 */
public class SimulatedCAS {
    private volatile int value;
    public synchronized int compareAndSwap(int expectedValue ,int newValue){
        int oldValue =value;
        if(oldValue == expectedValue){
            value=newValue;
        }
        return oldValue;
    }


}
