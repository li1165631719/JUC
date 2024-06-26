package collections.predecessor;

import java.util.*;

/**
 *
 * 描述: 演示Collections.synchronizedList(new ArrayList<E>())
 * @Author 李志豪
 * @Date 2024/6/26 21:04
 */
public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(5);
        Map<Object,Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());

    }
}
