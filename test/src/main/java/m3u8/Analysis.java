package m3u8;

import java.io.*;
import java.util.ArrayList;

/**
 * @Description
 * @author:awei
 * @date:2019/7/30
 * @ver:1.0
 **/
public class Analysis {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(new File("C:\\Users\\Administrator\\Desktop\\videos\\e00316w2fzo.321004.ts.m3u8"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String buffer = null;
        ArrayList<String> list = new ArrayList<>();
        while ((buffer = bufferedReader.readLine())!=null){
            if (!buffer.startsWith("#")){
                System.out.println(buffer);
                list.add(buffer);
            }
        }

    }
}
