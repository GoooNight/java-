package resourcesDownload;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @author:awei
 * @date:2019/8/11
 * @ver:1.0
 **/
public class PicDownload {
    static int len = 10;
    /**
     * 匹配的模式
     */
    static final Pattern pattern = Pattern.compile("new\\S+\\.jpg");
    static InputStream inputStream=null;
    static OutputStream outputStream =null;
    static BufferedInputStream bufferedInputStream;
    static BufferedOutputStream bufferedOutputStream;
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://comic2.ikkdm.com/comiclist/1733/index.htm").get();
        Element comiclistn = document.getElementById("comiclistn");
        Elements dd = comiclistn.getElementsByTag("dd");
        int section = 1;
        for (Element e :
                dd) {
            Elements a = e.getElementsByTag("a");
            String href = a.get(2).attr("href");
            String url = href;
            href  = href.substring(0,href.lastIndexOf("/")+1);
            System.out.println(href);
            String se = "E:\\漫画\\七大罪\\第"+section+"话";
            File file = new File(se);
            if (!file.exists()){
                file.mkdir();
            }
            for (int i = 1 ;url!=null&&!url.equals("");i++){
                try {
                    url = download(href+i+".htm",i,se);
                }catch (HttpStatusException e1){
                    e1.printStackTrace();
                    break;
                }catch (IOException e2){
                    e2.printStackTrace();
                    try {
                        url = download(href+i+".htm",i,se);
                    }catch (IOException e3) {
                        e3.printStackTrace();
                        continue;
                    }
                    continue;
                }

            }
            section++;


        }
    }
    public static String download(String href,int index,String se) throws IOException {
        String url="";
        String url_s="";
        Document document = Jsoup.connect(href).get();
        Elements table = document.getElementsByTag("table");
        Elements script = table.get(1).getElementsByTag("script");
        String html = script.get(0).html();
        Matcher m = pattern.matcher(html);
        if (m.find()) {
            int i = 0;
            url_s = "http://n2.1whour.com/"+m.group(i);
            String encode = URLEncoder.encode(url_s, "UTF-8");
            url= encode.replaceAll("%2F", "/").replaceAll("%3A",":");
            String name_scend = url_s.substring(0,url_s.lastIndexOf("/"));
            String name = name_scend.substring(name_scend.lastIndexOf("/")+1)+"第"+index+"张.jpg";
            System.out.println(name);
            outputStream = new FileOutputStream(new File(se+"\\"+name));
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36");
            inputStream = urlConnection.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            IOUtils.copy(bufferedInputStream,bufferedOutputStream);
            System.out.println(url);

        }
        /*http://n2.1whour.com/newkuku/2013/201301/20130111/七原罪第一话/kukudm.com_0103F.jpg*/
        return url;
    }
}
