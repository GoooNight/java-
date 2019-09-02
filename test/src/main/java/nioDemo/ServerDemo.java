package nioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description
 * @author:awei
 * @date:2019/8/30
 * @ver:1.0
 **/
public class ServerDemo {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    public ServerDemo(){
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.bind(new InetSocketAddress(8900));
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() throws IOException {
        while (true){
            System.out.println("等待中。。。");
            this.selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()){
                    try {
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel accept = server.accept();
                        accept.configureBlocking(false);
                        accept.register(this.selector,SelectionKey.OP_READ );
                        Config.map.put(accept,true);
                        System.out.println(accept.hashCode());
                        Config.executor.execute(()->{
                            while (true) {
                                Scanner scanner = new Scanner(System.in);
                                String s = scanner.nextLine();
                                if (Config.map.get(accept)){
                                    try {
                                        this.sendMessage(accept,s);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        System.out.println("线程异常终止"+Thread.currentThread().getName());
                                        break;
                                    }
                                }else {
                                    System.out.println("链接已断开"+Thread.currentThread().getName());
                                    break;
                                }

                            }
                        });
                        System.out.println("accept");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }if (key.isReadable()){
                    try {
                        this.receiveMessage("收到了，结束了",key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("断开链接");
                        SocketChannel channel = (SocketChannel) key.channel();
                        Config.map.put(channel,false);
                        channel.close();
                        System.out.println(channel.hashCode());
                    }
                }

            }
        }
    }



    public void sendMessage(SocketChannel socketChannel,String message) throws IOException {
        buffer.put(message.getBytes());
        buffer.limit(message.getBytes().length);
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();

    }
    public void receiveMessage(String message,SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        int count = socketChannel.read(readBuffer);
        System.out.println(count);
        byte[] bytes = new byte[count];
        readBuffer.flip();
        readBuffer.get(bytes);
        System.out.println(new String(bytes));
        readBuffer.clear();
    }

    public static void main(String[] args) throws Exception {
        new ServerDemo().start();
        System.out.println("----------");
        System.exit(-1);
    }
}
