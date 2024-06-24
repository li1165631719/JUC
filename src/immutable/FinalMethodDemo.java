package immutable;

/**
 * @Author 李志豪
 * @Date 2024/6/25 4:14
 */
public class FinalMethodDemo {

    public void drink(){

    }

    public final void eat(){

    }

    public static void sleep(){

    }
}

class SubClass extends FinalMethodDemo{
    @Override
    public void drink() {
        super.drink();
        eat();//是允许调用的
    }

//    public void eat(){
//
//    }
    public static void sleep(){

    }
}
