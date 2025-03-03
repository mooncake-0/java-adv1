package thread.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class MyLogger { // 구현체로 선언 불가

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS"); // 이런 Formatter 가지고 너무 깊이 팔 필요 없다...

    public static void log(Object object) {
        String timeFormat = LocalDateTime.now().format(formatter);

        // %9s 는 9칸을 확보한 상태에서 채운다는 뜻
        // object 도 toString 자동 출력을 지원하도록 포멧 출력
        System.out.printf("%s [%9s] %s\n", timeFormat, Thread.currentThread().getName(), object);
    }
}
