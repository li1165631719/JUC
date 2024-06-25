package immutable;

/**
 * @author 李志豪
 * @create 2024/6/25
 */
public class FinalStringDemo2 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDashixiong();
        String c = b + 2;
        System.out.println(a == c);//为什么是false是因为 final String b = getDashixiong(); 是在运行时确定的，而常量是在编译时候确定的


        final String d = "wukong";
        String e = d + 2;
        System.out.println(a == e);
    }

    private static String getDashixiong() {
        return "wukong";
    }
}
