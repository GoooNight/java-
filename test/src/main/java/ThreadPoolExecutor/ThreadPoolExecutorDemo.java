package ThreadPoolExecutor;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description 线程池的使用
 * @author:awei
 * @date:2019/8/9
 * @ver:1.0
 **/
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /**
         * 建议自己创建线程池，线程Callable接口存在返回值，Runnable接口不存在返回值
         * ExecutorCompletionService对线程池进行管理，其submit方法对线程接口Callable实现
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,7,10, TimeUnit.SECONDS,new LinkedBlockingQueue(10));
        ExecutorCompletionService<String> service = new ExecutorCompletionService<>(executor);
        List<Future<String>> list = new ArrayList<>(10);
        for (int i = 0;i < 5;i++){
            Future<String> submit = service.submit(() -> {
                Thread.sleep(5000);
                System.out.println("www");
                return "你好啊";});

            try {
                list.add(submit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        service.submit(()->{
            for (Future<String> future: list) {
                if (future.isDone()){
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        });
        executor.shutdown();

    }
}
