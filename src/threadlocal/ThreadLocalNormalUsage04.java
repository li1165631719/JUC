package threadlocal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程安全的线程池,但是牺牲了一部分时间性能
 * @Author 李志豪
 * @Date 2024/6/5 23:43
 */
public class ThreadLocalNormalUsage04 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage04().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }
    private String date(int seconds){
        //参数的单位是毫秒，是从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 *seconds);
        String s =null;
        synchronized (ThreadLocalNormalUsage04.class){
            s=simpleDateFormat.format(date);
        }
        return s;
    }
}
