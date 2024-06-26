package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：利用ThreadLocal，给每个线程分配自己的dateFormat对象，保证了线程安全，高效利用内存
 *
 * @Author 李志豪
 * @Date 2024/6/5 23:43
 */
public class ThreadLocalNormalUsage05 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage05().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    private String date(int seconds) {
        //参数的单位是毫秒，是从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal1 = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
