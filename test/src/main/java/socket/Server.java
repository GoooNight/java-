package socket;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable{
    private OutputStream outputStream;
    private InputStream inputStream;


    public static void main(String[] args) {
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("8080端口已开启");
            Socket socket = serverSocket.accept();
            server.inputStream = socket.getInputStream();
            server.outputStream = socket.getOutputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(server.inputStream);
            byte[] b = new byte[1024];
            new Thread(server).start();
            while (true){
                System.out.println(new String(b,0,bufferedInputStream.read(b)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                server.inputStream.close();
                server.outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            try {
                outputStream.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
