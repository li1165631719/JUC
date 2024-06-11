package threadlocal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2个线程
 * @Author 李志豪
 * @Date 2024/6/5 23:43
 */
public class ThreadLocalNormalUsage01 {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage01().date(finalI);
                    System.out.println(date);
                }
            }).run();
        }

    }
    private String date(int seconds){
        //参数的单位是毫秒，是从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 *seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
