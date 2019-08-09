package algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @Description
 * @author:awei
 * @date:2019/7/31
 * @ver:1.0
 **/
public class FACT {

    public static BigDecimal calcu(long n){
        BigDecimal result = new BigDecimal(1);
        StringBuffer stringResult = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        for (long i = 2;i <= n ;i++ ){
            result  = result.multiply(new BigDecimal(i));
        }
        System.out.println(result);
        stringResult.append(result);
        for (int j = 0;j<stringResult.length();j++){
            stringBuffer.append("0");
            int index = stringResult.lastIndexOf(stringBuffer.toString());
            if (index == -1){
                System.out.println("---- "+n+"  -----");
                System.out.println(j);
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        ArrayList<BigDecimal> integers = new ArrayList<>();
        BigDecimal calcu = new BigDecimal(1);
        for (int i = 1;i<=452;i++){
            if (integers.size()==0){
                calcu = calcu(i);
                integers.add(calcu);
                System.out.println(calcu);
            }else {
                calcu = calcu.multiply(new BigDecimal(i));
                integers.add(calcu);
                System.out.println(calcu);
            }
        }
    }
}
