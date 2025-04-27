package thread.sec10_동시성컬렉션.b_java_collection;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {

        Set<Integer> set = new CopyOnWriteArraySet<>();
        set.add(2);
        set.add(1);
        set.add(3);
        System.out.println("set = " + set);

        Set<Object> skipSet = new ConcurrentSkipListSet<>(); // Data 내부를 Sorting 해주는 Set, Comparator 를 넣어줄 수도 있다
        skipSet.add(3);
        skipSet.add(2);
        skipSet.add(1);
        System.out.println("skipSet = " + skipSet);
    }
}
