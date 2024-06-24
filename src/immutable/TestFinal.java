package immutable;

/**
 * 描述： 测试final能否被修改
 *
 * @author 李志豪
 * @create 2024/6/24
 */
public class TestFinal {
    String test;
    public static void main(String[] args) {

        final Person person=new Person();
        person.testFinal.test="13g";
        System.out.println(person.testFinal.test);
        person.testFinal.test="15g";
        System.out.println(person.testFinal.test);
    }
}
