package resourcesDownload;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;

public class VideoURL {
    volatile static int flag = 0;


    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://www.silisili.me/play/2114-12.html").get();
        String s = document.getElementsByTag("iframe").attr("src");
        System.out.println(s);
        Document document1 = Jsoup.connect(s).get();
        String videoUrl = document1.getElementsByTag("source").attr("src");
        System.out.println(videoUrl);
        String videoName = videoUrl.substring(videoUrl.lastIndexOf("/"));
        InputStream inputStream = new URL(videoUrl).openConnection().getInputStream();
        File file = new File("C:\\Users\\Administrator\\Desktop\\videos"+videoName);
        OutputStream outputStream = new FileOutputStream(file);
        long timeBegin = System.currentTimeMillis();
        Thread thread = new Thread(new VideoSpeed(file));
        thread.start();
        IOUtils.copy(inputStream,outputStream);
        long timeEnd = System.currentTimeMillis();
        flag = 1;
        System.out.println("总共花费时间："+(timeEnd-timeBegin)/1000+"s");
        inputStream.close();
        outputStream.close();
    }
}
