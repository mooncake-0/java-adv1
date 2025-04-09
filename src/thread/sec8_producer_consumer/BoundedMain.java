package thread.sec8_producer_consumer;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class BoundedMain {

    public static void main(String[] args) {
        //1. BoundedQueue 선택
//        BoundedQueue queue = new BoundedQueueV1(2);
//        BoundedQueue queue = new BoundedQueueV2(2);
//        BoundedQueue queue = new BoundedQueueV3(2);
//        BoundedQueue queue = new BoundedQueueV4(2);
        BoundedQueue queue = new BoundedQueueV5(2);


        //2. 생산자, 소비자 실행 순서 선택 (반드시 하나 선택)
        // 하나만 골라서 실행한다
        producerFirst(queue); // 생산자 먼저 실행
//        consumerFirst(queue);
    }

    // 절차 지향이 중요
    private static void producerFirst(BoundedQueue queue) {
        log("===== [생산자 먼저 실행 작업] 시작, " + queue.getClass().getSimpleName() + "=====");

        List<Thread> threads = new ArrayList<>();
        buildAndStartProducer(queue, threads);
        printAllState(queue, threads); // 만든 스레드들이 담긴 모습을 출력해보자
        buildAndStartConsumer(queue, threads);
        printAllState(queue, threads);

        log("===== [생산자 먼저 실행 작업] 종료, " + queue.getClass().getSimpleName() + "=====");
    }

    private static void consumerFirst(BoundedQueue queue) { // 순서만 반대
        log("===== [소비자 먼저 실행 작업] 시작, " + queue.getClass().getSimpleName() + "=====");

        List<Thread> threads = new ArrayList<>();
        buildAndStartConsumer(queue, threads);
        printAllState(queue, threads); // 만든 스레드들이 담긴 모습을 출력해보자
        buildAndStartProducer(queue, threads);
        printAllState(queue, threads);

        log("===== [소비자 먼저 실행 작업] 종료, " + queue.getClass().getSimpleName() + "=====");
    }

    private static void buildAndStartConsumer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) { // 생산자 3개 만들기
            Thread consumer = new Thread(new ConsumerTask(queue), "소비자 " + i);
            threads.add(consumer);
            consumer.start();
            sleep(100); // 역시 1,2,3 순서대로 시행되게끔 해줘보자
        }
    }

    private static void buildAndStartProducer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) { // 생산자 3개 만들기
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "생산자 " + i);
            threads.add(producer);
            producer.start();
            sleep(100); // 1,2,3 순서대로 시행되게끔 해줘보자
        }
    }

    // 어떤 thread 가 어떤 상태인지 출력한다
    private static void printAllState(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 현황: " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }
    }
}
