package org.example.optimize;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Stream의 장점 성능 최적화에 대한 예제이다.
 * 1부터 10까지 원소를 가지고 있는 리스트를 모두 1로 바꾼 후 전체 개수를 구하는 문제이다.
 * 전체 개수를 구하는데 1로 변하는 것은 중요하지 않기 때문에 mapToInt 연산을 생략한다.
 * 그래서  System.out.println("e : " + e);에 대한 출력은 나오지 않고
 * 10만이 출력된다.
 */
public class StreamOptimizeIntermediateOperationMeritExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        long count = numbers.stream()
                .mapToInt(e -> { // 동작하지 않는다. mapToInt()연산을 하지 않아도 개수는 변하지 않기 때문
                    System.out.println("e : " + e);
                    return 1;
                })
                .count();
        System.out.println(count); // 10

    }
}