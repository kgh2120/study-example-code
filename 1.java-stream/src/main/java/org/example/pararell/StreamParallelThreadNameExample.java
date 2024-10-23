package org.example.pararell;

import java.util.Arrays;
import java.util.List;

/**
 * Stream이 벙렬처리를 하는 과정에서 어떤 쓰레드가 동작하는지를 체크합니다.
 */
public class StreamParallelThreadNameExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .forEach(number -> System.out.println(Thread.currentThread().getName() + ": " + number));
        /*
            main: 1
            main: 2
            main: 3
            main: 4
            main: 5
            main: 6
            main: 7
            main: 8
            main: 9
            main: 10
         */
        numbers.parallelStream()
                .forEach(number -> System.out.println(Thread.currentThread().getName() + ": " + number));
        /*
            ForkJoinPool.commonPool-worker-5: 4
            ForkJoinPool.commonPool-worker-2: 2
            ForkJoinPool.commonPool-worker-6: 1
            ForkJoinPool.commonPool-worker-7: 8
            ForkJoinPool.commonPool-worker-4: 9
            ForkJoinPool.commonPool-worker-8: 6
            ForkJoinPool.commonPool-worker-9: 10
            main: 7
            ForkJoinPool.commonPool-worker-3: 5
            ForkJoinPool.commonPool-worker-1: 3
         */
    }
}
