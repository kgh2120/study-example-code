package org.example.readability;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Stream의 장점 중 가독성의 증가 예제 코드 ToBe
 *  1부터 10까지의 짝수 중에서 그들의 제곱의 합을 구하라.
 */
public class StreamMeritReadabilityToBe {

    public static void main(String[] args) {
        int sum = 0;
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        sum = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(number -> number * number)
                .sum();
        System.out.println(sum);

    }
}