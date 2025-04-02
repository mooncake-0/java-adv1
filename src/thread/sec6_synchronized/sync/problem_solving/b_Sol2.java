package thread.sec6_synchronized.sync.problem_solving;

import thread.util.MyLogger;


// 실행 결과를 예측하라
// localValue 지역 변수에 대해서 동시성 문제가 발생하는지를 위주로 생각하라
// 내 정답 -- 오~ 설명도 정확했음
// MyCounter 인스턴스는 하나
// MyCounter 인스턴스에 대한 count 함수가 호출되는데, 한번씩 호출된다
/*
  t1, t2
  |             |
  |  count      | - stack 내에서 localVar 를 선언하기 때문에, MyCounter 인스턴스 사용하지 않음
  |  - localVar |
  |  run        |
  즉, 공유자원이 아님
 */
// 따라서 동시성 문제가 발생하지 않고, 각자 1000의 실행결과를 확인할 수 있다

// 해설
// 지역 변수는 스레드 개별 저장 공간인 Stack Frame 안에 생긴다 (자신만 쓰는거, 다른 스레드는 여기 접근 못함)
// synchronized 로 얻는게 없음
public class b_Sol2 {

    public static void main(String[] args) {

        // Thread 자체와는 상관없는 인스턴스를 각 스레드에 데려가 사용하는 모습도 인상적임
        MyCounter myCounter = new MyCounter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

    }

    static class MyCounter {

        public void count() {
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue += 1;
            }
            MyLogger.log("[결과 확인]: " + localValue);
        }

    }

}
