package compareDemo;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Description
 * @author:awei
 * @date:2019/8/9
 * @ver:1.0
 **/
public class CompareDemo {
    public static void main(String[] args) {
        ArrayList<Demo> list = new ArrayList<>();
        list.add(new Demo("adna",18));
        list.add(new Demo("lina",20));
        list.add(new Demo("dawfe",19));

        Collections.sort(list,(o1,o2)->o1.getAge().compareTo(o2.getAge()));
        list.forEach(System.out::println);
    }

}
