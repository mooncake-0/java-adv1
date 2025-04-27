package thread.sec10_동시성컬렉션.a_simple;

import java.util.Arrays;

import static thread.util.ThreadUtils.sleep;

// BasicList 에 Sync 함수 붙여서 Intrinsic Lock 으로 해결
public class SyncList implements SimpleList{

    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size = 0;

    public SyncList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public synchronized void add(Object obj) {
        elementData[size] = obj;
        sleep(100);
        this.size++;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size)) + " size= " + size + ", capacity= " + elementData.length;

    }
}
