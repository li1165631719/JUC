import java.util.*;

/**
 * 描述：演示HashMap和Map在多线程情况下造成死循环的情况
 * @Author 李志豪
 * @Date 2024/6/26 21:01
 */
public class HashMapEndlessLoop {
    private static HashMap<Integer,String> map=new HashMap<>(2,1.5f);//扩容问题

    public static void main(String[] args) {
        map.put(5,"C");
        map.put(7,"B");
        map.put(3,"A");
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15,"D");
                System.out.println(map);
            }
        },"Thread 1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1,"E");
                System.out.println(map);
            }
        },"Thread 2").start();
    }

}
