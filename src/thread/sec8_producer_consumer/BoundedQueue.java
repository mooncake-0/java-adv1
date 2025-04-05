package thread.sec8_producer_consumer;

// 경계가 있는 Queue
public interface BoundedQueue {

    void put(String data); // 생산자 스레드가 버퍼에 데이터 생산

    String take(); // 소비자 스레드가 버퍼를 소비

}
