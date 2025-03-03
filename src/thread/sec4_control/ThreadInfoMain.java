package thread.sec4_control;

import thread.sec3_start.HelloRunnable;

import static thread.util.MyLogger.*;

public class ThreadInfoMain {

    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();

        log("main thread: " + mainThread); // toString : ID / 이름 / 우선순위 / 그룹 의 문자열을 반환
        log("main thread id: " + mainThread.threadId()); // ID 는 스레드의 고유 식별자, 직접 지정 불가, 생성될 때 할당
        log("main thread name: " + mainThread.getName());

        // Priority 는 기본이 5이고, 10이 가장 높음. 높을 수록 CPU 에게 부탁하는 것임 - 이거 좀 더 써달라 (근데 OS 가 고려는 하지만 무조건 적으로 고려하진 않는다)
        // Priority 를 직접 조정할 일은 거의 없다. OS 가 알아서 잘 해줌
        log("main thread priority: " + mainThread.getPriority());

        // 스레드는 그룹에 속하게 됨, 그룹화하여 관리 하는 기능 (일괄 종료, 일괄 우선순위 적용 등) - 스레드 그룹은 걍 있다 정도만 알면 됨. 거의 안씀
        // 기본적으로 모든 스레드는 부모 스레드와 동일한 스레드 그룹에 속함
        // 부모 스레드
        // - 스레드는 기본적으로 다른 스레드로인해 생성된다 (생성한 스레드를 부모로 간주) (가령 myThread 는 main 으로부터 생성되었고 부모로 간주한다)
        log("main thread group: " + mainThread.getThreadGroup());

        // 스레드의 상태
        // NEW : 아직 시작되지 않ㅇ는 상태
        // RUNNABLE : 스레드가 실행 중 or 실행 준비됨
        // BLOCKED : 스레드가 "동기화 락"을 기다리는 상태
        // WAITING : 스레드가 타 스레드 작업 완료되기를 기다리는 상태
        // TIMED_WAITING : Thread.sleep() 했을 때, 일정 시간동안 기다리는 상태
        // TERMINATED : 스레드가 실행을 마친 상태
        log("main thread state: " + mainThread.getState());


        // my Thread
        Thread myThread = new Thread(new HelloRunnable(), "myThread"); // 스레드 이름은 디버깅 용
        log("my thread: " + myThread);
        log("my thread id: " + myThread.threadId());
        log("my thread name: " + myThread.getName());
        log("my thread priority: " + myThread.getPriority());
        log("my thread group: " + myThread.getThreadGroup());
        log("my thread state: " + myThread.getState()); // 생성만 하고 실행은 안했으므로 NEW state 일 것
    }


}