package imooccache.computable;

import java.io.IOException;
import java.util.Random;

public class MayFail implements Computable<String,Integer>{

    @Override
    public Integer compute(String arg) throws Exception {
        double random =  Math.random();
        if(random>0.5){
            throw new IOException("读取文件出错");
        }
        Thread.sleep(5000);
        return new Integer(arg);
    }
}
