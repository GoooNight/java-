package threadLocalDemo;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description ThreadLocal练习
 * @author:awei
 * @date:2019/8/29
 * @ver:1.0
 **/

public class ThreadLocalDemo {

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal();
    public volatile static List<Integer> list = new ArrayList<>(16);
    public static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(2);
    public static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(2);
    public static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,20, TimeUnit.SECONDS,new ArrayBlockingQueue<>(8));
        ExecutorCompletionService service = new ExecutorCompletionService(threadPoolExecutor);
        service.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                while (true){
                    if (THREAD_LOCAL.get()==null){
                        System.out.println("----------");
                        THREAD_LOCAL.set("线程1");
                        System.out.println("----我是刚加的1---");
                    }
                    System.out.println("-----线程1开始-----");
                    list.add(1);
                    String s = THREAD_LOCAL.get();
                    System.out.println(s);
                    System.out.println("-----线程1结束-----");
                    COUNT_DOWN_LATCH.countDown();
                    Thread.sleep(2000);
                }

            }
        });
        service.submit(() -> {
            while (true) {
                if (THREAD_LOCAL.get() == null) {
                    System.out.println("----------");
                    THREAD_LOCAL.set("线程2");
                    System.out.println("-----我是刚加的2------");
                }
                COUNT_DOWN_LATCH.await();
                lock.lock();
                System.out.println("-----线程2开始-----");
                list.forEach(System.out::println);
                String s = THREAD_LOCAL.get();
                System.out.println(s);
                System.out.println("-----线程2结束-----");
                Thread.sleep(2000);
                lock.unlock();
                CYCLIC_BARRIER.await();
            }
        });
        service.submit(()->{
            while (true) {
                if (THREAD_LOCAL.get() == null) {
                    System.out.println("----------");
                    THREAD_LOCAL.set("线程3");
                    System.out.println("-----我是刚加的3------");
                }
                lock.lock();
                System.out.println("-----线程3开始-----");
                list.forEach(System.out::println);
                String s = THREAD_LOCAL.get();
                System.out.println(s);
                System.out.println("-----线程3结束-----");
                Thread.sleep(5000);
                lock.unlock();
                CYCLIC_BARRIER.await();
            }
        });
    }
}
