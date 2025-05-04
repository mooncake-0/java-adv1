package thread.sec11_executor_1.problem_solving;

import java.util.concurrent.Callable;

import static thread.util.MyLogger.*;


// 문제 상황
// 재고 작업, 배송 작업, 금융 작업 세가지가 모두 성공적으로 끝나야지 "성공"처리가 된다
// 단, 하나라도 실패하면 작업은 실패해야 한다
// 기존 코드는 하기와 같고, 작업 실패는 고려하지 않는다

// 다만, 작업과정이 너무 느리다. 성능을 개선해보라는 것이 문제

public class OrderServiceProblem implements OrderService {

    public void order(String orderNo) throws Exception {

        // 시작 시간 측정
        long startTime = System.nanoTime();

        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        Boolean inventoryResult = inventoryWork.call();
        Boolean shippingResult = shippingWork.call();
        Boolean accountingResult = accountingWork.call();

        // 결과 확인
        if (inventoryResult && shippingResult && accountingResult) {
            log("주문 작업이 성공적으로 완료되었습니다");
        } else {
            log("주문 중 일부 작업이 실패하여, 주문에 실패하였습니다");
        }

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
