package threadlocal;

/**
 * 演示ThreadLocal用法2：避免传递参数的麻烦
 * this指代当前对象
 * @author 李志豪
 * @create 2024/6/11
 */
public class ThreadLocalNormalUsage06 {
    public static void main(String[] args) {
        new service1().process();
    }
}

class service1 {
    public void process() {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
        new service2().process();
    }
}

class service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println(user.name);
        new service3().process();
    }
}

class service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println(user.name);
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    public String name;

    public User(String name) {
        this.name = name;
    }

}
