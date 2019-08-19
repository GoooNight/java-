package resourcesDownload;

import java.io.IOException;
import java.net.*;

/**
 * @Description
 * @author:awei
 * @date:2019/8/12
 * @ver:1.0
 **/
public class test {
    public static void main(String[] args) throws IOException {
        URLConnection urlConnection = new URL("http://n2.1whour.com/newkuku/2013/201301/20130111/%E4%B8%83%E5%8E%9F%E7%BD%AA%E7%AC%AC%E4%B8%80%E8%AF%9D/kukudm.com_0103F.jpg")
                .openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36");
        String contentType = urlConnection.getContentType();
        System.out.println(contentType);
        String s = "http://n2.1whour.com/newkuku/2013/201301/20130111/七原罪第一话/kukudm.com_0103F.jpg";
        System.out.println(URLDecoder.decode(s,"UTF-8"));
        String encode = URLEncoder.encode(s, "UTF-8");
        String s1 = encode.replaceAll("%2F", "/").replaceAll("%3A",":");

        System.out.println(s1);
        System.out.println(encode);
    }
}
