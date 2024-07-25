package imooccache;

import imooccache.computable.Computable;
import imooccache.computable.ExpensiveFunction;
import imooccache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 描述： 出于安全性考虑，缓存需要设置有效期，到期自动失效，否则如果缓存一直不失效，那么带来缓存不一致等问题
 */
public class ImoocCache11 {
    static ImoocCache10<String, Integer> expensiveComputer = new ImoocCache10<>(
            new ExpensiveFunction());
    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {

        }
    }
}
