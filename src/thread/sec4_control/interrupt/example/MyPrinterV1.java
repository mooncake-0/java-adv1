package thread.sec4_control.interrupt.example;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static thread.util.MyLogger.*;

/*
 실제 Printer 가 동작하는 것처럼!
 Main Thread 에서 Job Queue 에 Print 할 것을 넣어주고
 Work Thread 는 여기에서 꺼내서 작업을 수행한다
 */

public class MyPrinterV1 {

    public static void main(String[] args) {

        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer-thread");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요, 종료는 q: ");
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printerThread.interrupt();
//                printer.work = false;
                break;
            }
            // 작업 넣기
            printer.addJob(input); // Main Thread 가 넣어주고, Work Thread 는 값을 꺼내서 사용한다
        }

    }

    static class Printer implements Runnable {
        /*
         여러 스레드가 활용할 변수, 여러 스레드가 동시에 접근할 자료구조 등에는 항상 세밀한 제어가 필요
         */
//        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedDeque<>(); // 여러 스레드가 같이 쓸 때는 활용

        @Override
        public void run() {

//            while (work) {
            while(!Thread.interrupted()){
                if (jobQueue.isEmpty()) { // job queue 가 비어있으면 while 문을 반복
                    Thread.yield(); // 차라리 다른 스레드가 수행되는게 맞다. 하지만 Sleep 까지 걸어주고 싶진 않을 때 -> 다른 스레드드를 먼저 넣어줌
                    continue;
                }

                String job = jobQueue.poll();
                log("JOB 진행 시작: " + job + ", 대기 중인 Job: " + jobQueue);
                log("출력완료");
                ThreadUtils.sleep(3000);

            }
        }

        public void addJob(String input) {
            jobQueue.offer(input); // add vs offer?
        }
    }
}
