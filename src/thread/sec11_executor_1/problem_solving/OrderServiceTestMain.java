package thread.sec11_executor_1.problem_solving;

public class OrderServiceTestMain {

    public static void main(String[] args) throws Exception {
        String orderNo = "Order#1234";
        OrderService orderService1 = new OrderServiceProblem();
        orderService1.order(orderNo);
        OrderService orderService2 = new OrderServiceMySol();
        orderService2.order(orderNo);
        OrderService orderService3 = new OrderServiceAnw();
        orderService3.order(orderNo);
    }
}
