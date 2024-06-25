package immutable;

/**
 * @author 李志豪
 * @create 2024/6/25
 */
public class FinalStringDemo1 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;//而b是已知的不会变的，用final修饰的常量。常量+常量就还是常量，去常量池中找
        String e = d + 2;//在堆中计算，因为d是未知的变量
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
