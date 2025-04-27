package thread.sec10_동시성컬렉션.a_simple;

import java.util.Arrays;

import static thread.util.ThreadUtils.*;

// 일반적인 자료구조를 구현해보자
// - 왜 일반 Collection 계열이 Thread Safe 하지 않은지 먼저 보자
public class BasicList implements SimpleList{

    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size = 0;

    public BasicList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return this.size;
    }


    // 절대 원자적일 수 없다
    @Override
    public void add(Object obj) {
        elementData[size] = obj;
        sleep(100); // 멀티 스레드 문제 확인 가능
        this.size++; // 이미 연산이 나뉘고, Object 배열은 동시이다
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
