package thread.sec10_동시성컬렉션.b_java_collection;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

public class MapMain {
    public static void main(String[] args) {

        Map<Integer, String> map1 = new ConcurrentHashMap<>(); // 자주쓴다
        map1.put(2, "data2");
        map1.put(1, "data1");
        map1.put(3, "data3");
        System.out.println("map1 = " + map1);

        Map<Integer, String> map2 = new ConcurrentSkipListMap<>(); // Tree Set 대안
        map2.put(2, "data2");
        map2.put(1, "data1");
        map2.put(3, "data3");
        System.out.println("map2 = " + map2);


    }
}
