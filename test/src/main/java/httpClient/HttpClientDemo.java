package httpClient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;

/**
 * @Description
 * @author:awei
 * @date:2019/7/30
 * @ver:1.0
 **/
public class HttpClientDemo {
    public static void main(String[] args) throws IOException {

        SocketConfig socketConfig = SocketConfig.custom()
                .setSoKeepAlive(false)
                .setSoLinger(1)
                .setSoReuseAddress(true)
                .setSoTimeout(5000)
                .setTcpNoDelay(true).build();

        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000).build();

        HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultConfig).build();
        String url = "https://cn-zjwz-dx-v-03.acgvideo.com/upgcxcode/80/52/102665280/102665280-1-30280.m4s?expires=1564925400&platform=pc&ssig=BMhfOMVAYAfvrdnlaCmvsQ&oi=3080568704&trid=56bf8eac2290499ca5ec89e354c42542p&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1";
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Origin","https://www.bilibili.com");
        httpGet.addHeader("Range","bytes=0-57317037");
        httpGet.addHeader("Referer","https://www.bilibili.com/bangumi/play/ss26801/?spm_id_from=333.334.b_62696c695f62616e67756d69.5");
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36");
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        System.out.println(httpGet.getRequestLine());
        System.out.println("-----------------");

        System.out.println("-------------");
        System.out.println(response.getLocale());

        System.out.println("----响应类型----");
        System.out.println(entity.getContentType().getValue());
        System.out.println("----内容长度----");
        System.out.println(entity.getContentLength());
        System.out.println("----内容类型----");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(entity.getContent());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\videos\\鬼灭之刃01.m4s")));
        int flag = 0;
        while ((flag = bufferedInputStream.read())!=-1){
            bufferedOutputStream.write(flag);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
        /*System.out.println(EntityUtils.toString(entity,"UTF-8"));*/
        EntityUtils.consume(entity);
        System.out.println("---------------");
        System.out.println();
        String s = null;

        InputStream inputStream = new URL(url).openConnection().getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((s = bufferedReader.readLine())!=null){
            System.out.println(s);
        }
        inputStream.close();
        bufferedReader.close();
        System.out.println("------------------");

        long n = 5;
        BigDecimal result = new BigDecimal(1);
        StringBuffer stringResult = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        for (long i = 1;i <= n ;i++ ){
            result  = result.multiply(new BigDecimal(i));
        }
        System.out.println(result);
        stringResult.append(result);
        for (int j = 0;j<stringResult.length();j++){
            stringBuffer.append("0");
            System.out.println("---- 12  -----");
            System.out.println(stringBuffer);
            int index = stringResult.lastIndexOf(stringBuffer.toString());
            if (index == -1){
                System.out.println("----  34 -----");
                System.out.println(j);
                break;
            }
        }


    }


}
