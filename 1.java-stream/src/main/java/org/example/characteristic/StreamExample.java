package org.example.characteristic;

import java.util.List;

/**
 * Stream은 이름에서 알 수 있듯이 각각의 요소가 하나의 흐름대로 동작을 합니다.
 * 아래 주석처럼 모든 요소들이 각 과정을 단계 단계 거치는 것이 아닌, 한 요소가 모든 과정을 거치고 다음 요소로 넘어가는 순서로 동작합니다.
 * 병렬 상황에서도 쓰레드의 이름을 살펴보면 한 숫자를 담당한 쓰레드는 마지막의 forEach문까지 담당하는 것을 확인할 수 있습니다.
 */
public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        numbers.stream()
                .filter(number -> {
                    if (number % 2 == 0) {
                        System.out.println(number + "는 짝수입니다.");
                    }else {
                        System.out.println(number + "는 홀수에요 ㅠ");
                    }
                    return number % 2 == 0;
                })
                .mapToInt(e -> { // 동작하지 않는다. mapToInt()연산을 하지 않아도 개수는 변하지 않기 때문
                    System.out.println("e : " + e);
                    return e;
                }).forEach(System.out::println);
                /*
                    1는 홀수에요 ㅠ
                    2는 짝수입니다.
                    e : 2
                    2
                    3는 홀수에요 ㅠ
                    4는 짝수입니다.
                    e : 4
                    4
                    5는 홀수에요 ㅠ
                    6는 짝수입니다.
                    e : 6
                    6
                    7는 홀수에요 ㅠ
                    8는 짝수입니다.
                    e : 8
                    8
                    9는 홀수에요 ㅠ
                    10는 짝수입니다.
                    e : 10
                    10
                 */


        numbers.parallelStream()
                .filter(number -> {
                    if (number % 2 == 0) {
                        System.out.println(number + "는 짝수입니다. " + Thread.currentThread().getName());
                    }else {
                        System.out.println(number + "는 홀수에요 ㅠ "+ Thread.currentThread().getName());
                    }
                    return number % 2 == 0;
                })
                .mapToInt(e -> { // 동작하지 않는다. mapToInt()연산을 하지 않아도 개수는 변하지 않기 때문
                    System.out.println("e : " + e + " " + Thread.currentThread().getName());
                    return e;
                }).forEach(e -> System.out.println("final : " + e + " " + Thread.currentThread().getName()));
        /*
            3는 홀수에요 ㅠ ForkJoinPool.commonPool-worker-1
            8는 짝수입니다. ForkJoinPool.commonPool-worker-7
            4는 짝수입니다. ForkJoinPool.commonPool-worker-5
            7는 홀수에요 ㅠ main
            2는 짝수입니다. ForkJoinPool.commonPool-worker-2
            1는 홀수에요 ㅠ ForkJoinPool.commonPool-worker-6
            6는 짝수입니다. ForkJoinPool.commonPool-worker-8
            5는 홀수에요 ㅠ ForkJoinPool.commonPool-worker-3
            10는 짝수입니다. ForkJoinPool.commonPool-worker-9
            9는 홀수에요 ㅠ ForkJoinPool.commonPool-worker-4
            e : 4 ForkJoinPool.commonPool-worker-5
            e : 2 ForkJoinPool.commonPool-worker-2
            e : 8 ForkJoinPool.commonPool-worker-7
            e : 6 ForkJoinPool.commonPool-worker-8
            e : 10 ForkJoinPool.commonPool-worker-9
            final : 6 ForkJoinPool.commonPool-worker-8
            final : 2 ForkJoinPool.commonPool-worker-2
            final : 8 ForkJoinPool.commonPool-worker-7
            final : 10 ForkJoinPool.commonPool-worker-9
            final : 4 ForkJoinPool.commonPool-worker-5
         */



    }
}
