import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
//      Document document = Jsoup.connect("http://www.silisili.me/").get();
        Document document = Jsoup.connect("http://demo.cssmoban.com/cssthemes6/cpts_1844_dak/index.html").get();
       /* Elements a = document.getElementsByClass("content");*/
        Elements links = document.getElementsByTag("link");
        //FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\test.txt"));
       /* for (Element element : a) {
            String s = element.html();
            s = s.replace("<p>","");
            s = s.replace("</p>","\n\r");
           // outputStream.write(s.getBytes());
            System.out.println(s);
        }*/

        for (Element link :
                links) {
            String href = link.attr("href");
            System.out.println(href);
        }

    }
}
