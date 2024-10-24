package org.example.characteristic;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Stream은 Lazy한 특성을 가지고 있습니다.
 * 최종 연산(ex. forEach or collect...)이 발생하지 않으면 중간 연산은 동작하지 않습니다.
 */
public class LazyExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        IntStream intStream = numbers.stream()
                .mapToInt(e -> { // 동작하지 않는다. mapToInt()연산을 하지 않아도 개수는 변하지 않기 때문
                    System.out.println("e : " + e);
                    return 1;
                });
        // 아무 내용도 출력되지 않음.
        /*
            오후 12:50:38: Executing ':org.example.lazy.StreamIsLazyExample.main()'...

            > Task :compileJava
            > Task :processResources NO-SOURCE
            > Task :classes
            > Task :org.example.lazy.StreamIsLazyExample.main()

            BUILD SUCCESSFUL in 532ms
            2 actionable tasks: 2 executed
            오후 12:50:39: Execution finished ':org.example.lazy.StreamIsLazyExample.main()'.
         */
    }
}
