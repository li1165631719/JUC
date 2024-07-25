package imooccache;

import imooccache.computable.Computable;
import imooccache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 描述： 出于安全性考虑，缓存需要设置有效期，到期自动失效，否则如果缓存一直不失效，那么带来缓存不一致等问题
 */
public class ImoocCache10<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public ImoocCache10(Computable<A, V> c) {
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

    public V computeRandomExpire(A arg) throws Exception {
        long randomExpire = (long)Math.random()*1000;
        return compute(arg,randomExpire);
    }
    public final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    public V compute(A arg, long expire) throws Exception {
        if (expire > 0) {
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    expire(arg);
                }
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    public synchronized void expire(A key) {
        Future<V> f = cache.get(key);
        if (f != null) {
            if (!f.isDone()) {
                System.out.println("Future任务被取消");
                f.cancel(true);
            }
            System.out.println("过期时间到，缓存被清除");
            cache.remove(key);
        }
    }

    public static void main(String[] args) throws Exception {
        ImoocCache10<String, Integer> expensiveComputer = new ImoocCache10<>(
                new MayFail());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer result = null;
                try {
                    result = expensiveComputer.computeRandomExpire("666");
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

        Thread.sleep(6000L);
        Integer result = expensiveComputer.compute("666");
        System.out.println("主线程的计算结果：" + result);
    }
}
