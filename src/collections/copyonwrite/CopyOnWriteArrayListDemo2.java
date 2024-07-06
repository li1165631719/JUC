package collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author 李志豪
 * @Date 2024/7/7 3:15
 */
public class CopyOnWriteArrayListDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> list =new CopyOnWriteArrayList<>(new Integer[]{1,2,3});

        System.out.println(list);

        Iterator<Integer> itr1 = list.iterator();
        list.remove(2);
        Thread.sleep(1000);
        System.out.println(list);

        Iterator<Integer> itr2 = list.iterator();

        while(itr1.hasNext()){
            System.out.println(itr1.next());
        }

//        itr1.forEachRemaining(System.out::println);
//        itr2.forEachRemaining(System.out::println);
    }
}
