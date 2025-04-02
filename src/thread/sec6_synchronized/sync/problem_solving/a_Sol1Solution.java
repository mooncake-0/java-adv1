package thread.sec6_synchronized.sync.problem_solving;

import thread.util.MyLogger;

import java.util.concurrent.atomic.AtomicInteger;

// 나의 예상
// 문제는 두 스레드가 현재 교차되며 진행되고 있다
// 여러 코어가 정말 "동시에 count+=1" 을 수행하는 구간으로 누락되는 것 같다 (하나가 저장전에 실행되니 그냥 날라가는 거임)
// 그림을 그려보는게 매우 중요한듯!!
/*
  t1, t2 - 각자!!
  |           |
  | increment | - count 의 값을 읽는다 / 읽은 값에 1 더한다 / 더한 값을 저장한다의 <3가지 과정>
  |    run    |

  >> 두 스레드가 동시에 힙 영역의 count 를 읽는 그림 생각! (두 스레드가 동시에! 실행된다) --- 근데 아마 코드 한줄이기 때문에, 멀티코어라서 가능한 상황이라고 생각한다
  >> *increment 함수의 한줄짜리 코드는 임계 영역임*
 */

/*
 이후 알게 된 건데, "멀티 코어"라서 가능한 상황이였던 것이 아니다!!!!!
 2️⃣ 스레드 전환(TASK SWITCHING)이 count += 1 도중에 발생할 수 있을까?
    네, 발생할 수 있습니다.
    운영체제의 스케줄러는 스레드가 실행되는 도중 언제든지 다른 스레드로 전환할 수 있습니다.
    (스레드 전환은 명령어 단위로 일어날 수도 있고, 명령어 일부만 실행된 상태에서 바뀔 수도 있습니다.)
    즉, count += 1은 한 줄이지만, 이 연산이 CPU 단에서 여러 개의 기계어 명령어로 분할되기 때문에
    스레드가 중간에 전환될 수 있습니다.
    !!!!!!!!!한 줄이라고 안전한게 아니다!!!!!!!!
    >> 그래서 이를 "원자성"에 대한 관점으로 본다. Atomicity 가 보장되면, CPU 가 기계어 명령어로 분할해서 수행하는 한 과정 중 방해받지 않게 물리적으로 설계된 것을 말한다

 📌 정리하면?
    CPU 명령어 1개로 이루어진 연산이면 원자적(Atomic)이다.
    하지만 CPU 명령어가 여러 개로 분할되면, 원자성이 깨질 수 있다.
    원자성을 보장하려면 CAS(Compare-And-Swap) 같은 하드웨어 지원이 필요하다.
    >> 방법 1 : CAS 연산 - HW 지원을 통한, CPU의 CAS 연산을 지원하는 Library 사용 - AtomicInteger.incrementAndGet() 따위 - CPU 단에서 단일 명령어로 수행됨
    >> 방법 2 : 이 문제 솔루션처럼, sync 사용
    >> 방법 3 : 직접 락을 제어하여 사용

 */

public class a_Sol1Solution {

    public static void main(String[] args) throws InterruptedException {

//        Counter counter = new Counter();
        CASCounter counter = new CASCounter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        MyLogger.log("[결과확인]: " + counter.getCount());
    }

    // TIP :: 메모리 가시성 문제도 생기지만, Sync 덕분에 자연스럽게 해결됨을 인지 (Sync 하면 새로 가져옴)
    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count += 1;
        }

        public int getCount() {
            return count;
        }
    }

    // 내가 생각한 또다른 방법
    static class CASCounter {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet(); // ✅ 내부적으로 CAS 연산 수행 (원자성 보장)
        }

        public int getCount() {
            return count.get();
        }
    }
}
