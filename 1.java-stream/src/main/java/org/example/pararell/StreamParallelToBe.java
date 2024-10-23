package org.example.pararell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1부터 10억까지의 수를 정렬하는 문제
 * Stream의 병렬 처리를 이용해서 해결해보자.
 */
public class StreamParallelToBe {
    public static void main(String[] args) {
        int[] numbers = new int[10_0000_0000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        long start = System.currentTimeMillis();

        long sum = Arrays.stream(numbers).parallel()
                .mapToLong(i -> i)
                .sum();
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println(end-start); // 225

    }
}
