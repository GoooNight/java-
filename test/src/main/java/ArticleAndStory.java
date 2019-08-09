import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description
 * @author:awei
 * @date:2019/7/30
 * @ver:1.0
 **/
public class ArticleAndStory {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://blog.csdn.net/zh_JNU/article/details/88944892").get();

        String views = document.getElementById("content_views").html();
        String s = views.replaceAll("<p>", "")
                .replaceAll("</p>","</p>\r\n")
                .replaceAll("&nbsp;"," ")
                .replaceAll("<ol>","")
                .replaceAll("</ol>","\r\n")
                .replaceAll("<li>","    ")
                .replaceAll("</li>","</p>\r\n")
                .replaceAll("<span+.*?>","")
                .replaceAll("</span>","")
                .replaceAll("<(/)?strong>","");
        String[] split = s.split("</p>");
        OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\test.txt"));

        for (String string :
                split) {
            StringBuffer stringBuffer = new StringBuffer(string);
            for (int i = 60;i<stringBuffer.length();i+=54){

                stringBuffer.insert(i,"\r\n");

            }

            outputStream.write(stringBuffer.toString().getBytes());
            System.out.println(stringBuffer);
        }

    }
}
