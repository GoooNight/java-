package resourcesDownload;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;

/**
 * @Description
 * @author:awei
 * @date:2019/8/1
 * @ver:1.0
 **/
public class DownloadDemo {

    public static void main(String[] args) {
        try {
            String filePath = "D:\\iso\\centes.iso";
            InputStream inputStream = new URL("").openConnection().getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            Thread thread = new Thread(new VideoSpeed(new File(filePath)));
            long timeBegin = System.currentTimeMillis();
            thread.start();
            IOUtils.copy(bufferedInputStream,bufferedOutputStream);
            long timeEnd = System.currentTimeMillis();
            VideoURL.flag = 1;
            System.out.println("总共花费时间："+(timeEnd-timeBegin)/1000+"s");
            bufferedInputStream.close();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
