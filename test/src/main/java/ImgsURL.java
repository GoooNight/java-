import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;

public class ImgsURL {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://www.silisili.me/").get();
        Elements img = document.getElementsByTag("img");
        for (Element element : img) {
            try {
                URL url = new URL(element.attr("src"));
                System.out.println(element.attr("src"));
                String string = element.attr("src").substring(element.attr("src").lastIndexOf("/"));
                InputStream inputStream = url.openConnection().getInputStream();
                OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\imgs\\"+string));
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(b))!=-1){
                    outputStream.write(b,0,len);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
