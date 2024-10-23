package org.example.pararell;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1부터 10억까지의 수를 더하는 문제
 * for-each문을 이용해서 처리해보자.
 */
public class StreamParallelAsIs {
    public static void main(String[] args) {
        int[] numbers = new int[10_0000_0000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        long start = System.currentTimeMillis();
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println(end-start); // 255
    }
}
