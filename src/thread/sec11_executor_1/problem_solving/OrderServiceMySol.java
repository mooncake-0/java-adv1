package thread.sec11_executor_1.problem_solving;

import thread.sec11_executor_1.RunnableTask;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static thread.util.MyLogger.log;


// 내 해결 로직
// 우선 문제는 로직 안에서 스레드를 Callable 작업으로 그때 그때 만들어서 수행한다는 것
// 심지어 스레드를 만들고 있는 것도 아니다 -> 그냥 객체 내부 함수 실행
// Callable 을 사용하려면 Es 가 필요하다

public class OrderServiceMySol implements OrderService {

    public void order(String orderNo) throws Exception {

        long startTime = System.nanoTime();

        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        // 작업의 단위가 정해져 있다 (3가지). 따라서, 그냥 일괄 수행 후 일괄 결과를 받아보자
        ExecutorService es = Executors.newFixedThreadPool(3); // 세가지 작업을 동시 실행한다
        List<Future<Boolean>> futures = es.invokeAll(List.of(inventoryWork, shippingWork, accountingWork));

        for (Future<Boolean> future : futures) {
            if (!future.get()) {
                log("주문 중 일부 작업이 실패하여, 주문에 실패하였습니다");
                es.close();
                return;
            }
        }
        log("주문 작업이 성공적으로 완료되었습니다");
        es.close();

        // 종료 시간 측정
        long endTime = System.nanoTime();

        // 걸린 시간 계산 (나노초 -> 밀리초 변환)
        long durationInMillis = (endTime - startTime) / 1_000_000;
        System.out.println("수행 시간: " + durationInMillis + "ms");
    }

    static class InventoryWork implements Callable<Boolean> {

        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() throws Exception {
            log("재고 업데이트 : " + orderNo);
            Thread.sleep(1000);
            return true;
        }
    }

    static class ShippingWork implements Callable<Boolean> {

        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() throws Exception {
            log("배송 시스템 알림 : " + orderNo);
            Thread.sleep(1000);
            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean> {

        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() throws Exception {
            log("회계 시스템 업데이트 : " + orderNo);
            Thread.sleep(1000);
            return true;
        }
    }
}
