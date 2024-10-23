package org.example.optimize;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Stream의 단점 성능 최적화의 부작용에 대한 예제이다.
 * 1부터 10까지 원소를 가지고 있는 리스트를 모두 1로 바꾼 후 전체 개수를 구하는 문제이다.
 * 전체 개수를 구하는데 1로 변하는 것은 중요하지 않기 때문에 mapToInt 연산을 생략한다.
 * 그래서 counter에 대한 값이 증가하지 않는 문제가 발생한다.
 * 본래의 예상대로라면 카운터의 값이 10이 되어야 하는데, 그렇지 않고 0이 출력되는 문제가 발생한다.
 */
public class StreamOptimizeIntermediateOperationDisadvantageExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        AtomicInteger counter = new AtomicInteger(0);

        long count = numbers.stream()
                .mapToInt(e -> { // 동작하지 않는다. mapToInt()연산을 하지 않아도 개수는 변하지 않기 때문
                    counter.incrementAndGet(); // 하지만 side-effect도 동작하지 않기 때문에, stream연산을 완전히 신뢰해서는 안된다.
                    System.out.println("e : " + e);
                    return 1;
                })

                .count();
        System.out.println(count); // 10
        System.out.println(counter.get()); // 0

    }
}