import jdk.nashorn.internal.ir.Flags;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @author:awei
 * @date:2019/8/2
 * @ver:1.0
 **/
public class projectTest {
    public static void main(String[] args) throws IOException {
        Map<String,List> map = new HashMap();
        map.put("专属码格式错误：",new ArrayList<String>());
        map.put("无法导入的数据：",new ArrayList<String>());
        String fileName = "C:\\Users\\Administrator\\Desktop\\test.xlsx";
        Workbook sheets = null;
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        if ( fileName.endsWith(".xls")){
            sheets = new HSSFWorkbook(fileInputStream);
        }else if (fileName.endsWith("xlsx")){
            sheets = new XSSFWorkbook(fileInputStream);
        }else {
            System.out.println("文件类型错误!");
            return;
        }
        Sheet sheetAt = sheets.getSheetAt(0);
        for (int i= 0;i<=sheetAt.getLastRowNum();i++){
            Row row = sheetAt.getRow(i);
            for (int j = 0;j<row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                if (cell!=null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (cell.toString().matches("([A-Za-z]+|[0-9]+)+")) {
                        System.out.println(cell);
                    }else {
                        map.get("专属码格式错误：").add((i+1)+"行"+(j+1)+"列");
                        System.out.println("专属码格式错误："+(i+1)+"行"+(j+1)+"列");
                    }
                }else {
                    map.get("无法导入的数据：").add((i+1)+"行"+(j+1)+"列");
                    System.out.println("无法导入的数据："+(i+1)+"行"+(j+1)+"列");
                }
            }
        }
        for (String key : map.keySet()) {
            System.out.println(key);
            map.get(key).forEach(System.out::println);
        }
        fileInputStream.close();
    }
}
