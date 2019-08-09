package timerDemo;

import java.util.Timer;
import java.util.concurrent.*;

/**
 * @Description
 * @author:awei
 * @date:2019/8/9
 * @ver:1.0
 **/
public class TimerDemo {

    public static void main(String[] args) {
        /*

         */
        /*ExecutorService executorService1 = Executors.newFixedThreadPool(1);
        ExecutorService executorService = Executors.newCachedThreadPool();*/
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        /**
         * scheduleAtFixedRate()不受计划的时间影响，即线程进入运行时就开始倒计时
         */
        scheduledExecutorService.scheduleAtFixedRate(()-> {

                try {
                    System.out.println("你好");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },2,5,TimeUnit.SECONDS);

        /**
         * scheduleWithFixedDelay()受计划时间影响，等线程运行完，在倒计时
         */
        scheduledExecutorService.scheduleWithFixedDelay(()-> {

            try {
                System.out.println("你好");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },2,5,TimeUnit.SECONDS);

    }
}
