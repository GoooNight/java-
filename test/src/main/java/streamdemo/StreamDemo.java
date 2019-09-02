package streamdemo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @author:awei
 * @date:2019/8/29
 * @ver:1.0
 **/
public class StreamDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(8);
        Random random = new Random();
        for (int i=0;i<10;i++){
            list.add(random.nextInt(100));
        }

//        List<Integer> list1 = list.stream().sorted().collect(Collectors.toList());
        Collections.sort(list,Comparator.comparing(Object::toString));
        list.forEach(System.out::println);
    }
}
