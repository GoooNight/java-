package nioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @author:awei
 * @date:2019/8/30
 * @ver:1.0
 **/
public class ClientDemo {
    private Selector selector;
    private SocketChannel socketChannel;
    private ByteBuffer readBuffer =ByteBuffer.allocate(1024);
    private ByteBuffer buffer =ByteBuffer.allocate(1024);

    public ClientDemo(){
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            this.socketChannel.configureBlocking(false);
            this.socketChannel.register(selector, SelectionKey.OP_CONNECT);
            this.socketChannel.connect(new InetSocketAddress("localhost",8900));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        while (Config.status){
            System.out.println("等待中。。。");
            this.selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();
                if(key.isConnectable()){
                    SocketChannel channel=(SocketChannel)key.channel();
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("send message to server.").getBytes()));
                    channel.register(selector, SelectionKey.OP_READ);
                    Config.executor.execute(()->{
                        while (true) {
                            Scanner scanner = new Scanner(System.in);
                            String s = scanner.nextLine();
                            if (!Config.status){
                                System.out.println("服务器关闭");
                                scanner.close();
                                break;
                            }
                            try {
                                this.sendMessage(s);
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("服务端异常已终止"+Thread.currentThread());
                                scanner.close();
                                break;
                            }
                        }
                        System.out.println("---0---00----");
                    });
                    System.out.println("accept");
                }if (key.isReadable()){
                    try {
                        this.receiveMessage("收到了，我也结束了",key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("断开链接");
                        Config.status = false;
                        socketChannel.close();
                        break;
                    }
                }
            }
        }
        selector.close();
    }


    public void sendMessage(String message) throws IOException {
        buffer.put(message.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
    }
    public void receiveMessage(String message,SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        int count = socketChannel.read(readBuffer);
        byte[] bytes = new byte[count];
        readBuffer.flip();
        readBuffer.get(bytes);
        System.out.println(new String(bytes,"UTF-8"));
        readBuffer.clear();
    }

    public static void main(String[] args) throws Exception {
        new ClientDemo().start();
    }
}
