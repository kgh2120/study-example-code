package org.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamOptimizeIntermediateOperationExample {
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

        /*
            > Task :compileJava
            > Task :processResources NO-SOURCE
            > Task :classes

            > Task :org.example.StreamOptimizeIntermediateOperationExample.main()
            10
            0
            BUILD SUCCESSFUL in 1s
         */
    }
}