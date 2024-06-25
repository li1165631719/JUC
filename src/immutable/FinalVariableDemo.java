package immutable;

/**
 * 演示final变量
 * @author 李志豪
 * @create 2024/6/24
 */
public class FinalVariableDemo {
    private static final int a ;

    //public FinalVariableDemo(int a) {
    //    this.a = a;
    //}
    static {
        a=7;
    }

    void testFinal(){
        final int b;
        b = 5;
        //int c = b ;
    }
}
