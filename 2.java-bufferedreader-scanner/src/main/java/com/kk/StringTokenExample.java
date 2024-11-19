package com.kk;

import java.util.StringTokenizer;

/**
 * Scanner와 BufferedReader의 주된 성능 차이인 정규표현식 사용에 대한 대표적인 예시.
 * Java에서 문자열을 토큰화하는데 사용되는 두 방식인 String.split()과 StringTokenizer에서
 * 정규표현식을 이용하는 String.split()이 더 느린 것에 대한 예제
 */
public class StringTokenExample {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int size = 1000_0000;
        for (int i = 0; i < size; i++) {
            sb.append("1 ");
        }
        String target = sb.toString();

        StringTokenizer st = new StringTokenizer(target);
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            System.out.println(st.nextToken());
        }
        long l1 = System.currentTimeMillis();


        long s2 = System.currentTimeMillis();
        String[] s = target.split(" ");
        for(int i = 0; i<size;i++)
            System.out.println(s[i]);
        long l2 = System.currentTimeMillis();
        System.out.println(l1-s1 + "ms"); // 13815ms
        System.out.println(l2-s2 + "ms"); // 15378ms
    }
}
