package thread.util;

// static 함수를 쓰기 위한 용도이므로, 인스턴스 생성은 방지한다
public abstract class ThreadUtils {

    // 실제로 많이 쓰는 방식
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e) {
            MyLogger.log("인터럽트 발생: " + e.getMessage());
            throw new RuntimeException(e); // 언체크로 변경
        }
    }
}