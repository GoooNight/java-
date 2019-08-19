package timerDemo;

import java.util.concurrent.*;

/**
 * @Description 定时线程加异步，在定时线程中加入的方法改为其他线程
 * @author:awei
 * @date:2019/8/19
 * @ver:1.0
 **/
public class ScheduleDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8));
        ExecutorCompletionService service1 = new ExecutorCompletionService(executor);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            service1.submit(()->{
                System.out.println("你好");
                Thread.sleep(5000);
                return "success";
            });
        },1,2, TimeUnit.SECONDS);


    }
}
