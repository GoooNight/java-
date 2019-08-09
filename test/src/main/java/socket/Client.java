package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private OutputStream outputStream;
    private InputStream inputStream;

    public static void main(String[] args) {
        Client client = new Client();
        try {
            Socket socket = new Socket("localhost",8080);
            client.inputStream = socket.getInputStream();
            client.outputStream = socket.getOutputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(client.inputStream);
            byte[] b = new byte[1024];
            new Thread(client).start();
            while (true){
                System.out.println(new String(b,0,bufferedInputStream.read(b)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {

                client.inputStream.close();
                client.outputStream.close();
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
