package thread.sec10_동시성컬렉션.b_java_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncListMain {

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>()); // 동기화가 적용된 컬렉션으로 변환
        list.add("data1");
        list.add("data2");
        list.add("data3");
        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}
