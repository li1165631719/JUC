package immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述： 一个属性是对象，但是整体不可变，其他对象无法修改set里面的数据
 * @Author 李志豪
 * @Date 2024/6/25 4:38
 */
public class ImmutableDemo {

    //一旦被初始化没有人能访问到students，状态不可变。符合不可变性。
    private final Set<String> students = new HashSet<>();

    public ImmutableDemo() {
        students.add("李小美");
        students.add("王壮");
        students.add("徐福记");
    }

    public boolean isStudent(String name){
        return students.contains(name);
    }
}
