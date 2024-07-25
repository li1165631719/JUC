package imooccache;

import imooccache.computable.Computable;
import imooccache.computable.ExpensiveFunction;
import imooccache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 描述：利用Future，避免重复计算
 */
public class ImoocCache9<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public ImoocCache9(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        while (true) {
            System.out.println("进入缓存机制");
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable callable = new Callable() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(callable);
                f = cache.putIfAbsent(arg, ft);//利用原子操作保证线程安全
                if (f == null) {
                    f = ft;
                    System.out.println("利用futureTask调用了计算函数");
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                System.out.println("被取消了");
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                System.out.println("计算错误");
                cache.remove(arg);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ImoocCache9<String, Integer> expensiveComputer = new ImoocCache9<>(
                new MayFail());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer result = null;
                try {
                    result = expensiveComputer.compute("666");
                    System.out.println("第一次计算结果" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer result = null;
                try {
                    result = expensiveComputer.compute("666");
                    System.out.println("第二次计算结果" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer result = null;
                try {
                    result = expensiveComputer.compute("667");
                    System.out.println("第三次计算结果" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
