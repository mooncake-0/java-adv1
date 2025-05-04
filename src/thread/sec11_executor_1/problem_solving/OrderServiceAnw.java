package thread.sec11_executor_1.problem_solving;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static thread.util.MyLogger.log;

// 강사님의 솔루션
public class OrderServiceAnw implements OrderService {

    private final ExecutorService es = Executors.newFixedThreadPool(10); // 스레드 풀 수는 시스템 상황에 따라서 달라지는 것이라고 보면 된다 (적거나 많거나 모두 동일)

    @Override
    public void order(String orderNo) throws Exception {
        long startTime = System.nanoTime();

        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        // 작업을 ES 에게 제출 - 작업을 하나씩 요청한다
        Future<Boolean> invFuture = es.submit(inventoryWork);
        Future<Boolean> shipFuture = es.submit(shippingWork);
        Future<Boolean> accountFuture = es.submit(accountingWork);

        // 작업 완료 대기 및 각각 결과 반환 -> InvokeAll 보다 "각각의 결과" 를 인식하기 쉽다는 장점은 있다 (하지만 IA 도 불가능한건 당연히 아닐듯)
        boolean invResult = invFuture.get();
        boolean shipResult = shipFuture.get();
        boolean accountResult = accountFuture.get();

        // 결과 확인
        if (invResult && shipResult && accountResult) {
            log("주문 작업이 성공적으로 완료되었습니다");
        } else {
            log("주문 중 일부 작업이 실패하여, 주문에 실패하였습니다");
        }

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
