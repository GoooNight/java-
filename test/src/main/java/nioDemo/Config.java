package nioDemo;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @author:awei
 * @date:2019/9/2
 * @ver:1.0
 **/
public class Config {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
            10,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(16),
            new ThreadPoolExecutor.AbortPolicy());
    public static boolean status = true;
    public static Map<SocketChannel,Boolean> map = new HashMap<>(16);

}
