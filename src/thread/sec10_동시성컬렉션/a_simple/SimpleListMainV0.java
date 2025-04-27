package thread.sec10_동시성컬렉션.a_simple;

import java.util.ArrayList;
import java.util.List;

// Array List 는 Thread Safe 할까?
public class SimpleListMainV0 {

    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        System.out.println(list);

    }
}
